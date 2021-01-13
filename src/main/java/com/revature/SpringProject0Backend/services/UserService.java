package com.revature.SpringProject0Backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.SpringProject0Backend.models.User;
import com.revature.SpringProject0Backend.repositories.UserRepository;

@Service("userService")
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	//Saves the provided user then returns the generated id
	public int createNewUser(User user) {
		return userRepository.save(user).getId();
	}

	//Returns all users
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
}
