package com.cg.wallet.bean;

import java.util.Date;
import lombok.Data;


@Data
public class CreateNewSearchByDateRequest {

	private Integer accountId;
	private Date transactionDate;
	
}
