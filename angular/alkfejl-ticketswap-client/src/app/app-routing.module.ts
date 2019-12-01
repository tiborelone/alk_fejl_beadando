import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { BuyTicketComponent } from './buy-ticket/buy-ticket.component';
import { SellTicketComponent } from "./sell-ticket/sell-ticket.component";

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/home' },
  { path: 'home', component: HomeComponent },
  { path: 'buy', component: BuyTicketComponent },
  { path: 'sell', component: SellTicketComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }