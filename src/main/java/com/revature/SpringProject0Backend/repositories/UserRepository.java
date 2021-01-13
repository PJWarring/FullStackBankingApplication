package com.revature.SpringProject0Backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.SpringProject0Backend.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
}
