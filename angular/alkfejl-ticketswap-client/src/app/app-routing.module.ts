import { NgModule, Component } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';

import { EventComponent } from './event/event.component';
import { UserComponent } from './user/user.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EventDetailComponent }  from './event-detail/event-detail.component';
import { UserDetailComponent} from './user-detail/user-detail.component';
import { TicketComponent } from "./ticket/ticket.component";
import { TicketDetailComponent } from "./ticket-detail/ticket-detail.component";
import { AuthGuard } from './auth.guard';
import { LoginFormComponent } from './login-form/login-form.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/dashboard' },
  { path: 'home', component: HomeComponent },
  { path: 'event', component: EventComponent},
  { path: 'user', component: UserComponent},
  { path: 'dashboard', component: DashboardComponent},
  { path: 'eventdetail/:id', component: EventDetailComponent},
  { path: 'userdetail/:id', component: UserDetailComponent}, 
  { path: 'ticketdetail/:id', component: TicketDetailComponent}, 
  { path: 'ticket', component: TicketComponent},
  {
    path: 'login',
    component: LoginFormComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }