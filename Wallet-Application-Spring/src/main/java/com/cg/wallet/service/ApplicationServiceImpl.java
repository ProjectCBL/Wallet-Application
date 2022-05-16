package com.cg.wallet.service;

import java.util.Date;
import java.util.List;

import com.cg.wallet.entities.Transaction;

public class ApplicationServiceImpl implements ApplicationService{
	
	@Override
	public boolean validateLogin(String userName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Transaction addMoneyToWallet(Integer customerId, Double ammount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction withdrawMoney(Integer customerId, Double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction depositMoney(Integer customerId, Double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getLastTenTransactions(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction findTransaction(Date searchDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
