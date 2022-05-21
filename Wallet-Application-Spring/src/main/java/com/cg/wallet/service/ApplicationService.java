package com.cg.wallet.service;

import java.util.List;

import com.cg.wallet.bean.CreateNewTransferRequest;
import com.cg.wallet.bean.CreateNewUserRequest;
import com.cg.wallet.entities.Customer;
import com.cg.wallet.entities.Transaction;

public interface ApplicationService {
	
	// Wallet Application Services
	public abstract Customer validateLogin(String userName, String password);
	public abstract Boolean addNewUser(CreateNewUserRequest request);
	public abstract Transaction addMoneyToWallet(Integer customerId, Double amount);
	public abstract Transaction withdrawMoney(Integer customerId, Double amount, String source);
	public abstract Transaction depositMoney(Integer customerId, Double amount);
	public abstract Transaction transferMoney(CreateNewTransferRequest request);
	public abstract List<Transaction> getLastTenTransactions(Integer customerId);
	public abstract List<Transaction> findTransactionsAtDate(Integer customerId, String searchDate);
	
}
