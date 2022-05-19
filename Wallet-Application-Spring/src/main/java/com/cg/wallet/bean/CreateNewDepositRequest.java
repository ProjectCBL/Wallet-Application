package com.cg.wallet.bean;

import lombok.Data;

@Data
public class CreateNewDepositRequest {

	private Integer accountId;
	private Double amount;
	
}
