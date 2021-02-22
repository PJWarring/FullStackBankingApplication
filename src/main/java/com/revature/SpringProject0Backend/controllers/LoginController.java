package com.revature.SpringProject0Backend.controllers;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.SpringProject0Backend.models.User;
import com.revature.SpringProject0Backend.services.UserService;
import com.revature.SpringProject0Backend.util.ClientMessage;

@Controller("loginController")
@CrossOrigin(origins = "*") //TODO: see user controller
public class LoginController {
	
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	UserService userService;
	
	/* Accepts a POST request with a user in its body and determines if the username/password provided
	 * are valid credentials then returns the full user if they are valid */
	@PostMapping(path="/login", consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<User> login(@RequestBody User user) {
		User loggedInUser = userService.retrieveByUsername(user.getUsername());
		boolean validLogin;
		if (loggedInUser == null) validLogin = false;
		else {
			validLogin = loggedInUser.getPassword().equals(user.getPassword());
			loggedInUser.setPassword(""); //this hides the password from the user (for security)
		}
		if(validLogin) return ResponseEntity.ok(loggedInUser);
		else return ResponseEntity.ok(new User());
	}
}