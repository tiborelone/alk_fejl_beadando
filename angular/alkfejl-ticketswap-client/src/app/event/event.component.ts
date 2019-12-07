import { Component, OnInit } from '@angular/core';
import { Event } from '../event';
import { TicketSale } from '../ticket-sale';
/* import {EVENTS} from '../test-events'; */
import {EventService} from '../event.service';
import { stringify } from 'querystring';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  selectedEvent: Event;
  isSelected: Boolean;

  events: Event[];

  searchedEvent: String;
  filteredEvents: Array<Event>;

  constructor(private eventService: EventService) { }

  ngOnInit() {
    this.isSelected = false;
    this.getEvents();
  }

  getEvents(): void{
    this.eventService.getEvents()
      .subscribe(events => this.events = events);
  }

  add(name: string): void {
    name = name.trim();
    date: String;
    if (!name) { return; }
    this.eventService.addEvent({ name } as Event)
      .subscribe(hero => {
        this.events.push(hero);
      });
  }

  delete(event: Event): void {
    this.events = this.events.filter(h => h !== event);
    this.eventService.deleteEvent(event).subscribe();
  }

}
