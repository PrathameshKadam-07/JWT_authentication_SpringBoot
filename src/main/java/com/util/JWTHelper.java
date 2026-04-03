package com.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTHelper {
	private final long expire = 1000*60*60;
	
	private String Secret = "hbhbahbdbbakbkjadsadsdadasdsdasdasdassadsadabjfbakdb0";
	private SecretKey key = Keys.hmacShaKeyFor(Secret.getBytes());
	
	public String getJwtToken(String username) {
			return Jwts.builder()
					.setSubject(username)
					.setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis()+expire))
					.signWith(key,SignatureAlgorithm.HS256)
					.compact();
	}

}
