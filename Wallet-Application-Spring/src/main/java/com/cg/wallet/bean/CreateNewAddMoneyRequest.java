package com.cg.wallet.bean;

public class CreateNewAddMoneyRequest {

	private Integer accountId;
	private Double amount;
	
	public Integer getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
