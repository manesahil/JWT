package com.intern.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.intern.JwtUtil.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
public class HelloController {

	@Autowired
	private JwtUtil jwtUtil;

//	@GetMapping("/hello")
//	public String Hello(@RequestHeader("Auth") String token) {
//		if (token != null && token.startsWith("Bearer ")) {
//			token = token.substring(7);
//			if (jwtUtil.validateToken(token, token))
//				return "hello world";
//
//		}
////		return token;
//
//	}

	
	@GetMapping("/hello")
	public ResponseEntity<String> hello(@RequestHeader("Auth") String token) {
	    if (token != null && token.startsWith("Bearer ")) {
	        token = token.substring(7); // Remove "Bearer " prefix
	        String username = jwtUtil.extractUsername(token); // Extract username from the token
	        if (jwtUtil.validateToken(token, username)) {
	            return ResponseEntity.ok("hello world"); // Return response with status 200
	        }
	    }
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token"); // Return 401 for invalid token
	}

	
}
