import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AppService } from 'src/app/app.service';
import { Transaction } from 'src/app/transaction';

@Component({
	selector: 'app-withdraw',
	templateUrl: './withdraw.component.html',
	styleUrls: ['./withdraw.component.css']
})
export class WithdrawComponent implements OnInit {

	@Input() withdrawalSource: string = '';
	@Output() newWithdrawalEvent = new EventEmitter<string>();

	value: number = 0;
	addError: boolean = false;
	errorMsg: string = "";

	showTransaction: boolean = false;
	dateOfTransaction: string = "";
	timeOfTransaction: string = "";
	transaction: Transaction = new Transaction();

	constructor(private appService: AppService) { }

	ngOnInit(): void {
		$("#fail-text").hide();

		$(`button`).on('click', function () {
			$("#fail-text").fadeIn('slow');
		});
	}

	public withdrawMoney() {

		const val = Number(this.value);
		const accountId = Number(localStorage.getItem("customerId"));
		const currentBalance = Number(localStorage.getItem(this.withdrawalSource));

		if (val > 0 && val < currentBalance) {

			const fixedValue = Number(this.value).toFixed(2);
			let newBalance = (Number(currentBalance) - Number(fixedValue));

			this.appService.withdrawMoney(accountId, Number(fixedValue), this.withdrawalSource).subscribe((response: any) => {
				localStorage.setItem(this.withdrawalSource, newBalance.toFixed(2));
				this.newWithdrawalEvent.emit(this.withdrawalSource);
				this.transferTransactionData(response);
				this.showTransaction = true;
				this.addError = false;
			},
			(error) => {
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

	transferTransactionData(transaction: any) {

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
