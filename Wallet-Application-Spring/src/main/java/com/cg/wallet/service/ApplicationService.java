package com.cg.wallet.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.cg.wallet.bean.CreateNewTransferRequest;
import com.cg.wallet.bean.CreateNewUserRequest;
import com.cg.wallet.entities.Customer;
import com.cg.wallet.entities.Transaction;

public interface ApplicationService {
	public abstract Optional<Customer> getCustomerById(Integer id);
	public abstract Customer validateLogin(String userName, String password);
	public abstract List<Customer> getAllCustomers();
	public abstract List<Transaction> getAllTransactions();
	public abstract Integer addNewUser(CreateNewUserRequest request);
	public abstract Transaction addMoneyToWallet(Integer customerId, Double amount);
	public abstract Transaction withdrawMoney(Integer customerId, Double amount, String source);
	public abstract Transaction depositMoney(Integer customerId, Double amount);
	public abstract Transaction transferMoney(CreateNewTransferRequest request);
	public abstract List<Transaction> getLastTenTransactions(Integer customerId);
	public abstract List<Transaction> findTransactionsAtDate(Integer customerId, Date searchDate);
}
