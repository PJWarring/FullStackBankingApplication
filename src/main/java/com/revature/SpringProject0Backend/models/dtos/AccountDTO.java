package com.revature.SpringProject0Backend.models.dtos;

import com.revature.SpringProject0Backend.models.Account;
import com.revature.SpringProject0Backend.models.AccountStatus;
import com.revature.SpringProject0Backend.models.AccountType;

public class AccountDTO {

	private int id;
	private double balance;
	private AccountStatus status;
	private AccountType type;
	private int ownerid;
	
	public AccountDTO() {}
	public AccountDTO(int id, double balance, AccountStatus status, AccountType type, int ownerid) {
		this.id = id;
		this.balance = balance;
		this.status = status;
		this.type = type;
		this.ownerid = ownerid;
	}
	public AccountDTO(Account account, int ownerid) {
		this.id = account.getId();
		this.balance = account.getBalance();
		this.status = account.getStatus();
		this.type = account.getType();
		this.ownerid = ownerid;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public AccountStatus getStatus() {
		return status;
	}
	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	public AccountType getType() {
		return type;
	}
	public void setType(AccountType type) {
		this.type = type;
	}
	public int getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}
	
}
