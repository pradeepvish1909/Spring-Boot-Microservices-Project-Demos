package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@Controller
@RequestMapping("/api")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
//	@PostMapping("/login")
//	public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO){
//		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
//		
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		
//		return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
//	}
	
	@GetMapping("/login")
    public String login(){
        return "login";
    }
	
	@GetMapping("/welcome")
	public String greeting() {
		return "welcome";
	}
}
