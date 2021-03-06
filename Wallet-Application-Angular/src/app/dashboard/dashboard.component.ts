import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as $ from 'jquery';
import { AppService } from '../app.service';

@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

	dashboardView = "Basic";
	walletAction:number=0
	savingAction:number=0

	accountId:number = 0;
	firstname:string = ""
	walletBalance:number = 0;
	savingBalance:number = 0;

	constructor(private router: Router, private appService: AppService) { 
		this.accountId = localStorage.getItem("customerId") as unknown as number;
		this.firstname = localStorage.getItem("firstName") as unknown as string;
		this.walletBalance = localStorage.getItem("walletBalance") as unknown as number;
		this.savingBalance = localStorage.getItem("savingBalance") as unknown as number;
	}

	ngOnInit(): void {
	}

	public updateWalletBalance(){
		this.walletBalance = localStorage.getItem("walletBalance") as unknown as number;
	}

	public updateSavingBalance(){
		this.savingBalance = localStorage.getItem("savingBalance") as unknown as number;
	}

	public updateBalanceOnSource(event:string){
		(event == "walletBalance") ? this.updateWalletBalance() : this.updateSavingBalance();
	}

	updateBalanceOnTransfer(event:any){

		(event.source == "walletBalance") ? this.updateWalletBalance() : this.updateSavingBalance();
		(event.destination == "walletBalance") ? this.updateWalletBalance() : (event.destination == "savingBalance") 
			? this.updateSavingBalance() : ()=>{};

	}

	public changeViewToBasic(){
		this.dashboardView = "Basic";
	}

	public changeViewToHistory(){
		this.dashboardView = "History";
	}

	public logout(){
		localStorage.clear();
		this.router.navigate(['']);
	}

}
