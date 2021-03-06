import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from 'src/app/app.service';
import { Customer } from 'src/app/customer';
import * as $ from 'jquery';

@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	
	@Output() registerRedirect = new EventEmitter();
	
	customer: Customer;
	loginFailed: boolean = true;

	constructor(private router: Router, private appService: AppService) {
		localStorage.clear();
		this.customer = new Customer();
	}

	ngOnInit(): void {

		$("#fail-text").hide();

		$(`button`).on('click', function(){
			$("#fail-text").fadeIn('slow');
		});

	}

	public validateLogin(username:string, password:string){

		this.appService.validateLogin(username, password).subscribe((response:any)=>{
			if (response != null){
				this.customer = response;
				localStorage.setItem("customerId", this.customer.customerId.toString());
				localStorage.setItem("userName", this.customer.userName.toString());
				localStorage.setItem("email", this.customer.email.toString());
				localStorage.setItem("firstName", this.customer.firstName.toString());
				localStorage.setItem("lastName", this.customer.lastName.toString());
				localStorage.setItem("walletBalance", this.customer.walletBalance.toString());
				localStorage.setItem("savingBalance", this.customer.savingBalance.toString());
				this.router.navigate(['/dashboard']);
			}
			else{
				this.loginFailed = false;
			}
		});

	}

}
