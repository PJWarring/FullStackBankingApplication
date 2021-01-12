package com.revature.SpringProject0Backend.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="account")
@Getter @Setter @NoArgsConstructor
public class Account {

	@Id
	@Column(name="id", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="balance", nullable=false)
	private double balance;
	
	@Column(name="status", nullable=false)
	private AccountStatus status;
	
	@Column(name="type", nullable=false)
	private AccountType type;
	
	@ManyToMany(mappedBy="accounts")
	private List<User> users;
	
	public Account(int id, double balance, AccountStatus status, AccountType type) {
		this.id = id;
		this.balance = balance;
		this.status = status;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", status=" + status + ", type=" + type + "]";
	}
}
