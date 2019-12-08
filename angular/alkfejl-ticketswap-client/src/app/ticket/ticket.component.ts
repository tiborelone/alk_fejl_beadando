import { Component, OnInit } from '@angular/core';
import { Ticket } from '../ticket';

import {TicketService} from '../ticket.service';
import { stringify } from 'querystring';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {


  tickets: Ticket[];

  constructor(private ticketService: TicketService) { }

  ngOnInit() {
    this.getTickets();
  }

  getTickets(): void{
    this.ticketService.getTickets()
      .subscribe(tickets => this.tickets = tickets);
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.ticketService.addTicket({ name } as Ticket)
      .subscribe(ticket => {
        this.tickets.push(ticket);
      });
  }

  delete(ticket: Ticket): void {
    this.tickets = this.tickets.filter(h => h !== ticket);
    this.ticketService.deleteTicket(ticket).subscribe();
  }

}
