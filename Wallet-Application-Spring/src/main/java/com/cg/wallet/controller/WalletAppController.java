package com.cg.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
@CrossOrigin(origins = "http://localhost:4200")
public class WalletAppController {

	@Autowired
	ApplicationService appService;
	
	//region Mapped routes used to test various inputs and outputs of the controller
	
	@RequestMapping(value="/findCustomer", method=RequestMethod.POST)
	public Customer findCustomer(@RequestParam("id") Integer id) {
		return appService.getCustomerById(id);
	}
	
	@RequestMapping(value="/findRecentTransaction", method=RequestMethod.POST)
	public Transaction findRecentTransaction(@RequestParam("id") Integer id) {
		return appService.getRecentTransaction(id);
	}
	
	@RequestMapping(value="/allCustomers")
	public List<Customer> allCustomers() {
		return appService.getAllCustomers();
	}
	
	@RequestMapping(value="/allTransactions")
	public List<Transaction> allTransactions() {
		return appService.getAllTransactions();
	}
	
	//endregion
	
	//region Wallet Application Routes
	
	@RequestMapping(value="/validateLogin", method=RequestMethod.POST)
	public Customer validateLogin(@RequestBody Login login) {
		return appService.validateLogin(login.getUsername(), login.getPassword());
	}
	
	@RequestMapping(value="/registerCustomer", method=RequestMethod.POST)
	public Boolean registerCustomer(@RequestBody CreateNewUserRequest request) {
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
	public List<Transaction> getLastTenTransactions(@RequestParam("id") Integer id) {
		return appService.getLastTenTransactions(id);
	}
	
	@RequestMapping(value="/searchForTransactionsAtDate", method=RequestMethod.POST)
	public List<Transaction> getTransactionsAtDate(@RequestBody CreateNewSearchByDateRequest request) {
		return appService.findTransactionsAtDate(request.getAccountId(), request.getTransactionDate());
	}
	
	@RequestMapping(value="/transferMoney", method=RequestMethod.POST)
	public Transaction getTransactionsAtDate(@RequestBody CreateNewTransferRequest request){
		return appService.transferMoney(request);
	}
	
	@ExceptionHandler(value= {UnexpectedRollbackException.class})
	public ResponseEntity<String> duplicateUsernameConstraint(Exception exception){
		exception.printStackTrace();
		return new ResponseEntity<String>("That username is already in use.", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<String> handleAllExceptions(Exception exception){
		exception.printStackTrace();
		return new ResponseEntity<String>("Sorry, it appears something went wrong on our end. Please try again later.", HttpStatus.BAD_REQUEST);
	}
	
	//endregion
	
}
