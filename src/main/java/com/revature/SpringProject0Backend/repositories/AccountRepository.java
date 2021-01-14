package com.revature.SpringProject0Backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.SpringProject0Backend.models.Account;
import com.revature.SpringProject0Backend.models.AccountStatus;
import com.revature.SpringProject0Backend.models.User;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

	public List<Account> findAllByStatus(AccountStatus status);
	public List<Account> findAllByUsers_Id(int userId);
}
