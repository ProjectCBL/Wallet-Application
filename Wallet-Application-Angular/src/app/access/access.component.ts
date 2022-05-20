import { Component, OnInit } from '@angular/core';

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

	public alertOnSuccessRegistration(){
		alert("Account Successfully Created!");
	}
	
}
