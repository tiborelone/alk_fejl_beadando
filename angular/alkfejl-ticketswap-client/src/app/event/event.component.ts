import { Component, OnInit } from '@angular/core';
import { Event } from '../event';

import {EventService} from '../event.service';
import { stringify } from 'querystring';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  events: Event[];

  constructor(private eventService: EventService) { }

  ngOnInit() {
    this.getEvents();
  }

  getEvents(): void{
    this.eventService.getEvents()
      .subscribe(events => this.events = events);
  }

  add(name: string,date: string): void {
    name = name.trim();
    if (!name) { return; }
    this.eventService.addEvent({ name,date } as Event)
      .subscribe(event => {
        this.events.push(event);
      });
  }

  delete(event: Event): void {
    this.events = this.events.filter(h => h !== event);
    this.eventService.deleteEvent(event).subscribe();
  }

}
