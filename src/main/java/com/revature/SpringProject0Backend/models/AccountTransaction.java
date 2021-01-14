package com.revature.SpringProject0Backend.models;

public class AccountTransaction {
	
	private Account srcAccount;
	private Account destAccount;
	private AccountTransactionMethod method;
	private double amount;
	
	public AccountTransaction() {}
	public AccountTransaction(Account srcAccount, Account destAccount, AccountTransactionMethod method, double amount) {
		this.srcAccount = srcAccount;
		this.destAccount = destAccount;
		this.method = method;
		this.amount = amount;
	}
	
	public Account getSrcAccount() {
		return srcAccount;
	}
	public void setSrcAccount(Account srcAccount) {
		this.srcAccount = srcAccount;
	}
	public Account getDestAccount() {
		return destAccount;
	}
	public void setDestAccount(Account destAccount) {
		this.destAccount = destAccount;
	}
	public AccountTransactionMethod getMethod() {
		return method;
	}
	public void setMethod(AccountTransactionMethod method) {
		this.method = method;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "AccountTransaction [srcAccount=" + srcAccount + ", destAccount=" + destAccount + ", method=" + method
				+ ", amount=" + amount + "]";
	}
}
