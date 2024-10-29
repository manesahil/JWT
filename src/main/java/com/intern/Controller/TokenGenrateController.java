package com.intern.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



//just for checking 



import com.intern.JwtUtil.JwtUtil;
import com.intern.dto.usersrequest;

@RestController
public class TokenGenrateController {

	
	
	private final JwtUtil jwtUtil;
	
	TokenGenrateController(JwtUtil jwtUtil)
	{
		this.jwtUtil=jwtUtil;
	}
	
	@PostMapping("/login")
	public String login(@RequestBody usersrequest user) {
	
		if ("Sahil".equals(user.getUsername()) && "Sahil@123".equals(user.getPassword())) {
			return jwtUtil.generateToken(user.getUsername());
		} else {
			throw new RuntimeException("Invalid credentials");
		}
	}
}
