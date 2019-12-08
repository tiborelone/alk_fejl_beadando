import { Component, OnInit,Input } from '@angular/core';

import { Observable, Subject } from 'rxjs';

import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';

import { Ticket } from '../ticket';
import { TicketService } from '../ticket.service';
import { EventDetailComponent } from '../event-detail/event-detail.component';

@Component({
  selector: 'app-ticket-search',
  templateUrl: './ticket-search.component.html',
  styleUrls: [ './ticket-search.component.css' ]
})
export class TicketSearchComponent implements OnInit {
  tickets$: Observable<Ticket[]>;
  private searchTerms = new Subject<string>();

  @Input() n: number;
  

  constructor(private ticketService: TicketService) {
    
  }

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(this.n.toString());
  }

  ngOnInit(): void {
    this.tickets$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.ticketService.searchTickets(term)),
    );
  }
}