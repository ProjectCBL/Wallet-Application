package com.cg.wallet.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Integer customerId;
	
	@Column(name="user_name", nullable=false, unique=true)
	private String userName;
	
	private String password;
	
	private String email;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Column(name="wallet_balance")
	private Double walletBalance;
	
	@Column(name="saving_balance")
	private Double savingBalance;
	
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Transaction> transactions;
	
	public Customer() {
		
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(Double walletBalance) {
		this.walletBalance = walletBalance;
	}

	public Double getSavingBalance() {
		return savingBalance;
	}

	public void setSavingBalance(Double savingBalance) {
		this.savingBalance = savingBalance;
	}
	
	@SuppressWarnings("unused")
	private void setTransactions(List<Transaction> transactions){
		this.transactions = transactions;
	}
	
	@SuppressWarnings("unused")
	private List<Transaction> getTransactions(){
		return this.transactions;
	}
	
	public String toString() {
		return String.format("[%s]%s : %s", this.getCustomerId(), this.getUserName(), this.getPassword());
	}
	
	
}
