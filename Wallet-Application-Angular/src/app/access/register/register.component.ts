import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from 'src/app/app.service';
import * as $ from 'jquery';

@Component({
	selector: 'app-register',
	templateUrl: './register.component.html',
	styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

	errorMessage:string = '';
	displayError:boolean = false; 
	registrationSuccesful:boolean = false;

	constructor(private router: Router, private appService: AppService) { 

	}

	ngOnInit(): void {

		$("#fail-text").hide();

		$(`button`).on('click', function(){
			$("#fail-text").fadeIn('fast');
		});

	}

	public register(username:string, password:string, email:string, firstName:string, lastName: string){
		this.appService.register(username, password, email, firstName, lastName).subscribe((response:any)=>{
			if(response instanceof Boolean && response){
				console.log("something");
				(<any>$('.toast')).toast('show');
			}
			else{
				this.displayError = true;
				this.errorMessage = "Something happened, please try to register again later...";
			}
		}, 
		(error:any)=>{
			this.displayError = true;
			this.errorMessage = error.error;
		});
	}

}
