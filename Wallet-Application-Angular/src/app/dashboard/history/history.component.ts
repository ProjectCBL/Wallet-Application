import { Component, OnInit } from '@angular/core';

@Component({
	selector: 'app-history',
	templateUrl: './history.component.html',
	styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

	historyView:string = "Last Ten";

	constructor() { }

	ngOnInit(): void {
	}

	public changeViewToLastTen(){
		this.historyView = "Last Ten"
	}

	public changeViewToSearchByDate(){
		this.historyView = "Search By Date"
	}

}
