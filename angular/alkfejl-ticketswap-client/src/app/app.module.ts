import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { EventComponent } from './event/event.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EventDetailComponent } from './event-detail/event-detail.component';
import { UserDetailComponent } from './user-detail/user-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { TicketComponent } from './ticket/ticket.component';
import { TicketDetailComponent } from './ticket-detail/ticket-detail.component';


import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule, MatInputModule, MatFormFieldModule, MatToolbarModule, MatIconModule, MatButtonModule, MatDividerModule } from '@angular/material';

import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService }  from './in-memory-data.service';
import { EventSearchComponent } from './event-search/event-search.component';
<<<<<<< HEAD
import { LoginFormComponent } from './login-form/login-form.component';
=======
import { TicketSearchComponent } from './ticket-search/ticket-search.component';
>>>>>>> origin/master


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    UserComponent,
    EventComponent,
    EventDetailComponent,
    MessagesComponent,
    DashboardComponent,
    UserDetailComponent,
    EventSearchComponent,
    TicketComponent,
    TicketDetailComponent,
<<<<<<< HEAD
    LoginFormComponent
=======
    TicketSearchComponent
>>>>>>> origin/master
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatSliderModule,
    MatInputModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatDividerModule,
    ReactiveFormsModule,

    // The HttpClientInMemoryWebApiModule module intercepts HTTP requests
    // and returns simulated server responses.
    // Remove it when a real server is ready to receive requests.
    HttpClientInMemoryWebApiModule.forRoot(
      InMemoryDataService, { dataEncapsulation: false }
    ),

    BrowserAnimationsModule 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
