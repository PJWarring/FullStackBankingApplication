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
	
	//Returns user at specified id
	public User retrieveById(int id) {
		return userRepository.findById(id).get();
	}
	
	//Returns user with specified username
	public User retrieveByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	public boolean deleteUser(int userId) {
		userRepository.deleteById(userId);
		return true; //TODO: this will always return true, either remove the boolean or change it so its possible to fail
	}
}
