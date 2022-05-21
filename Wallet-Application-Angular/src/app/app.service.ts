import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { TransferState } from "@angular/platform-browser";
import { Observable } from "rxjs";
import { Customer } from "./customer";
import { Transaction } from "./transaction";


@Injectable({ providedIn: 'root' })
export class AppService {

    constructor(private httpClient: HttpClient) {

    }

    public validateLogin(username: string, password: string): Observable<Customer> {
        return this.httpClient.post<Customer>('http://localhost:8080/wallet-application/validateLogin', {
            "username": username,
            "password": password
        });
    }

    public register(username: string, password: string, email: string, firstname: string, lastname: string): Observable<number> {
        return this.httpClient.post<number>('http://localhost:8080/wallet-application/registerCustomer', {
            "userName": username,
            "password": password,
            "email": email,
            "firstName": firstname,
            "lastName": lastname
        });
    }

    public addMoneyToWallet(accountId: number, amount: number): Observable<Transaction> {
        return this.httpClient.post<Transaction>('http://localhost:8080/wallet-application/addMoneyToWallet', {
            "accountId":accountId,
            "amount":amount 
        });
    }

    public depositMoneyToSaving(accountId: number, amount: number): Observable<Transaction>{
        return this.httpClient.post<Transaction>('http://localhost:8080/wallet-application/depositMoneyToSavings', {
            "accountId":accountId,
            "amount":amount
        });
    }

    public withdrawMoney(accountId: number, amount: number, source: string): Observable<Transaction>{
        return this.httpClient.post<Transaction>('http://localhost:8080/wallet-application/withdrawMoney', {
            "accountId":accountId,
            "amount":amount,
            "source":source
        });
    }

    public transferMoney(accountId:number, source:string, destination:string, amount:number): Observable<Transaction>{
        return this.httpClient.post<Transaction>('http://localhost:8080/wallet-application/transferMoney',{
            "accountId":accountId,
            "source":source,
            "destination":destination,
            "amount":amount
        });
    }

    public getLastTenTransactions(accountId: number): Observable<Transaction[]>{
        return this.httpClient.get<Transaction[]>('http://localhost:8080/wallet-application/getLastTenTransactions?id=' + accountId);
    }

    public searchForTransactionAtDate(accountId: number, transactionDate: string): Observable<Transaction[]>{
        return this.httpClient.post<Transaction[]>('http://localhost:8080/wallet-application/searchForTransactionsAtDate', {
            "accountId":accountId,
            "transactionDate":transactionDate
        });
    }

}