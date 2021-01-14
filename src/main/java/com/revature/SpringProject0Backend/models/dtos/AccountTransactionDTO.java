package com.revature.SpringProject0Backend.models.dtos;

import com.revature.SpringProject0Backend.models.AccountTransaction;
import com.revature.SpringProject0Backend.models.AccountTransactionMethod;

public class AccountTransactionDTO {
	
	private int srcId;
	private int destId;
	private AccountTransactionMethod method;
	private double amount;
	
	public AccountTransactionDTO() {}
	public AccountTransactionDTO(int srcId, int destId, AccountTransactionMethod method, double amount) {
		this.srcId = srcId;
		this.destId = destId;
		this.method = method;
		this.amount = amount;
	}
	public AccountTransactionDTO(AccountTransaction accountTransaction) {
		this.srcId = accountTransaction.getSrcAccount().getId();
		this.destId = accountTransaction.getDestAccount().getId();
		this.method = accountTransaction.getMethod();
		this.amount = accountTransaction.getAmount();
	}
	
	public int getSrcId() {
		return srcId;
	}
	public void setSrcId(int srcId) {
		this.srcId = srcId;
	}
	public int getDestId() {
		return destId;
	}
	public void setDestId(int destId) {
		this.destId = destId;
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
		return "AccountTransactionDTO [srcId=" + srcId + ", destId=" + destId + ", method=" + method + ", amount="
				+ amount + "]";
	}
}
