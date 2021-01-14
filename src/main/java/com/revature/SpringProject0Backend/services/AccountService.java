package com.revature.SpringProject0Backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.SpringProject0Backend.models.Account;
import com.revature.SpringProject0Backend.models.AccountStatus;
import com.revature.SpringProject0Backend.repositories.AccountRepository;

@Service("accountService")
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	public int createNewAccount(Account account) {
		try {
			return accountRepository.save(account).getId();
		} catch(Exception e) {
			return -1;
		}
	}
	
	public List<Account> retrieveAllAccounts() {
		return accountRepository.findAll();
	}
	
	public List<Account> retrieveAccountsByStatus(AccountStatus status) {
		return accountRepository.findAllByStatus(status);
	}
	
	public List<Account> retrieveAccountsByUser(int userId) {
		return accountRepository.findAllByUsers_Id(userId);
	}
	
	public Account retrieveById(int id) {
		try {
			return accountRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean deleteAccount(int id) {
		try {
			accountRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Account updateAccount(Account account) {
		return accountRepository.save(account);
	}
}
