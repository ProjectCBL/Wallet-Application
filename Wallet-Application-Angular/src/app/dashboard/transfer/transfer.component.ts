import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AppService } from 'src/app/app.service';
import { Transaction } from 'src/app/transaction';

@Component({
	selector: 'app-transfer',
	templateUrl: './transfer.component.html',
	styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit {

	@Input() transferSource: string = "";
	@Input() transferSourceHtmlValue = ""
	@Output() newTransferEvent = new EventEmitter<any>();

	selectedDestination: string = "";
	routingName: string = "";
	routingNumber: string = "";
	value: number = 0;
	selectedSource: string = "";
	addError: boolean = false;
	errorMsg: string = "";

	showTransaction:boolean = false;
	dateOfTransaction:string = "";
	timeOfTransaction:string = "";
	transaction:Transaction = new Transaction();

	constructor(private appService: AppService) { }

	ngOnInit(): void {
		$("#fail-text").hide();

		$(`button`).on('click', function () {
			$("#fail-text").fadeIn('slow');
		});
	}

	public transferMoney() {

		let destinationBalance = null;
		const val = Number(this.value);
		const accountId = Number(localStorage.getItem("customerId"));
		const currentBalance = Number(localStorage.getItem(this.transferSource));
		const serviceDestination = (this.selectedDestination != "custom") ? this.selectedDestination : this.routingName + "-" + this.routingNumber;

		if (val > 0 && val < currentBalance) {

			const fixedValue = Number(this.value).toFixed(2);
			let newBalance = (Number(currentBalance) - Number(fixedValue));

			this.appService.transferMoney(accountId, this.transferSource, serviceDestination, Number(fixedValue)).subscribe((response:any) =>{
				localStorage.setItem(this.transferSource, newBalance.toFixed(2));

				if(this.selectedDestination != "custom"){
					destinationBalance = Number(localStorage.getItem(this.selectedDestination)) + Number(fixedValue);
					localStorage.setItem(this.selectedDestination, destinationBalance.toFixed(2));
				}

				this.newTransferEvent.emit({source:this.transferSource, destination:serviceDestination});
				this.transferTransactionData(response);
				this.showTransaction = true;
				this.addError = false;
			},
			(error)=>{
				this.addError = true;
				this.errorMsg = error.error;
				this.showTransaction = false;
			});

		}
		else {
			this.addError = true;
			this.errorMsg = "Please only use positive numbers and numbers equal or less than your balance...";
			this.showTransaction = false;
		}

	}

	transferTransactionData(transaction:any){

		let date = new Date(transaction.dateOfTransaction);

		this.transaction.type = transaction.type;
        this.dateOfTransaction = date.toLocaleDateString();
		this.timeOfTransaction = date.toLocaleTimeString();
        this.transaction.walletBalanceBefore = transaction.walletBalanceBefore;
        this.transaction.savingBalanceBefore = transaction.savingBalanceBefore;
        this.transaction.walletBalanceAfter = transaction.walletBalanceAfter;
        this.transaction.savingBalanceAfter = transaction.savingBalanceAfter;
        this.transaction.source = transaction.source;
        this.transaction.destination = transaction.destination;
        this.transaction.amount = transaction.amount;

	}

}
