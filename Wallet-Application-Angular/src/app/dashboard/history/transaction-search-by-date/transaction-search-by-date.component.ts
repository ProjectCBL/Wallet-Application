import { Component, OnInit } from '@angular/core';
import { AppService } from 'src/app/app.service';
import { Transaction } from 'src/app/transaction';

@Component({
	selector: 'app-transaction-search-by-date',
	templateUrl: './transaction-search-by-date.component.html',
	styleUrls: ['./transaction-search-by-date.component.css']
})
export class TransactionSearchByDateComponent implements OnInit {

	selectedDate:string = ""

	showError:boolean = false;
	currentIndex:number = 0;
	dateOfTransaction:string = "";
	timeOfTransaction:string = "";
	transactions:Transaction[] = [];

	constructor(private appService: AppService) { }

	ngOnInit(): void {
		
	}

	getTransactionsAtDate(){

		this.transactions.splice(0);
		const accountId = localStorage.getItem('customerId') as unknown as number; 

		this.appService.searchForTransactionAtDate(accountId, this.selectedDate).subscribe((response:Transaction[])=>{
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
