package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.AuthBean;

@RestController
@RequestMapping("/api")
public class AuthController {

	@GetMapping("/authenticate")
	public String getJwtToken(@RequestBody AuthBean auth) {
		
		return "JWT TOKEN";
	}
}
