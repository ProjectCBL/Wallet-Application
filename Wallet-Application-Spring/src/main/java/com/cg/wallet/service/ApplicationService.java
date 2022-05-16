package com.cg.wallet.service;

import java.util.Date;
import java.util.List;

import com.cg.wallet.entities.Transaction;

public interface ApplicationService {
	public abstract boolean validateLogin(String userName, String password);
	public abstract Transaction addMoneyToWallet(Integer customerId, Double ammount);
	public abstract Transaction withdrawMoney(Integer customerId, Double amount);
	public abstract Transaction depositMoney(Integer customerId, Double amount);
	public abstract List<Transaction> getLastTenTransactions(Integer customerId);
	public abstract Transaction findTransaction(Date searchDate);
}
