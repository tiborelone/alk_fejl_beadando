import { Component, OnInit, Input } from '@angular/core';
import {Ticket} from '../ticket';

import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { TicketService }  from '../ticket.service';


@Component({
  selector: 'app-ticket-detail',
  templateUrl: './ticket-detail.component.html',
  styleUrls: ['./ticket-detail.component.css']
})
export class TicketDetailComponent implements OnInit {

  @Input() ticket: Ticket;

  constructor(private route: ActivatedRoute,
    private ticketService: TicketService,
    private location: Location) { }

  ngOnInit():void{
    this.getTicket();
  }

  getTicket(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.ticketService.getTicket(id)
      .subscribe(ticket => this.ticket = ticket);
  }

  goBack(): void {
    this.location.back();
  }

   save(): void {
    this.ticketService.updateTicket(this.ticket)
      .subscribe(() => this.goBack());
  } 


}
