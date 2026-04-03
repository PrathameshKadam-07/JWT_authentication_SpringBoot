# 🔐 Spring Boot JWT Authentication System

## 📌 Overview
This project is a complete implementation of **JWT (JSON Web Token) based authentication** using **Spring Boot** and **Spring Security**. It demonstrates how to build a **stateless, secure, and scalable authentication system** for REST APIs.

Unlike traditional session-based authentication, this system uses **token-based security**, making it ideal for modern applications such as microservices and distributed systems.

---

## 🎯 Objectives
- Implement stateless authentication using JWT
- Secure REST APIs using Spring Security filters
- Understand token generation, validation, and parsing
- Replace session-based authentication with token-based architecture
- Build a scalable and production-ready security system

---

## 🚀 Features
- 🔐 JWT-based authentication and authorization
- 🧾 Token generation upon successful login
- 🔍 Token validation and parsing using custom filter
- ⚙️ Custom `JWTFilter` extending `OncePerRequestFilter`
- 👤 Custom `UserDetails` and `UserDetailsService`
- 🔒 Password encryption using `BCryptPasswordEncoder`
- 🚫 Stateless session management (`SessionCreationPolicy.STATELESS`)
- 🌐 Public and secured REST endpoints
- 🛡️ Secure API access using Authorization header

---

## 🏗️ System Architecture

### 🔄 Authentication Flow

```text
                  Client (Login Request)
                          ↓
                  AuthenticationController (/api/authenticate)
                          ↓
                  AuthenticationManager
                          ↓
                  UserDetailsService → Load User
                          ↓
                  Password Validation (BCrypt)
                          ↓
                  JWT Token Generated
                          ↓
                  Client Stores Token
                          ↓
                  -------------------------------------
                  Subsequent Requests
                          ↓
                  Authorization: Bearer <JWT>
                          ↓
                  JWT Filter (OncePerRequestFilter)
                          ↓
                  Token Validation (Signature + Expiry)
                          ↓
                  SecurityContextHolder (Authentication Set)
                          ↓
                  Access Granted to Protected APIs
```

---

## 🧠 Core Concepts Explained
### 🔑 1. JWT (JSON Web Token)

JWT is a compact, URL-safe token used for secure authentication.

Structure:
```
 Header.Payload.Signature
```
- Header → Algorithm & token type
- Payload → User data (username, roles, etc.)
- Signature → Ensures token integrity

### 🔒 2. Stateless Authentication

- No session stored on server
- Each request carries its own authentication token
- Improves scalability and performance
- 
### ⚙️ 3. JWT Filter

Custom filter that:

- Extracts token from header
- Validates token
- Sets authentication in SecurityContextHolder
```
 Authorization: Bearer <token>
```

### 🔍 4. Token Validation

Token is validated by:

- Checking signature (using secret key)
- Verifying expiration time
- Matching username with database
  
### 👤 5. UserDetails & UserDetailsService

- UserDetails → Represents authenticated user
- UserDetailsService → Loads user from database

---
  
### 📂 Project Structure
```
    src/main/java/com/
    │
    ├── config/          # Security configuration
    ├── controller/      # Authentication & API controllers
    ├── entity/          # User entity (implements UserDetails)
    ├── service/         # User service (UserDetailsService)
    ├── filter/          # JWT filter (OncePerRequestFilter)
    ├── util/            # JWT utility/helper class
    └── repository/      # Database access layer
```

### 🌐 API Endpoints
```

Endpoint	          | Method   |  Access	|         Description
- /api/authenticate   |	 POST 	 |  Public  |   Login & generate JWT token
- /api/public	      |  GET	 |  Public	|   Accessible without auth
- /api/private	      |  GET	 |  Secured	|   Requires valid JWT token
```

  ---
  
### 🔐 Authentication Process
Step 1: Login Request
```
POST /api/authenticate
Content-Type: application/json

{
  "username": "user",
  "password": "password"
}
```

Step 2: Response (JWT Token)
```
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

Step 3: Access Secured API
```
GET /api/private
Authorization: Bearer eyJhbGciOiJIUzI1NiIs...
```

---

### ⚙️ Security Configuration Highlights

- CSRF disabled for REST APIs
- Stateless session management
- JWT filter added before authentication filter
 ```
http
    .csrf(csrf -> csrf.disable())
    .sessionManagement(session -> 
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    .authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/authenticate").permitAll()
        .anyRequest().authenticated()
    );

http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

```

---

### 🛠️ Technologies Used

- Java 17+
- Spring Boot
- Spring Security
- JWT (io.jsonwebtoken)
- Maven
- JPA 
- H2 / MySQL

### 🧪 How to Run

Clone repository:
```
git clone <your-repo-url>
```

Navigate:
```
cd project-folder
```

Run:
```
mvn spring-boot:run
```

---
