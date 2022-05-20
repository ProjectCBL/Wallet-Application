import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-transaction-card',
  templateUrl: './transaction-card.component.html',
  styleUrls: ['./transaction-card.component.css']
})
export class TransactionCardComponent implements OnInit {

  @Input() type:String = ""
	@Input() dateOfTransaction:string = ""
	@Input() timeOfTransaction:string = ""
	@Input() walletBalanceBefore:number = 0;
	@Input() savingBalanceBefore:number = 0;
	@Input() walletBalanceAfter:number = 0;
	@Input() savingBalanceAfter:number = 0;
	@Input() source:string = '';
	@Input() destination:string = '';
	@Input() amount:number = 0;

  constructor() { }

  ngOnInit(): void {
  }

}
