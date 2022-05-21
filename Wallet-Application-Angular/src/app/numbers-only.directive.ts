// This directive was original found at https://stackoverflow.com/questions/57151147/only-allow-positive-numbers-with-only-one-decimal-in-angular-6
import {
	Directive,
	ElementRef,
	HostListener,
	Input,
	Output,
	EventEmitter,
} from '@angular/core';

import { NgControl } from '@angular/forms';

@Directive({
	selector: 'input[numbersOnly]',
})

export class NumberOnlyDirective {

	@Output() valueChange = new EventEmitter();

	text: string = '';
	private regex: RegExp = new RegExp(/^-?[0-9]+(\.[0-9]*){0,1}$/g);
	private specialKeys: Array<string> = ['Backspace', 'Tab'];

	constructor(private elem: ElementRef) { }

	@HostListener('keypress', ['$event'])
	onInputChange(event: KeyboardEvent) {

		if (this.specialKeys.indexOf(event.key) !== -1) {
			return;
		}

		const current: string = this.elem.nativeElement.value;
		const next: string = current.concat(event.key);

		if (next.includes('.')) {
			if (this.text == next) {
				event.preventDefault();
			}
			this.text = next;
		}
		if ((next && !String(next).match(this.regex))) {
			event.preventDefault();
		}

	}
}