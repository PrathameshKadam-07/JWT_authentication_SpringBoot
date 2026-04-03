package com.util;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
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

	public String extractUsername(String token) {
		String username = getClaims(token).getSubject();
		
		return username;
	}
	
	public Date getExpirationdate(String token) {
		Date date = getClaims(token).getExpiration();
		return date;
	}
	
	public boolean isdateExpire(String token) {
//		check username is not null and token is not expire.
		
		String username = extractUsername(token);
		Date date = getExpirationdate(token);
		
		return username!=null && !date.before(new Date());
	}
	
	public boolean validatetoken(String username,UserDetails ub,String token) {
//		Check username is equal to database Username.
		
		return username.equals(ub.getUsername()) && isdateExpire(token);
	}
	
	
	
	public Claims getClaims(String token) {
	    try {
	        return Jwts.parserBuilder()
	                .setSigningKey(key)
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    } catch (Exception e) {
	        throw new RuntimeException("Invalid JWT Token");
	    }
	}
	
}
