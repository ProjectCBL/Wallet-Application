import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AppService } from 'src/app/app.service';

@Component({
	selector: 'app-deposit',
	templateUrl: './deposit.component.html',
	styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {

	@Output() newSavingBalance = new EventEmitter<number>();

	value: number = 0;
	addError: boolean = false;
	errorMsg: string = "";

	showTransaction: boolean = false;
	type: String = ""
	dateOfTransaction: string = ""
	timeOfTransaction: string = ""
	walletBalanceBefore: number = 0;
	savingBalanceBefore: number = 0;
	walletBalanceAfter: number = 0;
	savingBalanceAfter: number = 0;
	source: string = '';
	destination: string = '';
	amount: number = 0;

	constructor(private appService: AppService) { }

	ngOnInit(): void {
		$("#fail-text").hide();

		$(`button`).on('click', function () {
			$("#fail-text").fadeIn('slow');
		});
	}

	public depositMoneyIntoSavings() {

		const val = Number(this.value);

		if (val > 0) {

			const accountId = Number(localStorage.getItem("customerId"));
			const currentSavingBalance = Number(localStorage.getItem("savingBalance"));

			const fixedValue = Number(this.value).toFixed(2);
			const newBalance = (Number(currentSavingBalance) + Number(fixedValue));

			this.appService.depositMoneyToSaving(accountId, Number(fixedValue)).subscribe((response: any) => {
				localStorage.setItem("savingBalance", newBalance.toString());
				this.newSavingBalance.emit();
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
			this.errorMsg = "Please only use positive numbers greater than zero...";
			this.showTransaction = false;
		}

	}

	transferTransactionData(transaction: any) {

		let date = new Date(transaction.dateOfTransaction);

		this.type = transaction.type;
		this.dateOfTransaction = date.toLocaleDateString();
		this.timeOfTransaction = date.toLocaleTimeString();
		this.walletBalanceBefore = transaction.walletBalanceBefore;
		this.savingBalanceBefore = transaction.savingBalanceBefore;
		this.walletBalanceAfter = transaction.walletBalanceAfter;
		this.savingBalanceAfter = transaction.savingBalanceAfter;
		this.source = transaction.source;
		this.destination = transaction.destination;
		this.amount = transaction.amount;

	}

}
