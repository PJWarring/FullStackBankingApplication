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
	
	//this will perform the account transaction its provided
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
	
	//this performs logic checking to determine if a transaction is valid
	public boolean verifyTransaction(AccountTransaction accountTransaction) {
		if (accountTransaction.getMethod() == AccountTransactionMethod.TRANSFER && accountService.retrieveById(accountTransaction.getDestAccount().getId()) == null) return false; //the method is transfer and the destination account does not exist
		if (accountTransaction.getAmount() <= 0) return false; //negative or empty amount
		if (accountTransaction.getMethod().equals(AccountTransactionMethod.DEPOSIT) || accountTransaction.getMethod().equals(AccountTransactionMethod.TRANSFER)) {
			//there is no condition not already covered where depositing fails
			return true;
		}
		if (accountTransaction.getMethod().equals(AccountTransactionMethod.WITHDRAW) || accountTransaction.getMethod().equals(AccountTransactionMethod.TRANSFER)) {
			if (accountTransaction.getSrcAccount().getBalance() - accountTransaction.getAmount() < 0) {
				return false; //amount to be withdrawn exceeds the balance of the account
			}
		}
		return true;
	}
}
