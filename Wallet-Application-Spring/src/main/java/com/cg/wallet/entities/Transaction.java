package com.cg.wallet.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="transaction_id")
	private Integer transactionId;
	private String type;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_of_transaction")
	private Date dateOfTransaction;
	
	@Column(name="wallet_balance_before")
	private Double walletBalanceBefore;
	
	@Column(name="saving_balance_before")
	private Double savingBalanceBefore;
	
	@Column(name="wallet_balance_after")
	private Double walletBalanceAfter;
	
	@Column(name="saving_balance_after")
	private Double savingBalanceAfter;
	
	private Double amount;
	private String source;
	private String destination;
	
	@ManyToOne(cascade= CascadeType.ALL, targetEntity = Customer.class)
	@JoinColumn(name = "customer_id_fk", nullable=false, referencedColumnName = "customer_id")
	private Customer customer;
	
	public Transaction() {
		
	}
	
	public Integer getTransactionId() {
		return this.transactionId;
	}
	
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Date getDateOfTransaction() {
		return this.dateOfTransaction;
	}
	
	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public Double getWalletBalanceBefore() {
		return walletBalanceBefore;
	}

	public void setWalletBalanceBefore(Double walletBalanceBefore) {
		this.walletBalanceBefore = walletBalanceBefore;
	}

	public Double getSavingBalanceBefore() {
		return savingBalanceBefore;
	}

	public void setSavingBalanceBefore(Double savingBalanceBefore) {
		this.savingBalanceBefore = savingBalanceBefore;
	}

	public Double getWalletBalanceAfter() {
		return walletBalanceAfter;
	}

	public void setWalletBalanceAfter(Double walletBalanceAfter) {
		this.walletBalanceAfter = walletBalanceAfter;
	}

	public Double getSavingBalanceAfter() {
		return savingBalanceAfter;
	}

	public void setSavingBalanceAfter(Double savingBalanceAfter) {
		this.savingBalanceAfter = savingBalanceAfter;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
