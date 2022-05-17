package com.cg.wallet.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.wallet.bean.CreateNewAddMoneyRequest;
import com.cg.wallet.bean.CreateNewDepositRequest;
import com.cg.wallet.bean.CreateNewSearchByDateRequest;
import com.cg.wallet.bean.CreateNewTransferRequest;
import com.cg.wallet.bean.CreateNewUserRequest;
import com.cg.wallet.bean.CreateNewWithdrawalRequest;
import com.cg.wallet.bean.Login;
import com.cg.wallet.entities.Customer;
import com.cg.wallet.entities.Transaction;
import com.cg.wallet.service.ApplicationService;

@RestController
public class WalletAppController {

	@Autowired
	ApplicationService appService;
	
	@RequestMapping(value="/test")
	public String test() {
		return "Hello World!!";
	}
	
	@RequestMapping(value="/findCustomer", method=RequestMethod.POST)
	public Optional<Customer> findFlights(@RequestParam("id") Integer id) {
		return appService.getCustomerById(id);
	}
	
	@RequestMapping(value="/allCustomers")
	public List<Customer> allCustomers() {
		return appService.getAllCustomers();
	}
	
	@RequestMapping(value="/allTransactions")
	public List<Transaction> allTransactions() {
		return appService.getAllTransactions();
	}
	
	@RequestMapping(value="/validateLogin", method=RequestMethod.POST)
	public Customer findFlights(@RequestBody Login login) {
		return appService.validateLogin(login.getUsername(), login.getPassword());
	}
	
	@RequestMapping(value="/addNewCustomer", method=RequestMethod.POST)
	public Integer addCustomer(@RequestBody CreateNewUserRequest request) {
		return appService.addNewUser(request);
	}
	
	@RequestMapping(value="/addMoneyToWallet", method=RequestMethod.POST)
	public Transaction addMoneyToWallet(@RequestBody CreateNewAddMoneyRequest request) {
		return appService.addMoneyToWallet(request.getAccountId(), request.getAmount());
	}
	
	@RequestMapping(value="/depositMoneyToSavings", method=RequestMethod.POST)
	public Transaction depositMoneyToSavings(@RequestBody CreateNewDepositRequest request) {
		return appService.depositMoney(request.getAccountId(), request.getAmount());
	}
	
	@RequestMapping(value="/withdrawMoney", method=RequestMethod.POST)
	public Transaction withdrawMoney(@RequestBody CreateNewWithdrawalRequest request) {
		return appService.withdrawMoney(request.getAccountId(), request.getAmount(), request.getSource());
	}
	
	@RequestMapping(value="/getLastTenTransactions", method=RequestMethod.GET)
	public List<Transaction> withdrawMoney(@RequestParam("id") Integer id) {
		return appService.getLastTenTransactions(id);
	}
	
	@RequestMapping(value="/searchForTransactionsAtDate", method=RequestMethod.POST)
	public List<Transaction> getTransactionsAtDate(@RequestBody CreateNewSearchByDateRequest request){
		return appService.findTransactionsAtDate(request.getAccountId(), request.getTransactionDate());
	}
	
	@RequestMapping(value="/transferMoney", method=RequestMethod.POST)
	public Transaction getTransactionsAtDate(@RequestBody CreateNewTransferRequest request){
		return appService.transferMoney(request);
	}
	
	
	
}
