package com.revature.SpringProject0Backend.controllers;

import static com.revature.SpringProject0Backend.util.ClientMessageUtil.DELETION_FAILED;
import static com.revature.SpringProject0Backend.util.ClientMessageUtil.SUCCESSFULLY_DELETED;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	//TODO: this crashes if the user does not exist
	/* Accepts a GET request and returns 200 OK with the user at the specified id */
	@GetMapping(path="/view/{userId}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> viewUser(@PathVariable("userId") int userId) {
		User user = userService.retrieveById(userId);
		return ResponseEntity.ok(user);
	}
	
	/* Accepts a PUT request with the updated user and updates the user in the database
	 * Returns either a 200 OK or a 204 No Content depending on success */
	@PutMapping(path="/update/{userId}", consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> updateItem(@RequestBody User User) {
		User updatedUser = userService.updateUser(User);
		if (updatedUser == null) {	// A null object denotes that the update had failed or done nothing.
			return ResponseEntity.noContent().build();	// 204 is the proper response for a PUT that changes nothing.
		} else {	// Success, return the user.
			return ResponseEntity.ok(updatedUser);
		}
	}
	
	//TODO: if you try to delete a user that doesnt exist (possibly an uncaught exception) - solution possibly related to fixing service condition
	/* Accepts a DELETE request with the id and deletes the id from the database*/
	@DeleteMapping(path="/delete/{userId}")
	public ResponseEntity<ClientMessage> deleteUser(@PathVariable("userId") int userId) {
		ClientMessage body = userService.deleteUser(userId) ? SUCCESSFULLY_DELETED : DELETION_FAILED;
		return ResponseEntity.ok(body);
	}
}
