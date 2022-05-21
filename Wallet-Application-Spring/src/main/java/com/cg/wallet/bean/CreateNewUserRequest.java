package com.cg.wallet.bean;

import lombok.Data;

@Data
public class CreateNewUserRequest {

	private String userName;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	
}
