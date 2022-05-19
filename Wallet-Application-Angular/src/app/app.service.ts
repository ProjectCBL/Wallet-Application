import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Customer } from "./customer";
import { Transaction } from "./transaction";


@Injectable({ providedIn: 'root' })
export class AppService {

    constructor(private httpClient: HttpClient) {

    }

    public login(username: string, password: string): Observable<Customer> {
        return this.httpClient.post<Customer>('http://localhost:8080/wallet-application/validateLogin', {
            "username":username,
            "password":password
        });
    }

    public register(username: string, password: string, email: string, firstname: string, lastname: string): Observable<number>{
        return this.httpClient.post<number>('http://localhost:8080/wallet-application/registerCustomer', {
            "userName":username,
            "password":password,
            "email":email,
            "firstName":firstname,
            "lastName":lastname
        });
    }

    /*public addMoneyToWallet(accountId:number, amount:number):Observable<Transaction>{

    }*/

}