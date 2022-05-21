import { Component, OnInit } from '@angular/core';
import { Transaction } from 'src/app/transaction';
import * as $ from 'jquery';
import { AppService } from 'src/app/app.service';

@Component({
	selector: 'app-recent-transactions',
	templateUrl: './recent-transactions.component.html',
	styleUrls: ['./recent-transactions.component.css']
})
export class RecentTransactionsComponent implements OnInit {

	showError:boolean = false;
	currentIndex:number = 0;
	dateOfTransaction:string = "";
	timeOfTransaction:string = "";
	transactions:Transaction[] = [];

	constructor(private appService: AppService) { }

	ngOnInit(): void {
		this.getRecentTransactions();
	}

	getRecentTransactions(){

		const accountId = localStorage.getItem('customerId') as unknown as number; 

		this.appService.getLastTenTransactions(accountId).subscribe((response:Transaction[])=>{
			const len = response.length;
			if(response != null && len != 0){
				this.transactions.push(...response);
				this.currentIndex = 0;

				let date = new Date(String(this.transactions[this.currentIndex].dateOfTransaction));
				this.dateOfTransaction = date.toLocaleDateString();
				this.timeOfTransaction = date.toLocaleTimeString();

				this.showError = false;
			}
			else if(len == 0){
				this.transactions.push(...response);
				return;
			}
		},
		(error)=>{
			this.showError = true;
		});

	}

	changeActive(newActiveIndex:number){
		this.currentIndex = newActiveIndex;
		let date = new Date(String(this.transactions[this.currentIndex].dateOfTransaction));
		this.dateOfTransaction = date.toLocaleDateString();
		this.timeOfTransaction = date.toLocaleTimeString();
	}

}
