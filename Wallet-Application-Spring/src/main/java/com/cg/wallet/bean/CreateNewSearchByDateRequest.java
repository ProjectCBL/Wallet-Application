package com.cg.wallet.bean;

import lombok.Data;


@Data
public class CreateNewSearchByDateRequest {

	private Integer accountId;
	private String transactionDate;
	
}
