import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from 'src/app/app.service';
import * as $ from 'jquery';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

	@Output() loginRedirect = new EventEmitter();
	@Output() successfulRegistration = new EventEmitter();

	errorMessage:string = '';
	displayError:boolean = false; 
	registrationSuccesful:boolean = false;

	constructor(private appService: AppService) { 

	}

	ngOnInit(): void {

		$("#fail-text").hide();

		$(`button`).on('click', function(){
			$("#fail-text").fadeIn('slow');
		});

	}

	public register(username:string, password:string, email:string, firstName:string, lastName: string){
		if(username != '' && password != '' && email != '' && firstName != '' && lastName != ''){

			this.appService.register(username, password, email, firstName, lastName).subscribe((response:any)=>{
				if(response != null && response){
					this.successfulRegistration.emit();
					this.loginRedirect.emit();
				}
			}, 
			(error:any)=>{
				this.displayError = true;
				this.errorMessage = error.error;
			});

		}
		else{

			this.displayError = true;
			this.errorMessage = "Make sure to fill out every field...";

		}
	}

}
