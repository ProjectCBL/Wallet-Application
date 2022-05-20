import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';

@Component({
	selector: 'app-access',
	templateUrl: './access.component.html',
	styleUrls: ['./access.component.css']
})
export class AccessComponent implements OnInit {

	showComponent:boolean = false;

	constructor() { }

	ngOnInit(): void {
	}

	public toggleView(){
		this.showComponent = !this.showComponent;
	}
	
}
