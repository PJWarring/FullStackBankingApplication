package com.revature.SpringProject0Backend.controllers;

import static com.revature.SpringProject0Backend.util.ClientMessageUtil.CREATION_FAILED;
import static com.revature.SpringProject0Backend.util.ClientMessageUtil.SUCCESSFULLY_CREATED;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.SpringProject0Backend.models.User;
import com.revature.SpringProject0Backend.services.UserService;
import com.revature.SpringProject0Backend.util.ClientMessage;

@RestController("userController")
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	/* Accepts a POST request with a user in its body and creates that user in the database
	 * Returns a 201 Created*/
	@PostMapping(path="/create", consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ClientMessage> createUser(@RequestBody User user) {
		URI location = URI.create("/user/view/" + userService.createNewUser(user));
		return ResponseEntity.created(location).build();
	}
	
	/* Accepts a GET request and returns 200 OK with all users as json data */
	@GetMapping(path="/view/all", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<User>> viewAllUsers() {
		List<User> UserList = userService.retrieveAllUsers();
		return ResponseEntity.ok(UserList);
	}
}
