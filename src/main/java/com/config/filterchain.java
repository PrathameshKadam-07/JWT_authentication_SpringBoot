package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.filter.JWTfilter;

@Configuration
@EnableWebSecurity
public class filterchain {

	@Autowired
	JWTfilter jwtfilter;
	
	@Bean
	SecurityFilterChain secfilterchain(HttpSecurity http) throws Exception {
			http
				.csrf(csrf-> csrf.disable())
			    .sessionManagement(session -> 
		            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			     
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/h2-console/**").permitAll()
						.requestMatchers("/api/authenticate").permitAll()
						.requestMatchers("/api/public").permitAll()
						.anyRequest().authenticated())
			
				
			.headers(head-> head.frameOptions(frame->frame.disable()));
			
//			added filter before UsernamePasswordAuthenticationFilter
			http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
			
		return http.build();
	}
	
	
	@Bean
	PasswordEncoder pencode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//	   NEW METHODE DIRECT
		return config.getAuthenticationManager();
	     
//	    OLD METHODE
//	    DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
//	    dap.setUserDetailsService(userDetailService);
//	    dap.setPasswordEncoder(pe);
//	    return new ProviderManager(dap);
	}}

