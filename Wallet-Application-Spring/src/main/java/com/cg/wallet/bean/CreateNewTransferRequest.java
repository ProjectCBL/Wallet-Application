package com.cg.wallet.bean;

import lombok.Data;

@Data
public class CreateNewTransferRequest {

	private Integer accountId;
	private String source;
	private String destination;
	private Double amount;
	
}
