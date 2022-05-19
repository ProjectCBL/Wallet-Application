package com.cg.wallet.bean;

import lombok.Data;

@Data
public class CreateNewWithdrawalRequest {

	private Integer accountId;
	private Double amount;
	private String source;
	
}
