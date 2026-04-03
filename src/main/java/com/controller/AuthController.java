package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.AuthBean;
import com.util.JWTHelper;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	AuthenticationManager authenticatemanager;
	
	@Autowired
	JWTHelper jwthelper;
	
	@GetMapping("/authenticate")
	public String getJwtToken(@RequestBody AuthBean auth) {
		try {
			UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword());
			
			authenticatemanager.authenticate(authenticate);
			
			return jwthelper.getJwtToken(auth.getUsername());
			}
		catch (Exception e) {
			throw e;
		}
	}
	

}
