package com.example.demo.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WelcomeController {
	
	@GetMapping("/greeting")
	public String greeting(Authentication authentication) {
		String userName = authentication.getName();
		
		return "Spring Security In-memory Authentication Example - Welcome "+userName;
	}
	
	@GetMapping("/hello")
	public String getHello() {
		return "Hello World";
	}
	
	@GetMapping("/world")
	public String getWorld() {
		return "Hello World2.0";
	}
}
