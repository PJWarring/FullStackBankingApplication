package com.revature.SpringProject0Backend.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.SpringProject0Backend.models.User;
import com.revature.SpringProject0Backend.services.UserService;
import com.revature.SpringProject0Backend.util.ClientMessage;
import static com.revature.SpringProject0Backend.util.ClientMessageUtil.LOGIN_SUCCESSFUL;
import static com.revature.SpringProject0Backend.util.ClientMessageUtil.LOGIN_UNSUCCESSFUL;

@Controller("loginController")
public class LoginController {
	
	//TODO: This does not work
	
	/*
	
	@Autowired
	UserService userService;
	
	/* Accepts a POST request with a user in its body and determines if the username/password provided
	 * are valid credentials then returns the full user if they are valid *\/
	@PostMapping(path="/login", consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ClientMessage> login(HttpServletRequest request, @RequestBody String username, @RequestBody String password) {
		User loggedInUser = userService.retrieveByUsername(username);
		boolean validLogin = loggedInUser.getPassword().equals(password);
		if (validLogin) request.getSession().setAttribute("loggedInUser", loggedInUser);
		ClientMessage body = (validLogin) ? LOGIN_SUCCESSFUL : LOGIN_UNSUCCESSFUL;
		return ResponseEntity.ok(body);
	}
	*/
}
