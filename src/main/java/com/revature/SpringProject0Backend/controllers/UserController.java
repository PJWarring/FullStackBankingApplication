package com.revature.SpringProject0Backend.controllers;

import static com.revature.SpringProject0Backend.util.ClientMessageUtil.DELETION_FAILED;
import static com.revature.SpringProject0Backend.util.ClientMessageUtil.SUCCESSFULLY_DELETED;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	UserService userService;
	
	/* Accepts a POST request with a user in its body and creates that user in the database
	 * Returns a 201 Created, if creation fails, returns a 400 Bad Request */
	@PostMapping(path="/create", consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity createUser(@RequestBody User user) {
		int newUserId = userService.createNewUser(user);
		if (newUserId != -1) {
			URI location = URI.create("/user/view/" + newUserId);
			return ResponseEntity.created(location).build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	/* Accepts a GET request and returns 200 OK with all users as json data */
	@GetMapping(path="/view/all", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<User>> viewAllUsers() {
		List<User> UserList = userService.retrieveAllUsers();
		return ResponseEntity.ok(UserList);
	}
	
	/* Accepts a GET request and returns 200 OK with the user at the specified id,
	 * if the requested resource is not found, returns a 404 Not Found */
	@GetMapping(path="/view/{userId}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> viewUser(@PathVariable("userId") int userId) {
		User user = userService.retrieveById(userId);
		if (user != null) return ResponseEntity.ok(user);
		else return ResponseEntity.notFound().build();
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
	
	/* Accepts a DELETE request with the id and deletes the id'd resource from the database*/
	@DeleteMapping(path="/delete/{userId}")
	public ResponseEntity<ClientMessage> deleteUser(@PathVariable("userId") int userId) {
		ClientMessage body = userService.deleteUser(userId) ? SUCCESSFULLY_DELETED : DELETION_FAILED;
		return ResponseEntity.ok(body);
	}
	
	/* Accepts a GET request and returns 200 OK with all users that own the specified accountId */
	@GetMapping(path="/view/account/{accountId}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<User>> viewUsersByUser(@PathVariable("accountId") int accountId) {
		List<User> UserList = userService.retrieveUsersByAccount(accountId);
		return ResponseEntity.ok(UserList);
	}
}
