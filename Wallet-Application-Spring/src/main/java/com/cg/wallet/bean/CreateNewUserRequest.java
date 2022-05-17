package com.cg.wallet.bean;

public class CreateNewUserRequest {

	private String userName;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	
	public CreateNewUserRequest() {
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String toString() {
		return String.format("%s, %s, %s, %s, %s", this.getUserName(), this.getPassword(), 
				this.getEmail(), this.getFirstName(), this.getLastName());
	}
	
}
