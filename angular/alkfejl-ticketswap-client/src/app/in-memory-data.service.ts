import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Event } from './event';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const events = [
      {id: 0, name: 'zero', date:'000000', ticketSale:null,ticketWanted:null},
      {id: 1, name: 'first', date:'000001', ticketSale:null,ticketWanted:null},
      {id: 2, name: 'second', date:'000010', ticketSale:null,ticketWanted:null},
      {id: 42, name: 'truth', date:'010101', ticketSale:null,ticketWanted:null},
    ];
    return {events};
  }

  // Overrides the genId method to ensure that a event always has an id.
  // If the events array is empty,
  // the method below returns the initial number (11).
  // if the events array is not empty, the method below returns the highest
  // event id + 1.
  genId(events: Event[]): number {
    return events.length > 0 ? Math.max(...events.map(event => event.id)) + 1 : 11;
  }
} 