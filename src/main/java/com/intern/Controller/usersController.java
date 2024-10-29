package com.intern.Controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intern.JwtUtil.JwtUtil;
import com.intern.model.users;
import com.intern.service.usersservice;

//import org.springframework.web.bind.annotation.PathSVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
public class usersController {

	@Autowired
	private usersservice Usersservice;
	
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/add")
	public ResponseEntity<String> useradd(@RequestBody users Users) {
		try {
			Usersservice.useradd(Users);

			return ResponseEntity.ok("User account Created");
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@PostMapping("/viewall")
	public ResponseEntity<?> viewall() {
		try {
			Usersservice.viewall();
			return ResponseEntity.ok(Usersservice.viewall());
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		}
	}

	@PostMapping("/delete/{user_id}")
	public ResponseEntity<String> delete(@PathVariable Long user_id) {
		try {
			Usersservice.deleteuser(user_id);
			return ResponseEntity.ok("Data deleted successfully.");
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		}
	}

	@PostMapping("/viewbyid/{user_id}")
	public ResponseEntity<?> viewbyid(@PathVariable Long user_id) {
		try {
			Usersservice.viewbyid(user_id);

			return ResponseEntity.ok(Usersservice.viewbyid(user_id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("user not found");
		}

	}
	
	@GetMapping("/authenticate")
	public String authenticate(@RequestParam String username,@RequestParam String password) {
		if (Usersservice.ValidateUser(username, password)) {
			return jwtUtil.generateToken(username);
		}
		
		return "INVAILD CREDENTAILS";
		
	}
	

}
