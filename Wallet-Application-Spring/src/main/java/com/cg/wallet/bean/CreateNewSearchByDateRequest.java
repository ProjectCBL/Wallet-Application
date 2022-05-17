package com.cg.wallet.bean;

import java.util.Date;

public class CreateNewSearchByDateRequest {

	private Integer accountId;
	private Date transactionDate;
	
	public Integer getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
}
