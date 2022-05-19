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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Transaction")
public class Transaction {

	@Id
	@Column(name="transaction_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer transactionId;

	private String type;
	
	@Temporal(TemporalType.TIMESTAMP)
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
	
}
