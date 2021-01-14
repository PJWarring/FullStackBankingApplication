package com.revature.SpringProject0Backend.controllers;

import static com.revature.SpringProject0Backend.util.ClientMessageUtil.DELETION_FAILED;
import static com.revature.SpringProject0Backend.util.ClientMessageUtil.SUCCESSFULLY_DELETED;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.SpringProject0Backend.models.Account;
import com.revature.SpringProject0Backend.models.AccountStatus;
import com.revature.SpringProject0Backend.models.AccountTransaction;
import com.revature.SpringProject0Backend.models.User;
import com.revature.SpringProject0Backend.models.dtos.AccountTransactionDTO;
import com.revature.SpringProject0Backend.services.AccountService;
import com.revature.SpringProject0Backend.services.AccountTransactionService;
import com.revature.SpringProject0Backend.util.ClientMessage;

@RestController("accountController")
@RequestMapping("/account")
public class AccountController {

	/* TODO: Controller Methods
	 *update account (seperately deposit, withdraw, transfer, add new owner(s), remove one or more current owner, approve pending account, close current account)
	 *delete account (to be used when an account is closed or denied) - TODO: DBG: this cascading to users and deleting them when it shouldnt */
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountTransactionService accountTransactionService;
	
	/* Accepts a POST request with a account in its body and creates that account in the database
	 * Returns a 201 Created, if creation fails, returns a 400 Bad Request */
	@PostMapping(path="/create", consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity createAccount(@RequestBody Account account) {
		int newAccountId = accountService.createNewAccount(account);
		if (newAccountId != -1) {
			URI location = URI.create("/account/view/" + newAccountId);
			return ResponseEntity.created(location).build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	/* Accepts a GET request and returns 200 OK with all accounts as json data */
	@GetMapping(path="/view/all", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Account>> viewAllAccounts() {
		List<Account> AccountList = accountService.retrieveAllAccounts();
		return ResponseEntity.ok(AccountList);
	}
	
	/* Accepts a GET request and returns 200 OK with the account at the specified id,
	 * if the requested resource is not found, returns a 404 Not Found */
	@GetMapping(path="/view/{accountId}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Account> viewAccount(@PathVariable("accountId") int accountId) {
		Account account = accountService.retrieveById(accountId);
		if (account != null) return ResponseEntity.ok(account);
		else return ResponseEntity.notFound().build();
	}
	
	/* Accepts a GET request and returns 200 OK with all accounts with the specified status */
	@GetMapping(path="/view/status/{accountStatus}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Account>> viewAccountsByStatus(@PathVariable("accountStatus") AccountStatus accountStatus) {
		List<Account> AccountList = accountService.retrieveAccountsByStatus(accountStatus);
		return ResponseEntity.ok(AccountList);
	}
	
	/* Accepts a GET request and returns 200 OK with all accounts that belong to the specified userId */
	@GetMapping(path="/view/user/{userId}", produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Account>> viewAccountsByUser(@PathVariable("userId") int userId) {
		List<Account> AccountList = accountService.retrieveAccountsByUser(userId);
		return ResponseEntity.ok(AccountList);
	}
	
	/* Accepts a DELETE request with the id and deletes the id'd resource from the database*/
	@DeleteMapping(path="/delete/{accountId}")
	public ResponseEntity<ClientMessage> deleteAccount(@PathVariable("accountId") int accountId) {
		ClientMessage body = accountService.deleteAccount(accountId) ? SUCCESSFULLY_DELETED : DELETION_FAILED;
		return ResponseEntity.ok(body);
	}
	
	/* This update will be used for updating account information like status and account owners
	 * Depositing, withdrawing, and transferring will be handled in seperate methods
	 * Accepts a PUT request with the updated account and updates the account in the database
	 * Returns either a 200 OK or a 204 No Content depending on success */
	@PutMapping(path="/update/{accountId}", consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Account> updateItem(@RequestBody Account Account) {
		Account updatedAccount = accountService.updateAccount(Account);
		if (updatedAccount == null) {	// A null object denotes that the update had failed or done nothing.
			return ResponseEntity.noContent().build();	// 204 is the proper response for a PUT that changes nothing.
		} else {	// Success, return the account.
			return ResponseEntity.ok(updatedAccount);
		}
	}
	
	/* Accepts a POST request containing an AccountTransactionDTO which is used to update account balance
	 * Returns either a 200 OK or a 204 No Content depending on success */
	@PostMapping(path="/performTransaction", consumes= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Account>> performAccountTransaction(@RequestBody AccountTransactionDTO accountTransactionDTO) {
		AccountTransaction accountTransaction = accountTransactionService.convertFromDTO(accountTransactionDTO);
		List<Account> newAccounts = accountTransactionService.performTransaction(accountTransaction);
		boolean validTransaction;
		List<Account> updatedAccounts = new ArrayList<>();
		if (newAccounts.size() == 1) { //TODO: depositing, withdrawing, transferring need value checking (to prevent overdrafting and depositing negative values)
			Account updatedAccount = accountService.updateAccount(newAccounts.get(0));
			updatedAccounts.add(updatedAccount);
			validTransaction = updatedAccount == null;
		} else if (newAccounts.size() == 2) {
			for (int i = 0; i < newAccounts.size(); i++) { //TODO: if either of these accounts fail, it should rollback, currently it does not; however, it does state if it failed
				Account updatedAccount = accountService.updateAccount(newAccounts.get(0));
				if (updatedAccount == null) { validTransaction = false; break;}
				updatedAccounts.add(updatedAccount);
			}
			validTransaction = true;
		} else { //This should never happen, the size of newAccounts is <1 || >2 - this won't happen unless implementation is changed without updating this controller method
			validTransaction = false;
		}
		
		if (validTransaction) {	// A null object denotes that the update had failed or done nothing.
			return ResponseEntity.noContent().build();	// 204 is the proper response for a PUT that changes nothing.
		} else {	// Success, return the account.
			return ResponseEntity.ok(updatedAccounts);
		}
	}
}