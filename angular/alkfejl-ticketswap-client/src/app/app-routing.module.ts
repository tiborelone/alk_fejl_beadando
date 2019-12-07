import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { BuyTicketComponent } from './buy-ticket/buy-ticket.component';
import { SellTicketComponent } from './sell-ticket/sell-ticket.component';

import { EventComponent } from './event/event.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EventDetailComponent }  from './event-detail/event-detail.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/dashboard' },
  { path: 'home', component: HomeComponent },
  { path: 'buy', component: BuyTicketComponent },
  { path: 'sell', component: SellTicketComponent },
  { path: 'event', component: EventComponent},
  { path: 'dashboard', component: DashboardComponent},
  { path: 'eventdetail/:id', component: EventDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }