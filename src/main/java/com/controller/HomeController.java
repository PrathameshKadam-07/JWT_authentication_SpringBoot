package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

	@GetMapping("/public")
	public String getpublicApi() {
		return "Public API";
	}
	
	@GetMapping("/private")
	public String getprivateApi() {
		return "Private API";
	}
}
