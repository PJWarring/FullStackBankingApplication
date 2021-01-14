package com.revature.SpringProject0Backend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.SpringProject0Backend.models.Account;
import com.revature.SpringProject0Backend.models.AccountTransaction;
import com.revature.SpringProject0Backend.models.AccountTransactionMethod;
import com.revature.SpringProject0Backend.models.dtos.AccountTransactionDTO;

@Service("accountTransactionService")
public class AccountTransactionService {

	@Autowired
	AccountService accountService;
	
	public AccountTransaction convertFromDTO(AccountTransactionDTO accountTransactionDTO) {
		return new AccountTransaction(accountService.retrieveById(accountTransactionDTO.getSrcId()), accountService.retrieveById(accountTransactionDTO.getDestId()), accountTransactionDTO.getMethod(), accountTransactionDTO.getAmount());
	}
	
	public AccountTransactionDTO convertToDTO(AccountTransaction accountTransaction) {
		return new AccountTransactionDTO(accountTransaction);
	}
	
	public List<Account> performTransaction(AccountTransaction accountTransaction) {
		//TODO: log the transaction object here
		List<Account> newAccounts = new ArrayList<>();
		if (accountTransaction.getMethod().equals(AccountTransactionMethod.DEPOSIT)) {
			Account newAccount = accountTransaction.getDestAccount();
			newAccount.setBalance(newAccount.getBalance() + accountTransaction.getAmount());
			newAccounts.add(newAccount);
			return newAccounts;
		} else if (accountTransaction.getMethod().equals(AccountTransactionMethod.WITHDRAW)) {
			Account newAccount = accountTransaction.getSrcAccount();
			newAccount.setBalance(newAccount.getBalance() - accountTransaction.getAmount());
			newAccounts.add(newAccount);
			return newAccounts;
		} else if (accountTransaction.getMethod().equals(AccountTransactionMethod.TRANSFER)) {
			Account srcAccount = accountTransaction.getSrcAccount();
			Account destAccount = accountTransaction.getDestAccount();
			srcAccount.setBalance(srcAccount.getBalance() - accountTransaction.getAmount());
			destAccount.setBalance(destAccount.getBalance() + accountTransaction.getAmount());
			newAccounts.add(srcAccount);
			newAccounts.add(destAccount);
			return newAccounts;
		} else {	//This should never happen (the method would have to not exist in AccountTransactionMethod, or not yet be implemented)
			return null;
		}
	}
}
