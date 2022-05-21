package com.cg.wallet.bean;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;


@Data
public class CreateNewSearchByDateRequest {

	private Integer accountId;
	private String transactionDate;
	
}
