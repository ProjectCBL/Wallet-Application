import { ChangeDetectorRef, Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AppService } from 'src/app/app.service';
import { Transaction } from 'src/app/transaction';

@Component({
	selector: 'app-add',
	templateUrl: './add.component.html',
	styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

	@Output() newWalletBalance = new EventEmitter<number>();

	value:number = 0;
	addError:boolean = false;
	errorMsg:string = "";

	showTransaction:boolean = false;
	dateOfTransaction:string = "";
	timeOfTransaction:string = "";
	transaction:Transaction = new Transaction();

	constructor(private appService:AppService) { }

	ngOnInit(): void {
		$("#fail-text").hide();

		$(`button`).on('click', function(){
			$("#fail-text").fadeIn('slow');
		});
	}

	public addMoneyToWallet(){

		const val = Number(this.value);

		if(val > 0){

			const accountId = Number(localStorage.getItem("customerId"));
			const currentWalletBalance = Number(localStorage.getItem("walletBalance"));

			const fixedValue = Number(this.value).toFixed(2);
			const newBalance = (Number(currentWalletBalance) + Number(fixedValue));

			this.appService.addMoneyToWallet(accountId, Number(fixedValue)).subscribe((response:any)=>{
				localStorage.setItem("walletBalance", newBalance.toFixed(2));
				this.newWalletBalance.emit();
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
		else{
			this.addError = true;
			this.errorMsg = "Please only use positive numbers greater than zero...";
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

