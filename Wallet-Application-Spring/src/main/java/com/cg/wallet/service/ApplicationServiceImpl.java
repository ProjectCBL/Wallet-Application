package com.cg.wallet.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

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
	
	public Customer getCustomerById(Integer id) {
		return customerRepo.findById(id).get();
	}
	
	public Transaction getRecentTransaction(Integer id) {
		List<Transaction> transactions = transactionRepo.getAllTransactionsFromCustomer(id);
		return transactions.get(transactions.size()-1);
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
	public Customer validateLogin(String userName, String password) {
		return customerRepo.findByName(userName, password);
	}
	
	@Override
	@Transactional
	public Boolean addNewUser(CreateNewUserRequest request) {
		
		try {
			
			Customer customer = new Customer();
			customer.setUserName(request.getUserName());
			customer.setPassword(request.getPassword());
			customer.setEmail(request.getEmail());
			customer.setFirstName(request.getFirstName());
			customer.setLastName(request.getLastName());
			customer.setWalletBalance(0.0);
			customer.setSavingBalance(0.0);
			
			customerRepo.save(customer);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}

	@Override
	@Transactional
	public Transaction addMoneyToWallet(Integer customerId, Double amount) {
		
		Customer customer = customerRepo.findById(customerId).get();
		Transaction lastTransaction = getLastTransaction(customerId);
		
		try {
			
			Double newBalance = customer.getWalletBalance() + amount;
			
			Transaction transaction = new Transaction();
			transaction.setType("Add");
			transaction.setDateOfTransaction(new Date());
			transaction.setWalletBalanceBefore(customer.getWalletBalance());
			transaction.setSavingBalanceBefore(customer.getSavingBalance());
			transaction.setWalletBalanceAfter(newBalance);
			transaction.setSavingBalanceAfter(customer.getSavingBalance());
			transaction.setAmount(amount);
			transaction.setSource(customer.getUserName() + "-Add");
			transaction.setDestination(customer.getUserName() + "-Wallet");
			transaction.setCustomer(customer);
			
			customer.setWalletBalance(newBalance);
			customerRepo.save(customer);
			
			return transactionRepo.save(transaction);
			
		}
		catch(Exception e) {
			revertChanges(customer, lastTransaction);
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	@Transactional
	public Transaction depositMoney(Integer customerId, Double amount) {
		
		Customer customer = customerRepo.findById(customerId).get();
		Transaction lastTransaction = getLastTransaction(customerId);
		
		try {
		
			Double newBalance = customer.getSavingBalance() + amount;
			
			Transaction transaction = new Transaction();
			transaction.setType("Deposit");
			transaction.setDateOfTransaction(new Date());
			transaction.setWalletBalanceBefore(customer.getWalletBalance());
			transaction.setSavingBalanceBefore(customer.getSavingBalance());
			transaction.setWalletBalanceAfter(customer.getWalletBalance());
			transaction.setSavingBalanceAfter(newBalance);
			transaction.setAmount(amount);
			transaction.setSource(customer.getUserName() + "-Deposit");
			transaction.setDestination(customer.getUserName() + "-Saving");
			transaction.setCustomer(customer);
			
			customer.setSavingBalance(newBalance);
			customerRepo.save(customer);
			
			return transactionRepo.save(transaction);
			
		}
		catch(Exception e) {
			revertChanges(customer, lastTransaction);
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	@Override
	@Transactional
	public Transaction withdrawMoney(Integer customerId, Double amount, String source) {
		
		Customer customer = customerRepo.findById(customerId).get();
		Transaction lastTransaction = getLastTransaction(customerId);
		
		try {
		
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
			transaction.setSource(customer.getUserName() + "-Withdraw");
			transaction.setDestination(customer.getUserName() + "-" + source);
			transaction.setCustomer(customer);
			
			customerRepo.save(customer);
			
			return transactionRepo.save(transaction);
			
		}
		catch(Exception e) {
			revertChanges(customer, lastTransaction);
			e.printStackTrace();
		}
		
		return null;
			
	}
	
	@Override
	@Transactional
	public Transaction transferMoney(CreateNewTransferRequest request) {
		
		Customer customer = customerRepo.findById(request.getAccountId()).get();
		Transaction lastTransaction = getLastTransaction(request.getAccountId());
		
		try {
			
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
		catch(Exception e) {
			revertChanges(customer, lastTransaction);
			e.printStackTrace();
		}
		
		return null;
			
	}

	@Override
	public List<Transaction> getLastTenTransactions(Integer customerId) {
		try {
			List<Transaction> transactions = transactionRepo.getAllTransactionsFromCustomer(customerId);
			List<Transaction> lastTenTransactions = transactions.subList(
					transactions.size()-10, 
					transactions.size());
			return lastTenTransactions;
		}
		catch(EntityNotFoundException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<Transaction> findTransactionsAtDate(Integer customerId, Date searchDate) {
		try {
			return transactionRepo.getTransactionsAtDate(customerId, searchDate);
		}
		catch(EntityNotFoundException e) {
			return Collections.emptyList();
		}
	}
	
	// Although rollback is applied with the @Transactional annotation, this only triggers on the entity
	// the persist operation fails on.  To prevent effects occurring in either databases this function reverts
	// all changes prior to the transaction.
	private void revertChanges(Customer unalteredCustomer, Transaction lastTransaction) throws EntityNotFoundException{
		Transaction recentTransaction = getLastTransaction(unalteredCustomer.getCustomerId());
		customerRepo.save(unalteredCustomer);
		if (lastTransaction.getTransactionId() != recentTransaction.getTransactionId())
			transactionRepo.delete(recentTransaction);
	}
	
	private Transaction getLastTransaction(Integer customerId) {
		List<Transaction> transactions = transactionRepo.getAllTransactionsFromCustomer(customerId);
		return transactions.get(transactions.size()-1);
	}
	
}
