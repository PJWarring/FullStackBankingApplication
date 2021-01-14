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
		try {
			return userRepository.save(user).getId();
		} catch(Exception e) {
			return -1;
		}
	}

	//Returns all users
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	//Returns user at specified id
	public User retrieveById(int id) {
		try {
			return userRepository.findById(id).get();
		} catch(Exception e) {
			return null;
		}
	}
	
	//Returns user with specified username
	public User retrieveByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public List<User> retrieveUsersByAccount(int accountId) {
		return userRepository.findAllByAccounts_Id(accountId);
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	public boolean deleteUser(int userId) {
		try {
			userRepository.deleteById(userId);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
