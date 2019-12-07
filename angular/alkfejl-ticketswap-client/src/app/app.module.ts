import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { BuyTicketComponent } from './buy-ticket/buy-ticket.component';
import { SellTicketComponent } from './sell-ticket/sell-ticket.component';
import { UserComponent } from './user/user.component';
import { EventComponent } from './event/event.component';
import { FormsModule } from '@angular/forms';
import { EventDetailComponent } from './event-detail/event-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { DashboardComponent } from './dashboard/dashboard.component';

import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService }  from './in-memory-data.service';
import { EventSearchComponent } from './event-search/event-search.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    BuyTicketComponent,
    SellTicketComponent,
    UserComponent,
    EventComponent,
    EventDetailComponent,
    MessagesComponent,
    DashboardComponent,
    EventSearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,

    // The HttpClientInMemoryWebApiModule module intercepts HTTP requests
    // and returns simulated server responses.
    // Remove it when a real server is ready to receive requests.
    HttpClientInMemoryWebApiModule.forRoot(
      InMemoryDataService, { dataEncapsulation: false }
    ) 
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
