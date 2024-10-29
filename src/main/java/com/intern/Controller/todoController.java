package com.intern.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intern.dto.todolisttrequest;
import com.intern.model.Todolist;
import com.intern.service.todoservice;

import java.util.Collections;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/todo")
public class todoController {

	@Autowired
	private todoservice Todoservice;

	@PostMapping("/add")
	public ResponseEntity<String> addtodo(@RequestBody Todolist Todolist) {
		try {
			Todoservice.addtodo(Todolist);
			return ResponseEntity.ok("Data Added");
		} catch (Error e) {
			throw new RuntimeErrorException(e, "data not been added");
		}
	}

	@PostMapping("/viewbyid/{user_id}")
	public ResponseEntity<List<todolisttrequest>> viewbyid(@PathVariable Long user_id) {
		try {
			Todoservice.viewbyid(user_id);
			return ResponseEntity.ok(Todoservice.viewbyid(user_id));
		} catch (Error e) {
			throw new RuntimeErrorException(e, "ID NOT FOUND");
		}
	}

//	@GetMapping("/viewall/{user_id}")
//	public ResponseEntity<List<todolisttrequest>> getTodos(@PathVariable Long user_id) {
//		  List<todolisttrequest> todos = (List<todolisttrequest>) Todoservice.viewbyid(user_id);
//		 
//          return ResponseEntity.ok(todos);
//	}

	@GetMapping("/viewall/{user_id}")
	public ResponseEntity<?> getTodos(@PathVariable Long user_id) {
		List<todolisttrequest> todos = Todoservice.findallbyrole(user_id);

		if (todos.isEmpty()) {
			String message = "You do not have the authorization to access all to-do items.";
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Collections.singletonMap("message", message));
		}

		return ResponseEntity.ok(todos);
	}

	@PostMapping("/update/{User_id}/{id}")
	public ResponseEntity<ResponseEntity<List<todolisttrequest>>> Update(@PathVariable Long User_id,@PathVariable Long id,
			@RequestBody todolisttrequest Todolisttrequest) {
		Todoservice.Update(User_id,id,Todolisttrequest);
		return ResponseEntity.ok(viewbyid(User_id));
	}

	@PostMapping("/deletebyid/{user_id}/{id}")
	public ResponseEntity<String> deletebyid(@PathVariable Long user_id,@PathVariable Long id) {
		Todoservice.deletebyid(user_id,id);

		return ResponseEntity.ok("DATA DELETED");
	}

}
