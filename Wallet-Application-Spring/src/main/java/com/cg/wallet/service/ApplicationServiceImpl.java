package com.cg.wallet.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.wallet.bean.CreateNewTransferRequest;
import com.cg.wallet.bean.CreateNewUserRequest;
import com.cg.wallet.entities.Customer;
import com.cg.wallet.entities.Transaction;
import com.cg.wallet.repository.CustomerRepository;
import com.cg.wallet.repository.TransactionRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService{
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private TransactionRepository transactionRepo;
	
	public Optional<Customer> getCustomerById(Integer id) {
		return customerRepo.findById(id);
	}
	
	@Override
	public Customer validateLogin(String userName, String password) {
		return customerRepo.findByName(userName, password);
	}
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}
	
	@Override
	public List<Transaction> getAllTransactions() {
		return transactionRepo.findAll();
	}

	@Override
	@Transactional
	public Integer addNewUser(CreateNewUserRequest request) {
		
		Customer customer = new Customer();
		customer.setUserName(request.getUserName());
		customer.setPassword(request.getPassword());
		customer.setEmail(request.getEmail());
		customer.setFirstName(request.getFirstName());
		customer.setLastName(request.getLastName());
		customer.setWalletBalance(0.0);
		customer.setSavingBalance(0.0);
		
		return customerRepo.save(customer).getCustomerId();
	}

	@Override
	@Transactional
	public Transaction addMoneyToWallet(Integer customerId, Double amount) {
		
		Customer customer = customerRepo.findById(customerId).get();
		Double newBalance = customer.getWalletBalance() + amount;
		
		Transaction transaction = new Transaction();
		transaction.setType("Add");
		transaction.setDateOfTransaction(new Date());
		transaction.setWalletBalanceBefore(customer.getWalletBalance());
		transaction.setSavingBalanceBefore(customer.getSavingBalance());
		transaction.setWalletBalanceAfter(newBalance);
		transaction.setSavingBalanceAfter(customer.getSavingBalance());
		transaction.setAmount(amount);
		transaction.setSource("Admin-Add");
		transaction.setDestination("Admin-Wallet");
		transaction.setCustomer(customer);
		
		System.out.println(transaction.getCustomer());
		
		customer.setWalletBalance(newBalance);
		customerRepo.save(customer);
		
		return transactionRepo.save(transaction);
	}

	@Override
	@Transactional
	public Transaction depositMoney(Integer customerId, Double amount) {
		
		Customer customer = customerRepo.findById(customerId).get();
		Double newBalance = customer.getSavingBalance() + amount;
		
		Transaction transaction = new Transaction();
		transaction.setType("Deposit");
		transaction.setDateOfTransaction(new Date());
		transaction.setWalletBalanceBefore(customer.getWalletBalance());
		transaction.setSavingBalanceBefore(customer.getSavingBalance());
		transaction.setWalletBalanceAfter(customer.getWalletBalance());
		transaction.setSavingBalanceAfter(newBalance);
		transaction.setAmount(amount);
		transaction.setSource("Admin-Deposit");
		transaction.setDestination("Admin-Saving");
		transaction.setCustomer(customer);
		
		customer.setSavingBalance(newBalance);
		customerRepo.save(customer);
		
		return transactionRepo.save(transaction);
	}
	
	@Override
	@Transactional
	public Transaction withdrawMoney(Integer customerId, Double amount, String source) {
		
		Customer customer = customerRepo.findById(customerId).get();
		Double newBalance = null;
		
		Transaction transaction = new Transaction();
		transaction.setType("Deposit");
		transaction.setDateOfTransaction(new Date());
		transaction.setWalletBalanceBefore(customer.getWalletBalance());
		transaction.setSavingBalanceBefore(customer.getSavingBalance());
		
		if(source.equals("Saving")) {
			newBalance = customer.getSavingBalance() - amount;
			transaction.setWalletBalanceAfter(customer.getWalletBalance());
			transaction.setSavingBalanceAfter(newBalance);
			customer.setSavingBalance(newBalance);
		}
		else {
			newBalance = customer.getWalletBalance() - amount;
			transaction.setWalletBalanceAfter(newBalance);
			transaction.setSavingBalanceAfter(customer.getSavingBalance());
			customer.setWalletBalance(newBalance);
		}
		
		transaction.setAmount(amount);
		transaction.setSource("Admin-Withdraw");
		transaction.setDestination("Admin-" + source);
		transaction.setCustomer(customer);
		
		customerRepo.save(customer);
		
		return transactionRepo.save(transaction);
	}
	
	@Override
	@Transactional
	public Transaction transferMoney(CreateNewTransferRequest request) {
		
		Customer customer = customerRepo.findById(request.getAccountId()).get();
		Double newBalance = null;
		
		Transaction transaction = new Transaction();
		transaction.setType("Transfer");
		transaction.setDateOfTransaction(new Date());
		transaction.setWalletBalanceBefore(customer.getWalletBalance());
		transaction.setSavingBalanceBefore(customer.getSavingBalance());
		transaction.setAmount(request.getAmount());
		
		// Source
		if(request.getSource().equals("Saving")) {
			newBalance = customer.getSavingBalance() - request.getAmount();
			transaction.setSavingBalanceAfter(newBalance);
			transaction.setWalletBalanceAfter(customer.getWalletBalance());
			customer.setSavingBalance(newBalance);
		}
		else{
			newBalance = customer.getWalletBalance() - request.getAmount();
			transaction.setWalletBalanceAfter(newBalance);
			transaction.setSavingBalanceAfter(customer.getSavingBalance());
			customer.setWalletBalance(newBalance);
		}
		
		
		// Destination
		if(request.getDestination().equals("Saving")) {
			newBalance = customer.getSavingBalance() + request.getAmount();
			transaction.setSavingBalanceAfter(newBalance);
			customer.setSavingBalance(newBalance);
		}
		else if(request.getDestination().equals("Wallet")){
			newBalance = customer.getWalletBalance() + request.getAmount();
			transaction.setWalletBalanceAfter(newBalance);
			customer.setWalletBalance(newBalance);
		}
		
		transaction.setSource(request.getSource());
		transaction.setDestination(request.getDestination());
		transaction.setCustomer(customer);
		
		customerRepo.save(customer);
		
		return transactionRepo.save(transaction);
	}

	@Override
	@Transactional
	public List<Transaction> getLastTenTransactions(Integer customerId) {
		List<Transaction> transactions = transactionRepo.getAllTransactionsFromCustomer(customerId);
		List<Transaction> lastTenTransactions = transactions.subList(
				transactions.size()-10, 
				transactions.size());
		return lastTenTransactions;
	}

	@Override
	@Transactional
	public List<Transaction> findTransactionsAtDate(Integer customerId, Date searchDate) {
		return transactionRepo.getTransactionsAtDate(customerId, searchDate);
	}
	
}
