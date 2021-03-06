import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { AccessComponent } from './access/access.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './access/login/login.component';
import { RegisterComponent } from './access/register/register.component';
import { AddComponent } from './dashboard/add/add.component';
import { DepositComponent } from './dashboard/deposit/deposit.component';
import { WithdrawComponent } from './dashboard/withdraw/withdraw.component';
import { TransferComponent } from './dashboard/transfer/transfer.component';
import { HistoryComponent } from './dashboard/history/history.component';
import { RecentTransactionsComponent } from './dashboard/history/recent-transactions/recent-transactions.component';
import { TransactionSearchByDateComponent } from './dashboard/history/transaction-search-by-date/transaction-search-by-date.component';

import { NumberOnlyDirective } from './numbers-only.directive';
import { TransactionCardComponent } from './transaction-card/transaction-card.component';

const routes: Routes = [
	{ path: '', component: AccessComponent },
	{ path: 'dashboard', component: DashboardComponent }
]

@NgModule({
	declarations: [
		AppComponent,
		AccessComponent,
		DashboardComponent,
		LoginComponent,
		RegisterComponent,
		AddComponent,
		DepositComponent,
		WithdrawComponent,
		TransferComponent,
		HistoryComponent,
		RecentTransactionsComponent,
		TransactionSearchByDateComponent,
		NumberOnlyDirective,
  		TransactionCardComponent
	],
	imports: [
		BrowserModule,
		HttpClientModule,
		FormsModule,
		[RouterModule.forRoot(routes)]
	],
	exports: [RouterModule],
	providers: [],
	bootstrap: [AppComponent],
})
export class AppModule { }
