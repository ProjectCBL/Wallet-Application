export class Transaction{

    public transactionId:number = 0;
    public type:String = "";
    public dateOfTransaction:String = "";
    public walletBalanceBefore:number = 0.0;
    public savingBalanceBefore:number = 0.0;
    public walletBalanceAfter:number = 0.0;
    public savingBalanceAfter:number = 0.0;
    public amount:number = 0.0;
    public source:string = "";
    public destination:string = "";
    public customerId:number = 0;

}