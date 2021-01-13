package com.revature.SpringProject0Backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.SpringProject0Backend.models.User;
import com.revature.SpringProject0Backend.services.UserService;
import com.revature.SpringProject0Backend.util.ClientMessage;

@RestController("loginController")
public class LoginController {
	/* TODO
	@Autowired
	UserService userService;
	
	/* Accepts a POST request with a user in its body and determines if the username/password provided
	 * are valid credentials then returns the full user if they are valid *\/
	@PostMapping(path="/login", consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> login(@RequestBody String username, @RequestBody String password) {
		User loggedInUser = userService.retrieveByUsername(username);
		if (loggedInUser.getPassword() == password)
			return ResponseEntity.ok(loggedInUser);
	}
	*/
}
