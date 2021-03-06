import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Event } from './event';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {

  id: number = 100;

  createDb() {
    const events = [
      {id: 0, name: 'zero', date:'000000'},
      {id: 1, name: 'first', date:'000001'},
      {id: 2, name: 'second', date:'000010'},
      {id: 4, name: 'truth', date:'010101'},
    ];

    const users = [
      {id: 50, username: 'lajos97', password:'000000', name:'lajcsi', selling:null, rating: 0},
      {id: 10, username: 'sandor1', password:'000001', name:'sanyi', selling:null,rating: 0},
      {id: 20, username: 'imre007', password:'000010', name:'imre', selling:null,rating: 0},
      {id: 30, username: 'istvan42', password:'010101', name:'istvan', selling:null,rating: 0},
    ];

    const tickets = [
      {id: 80, name: 'teszt000',event: 0, barcode: 121112,seller: 50,price: 123},
      {id: 81, name: 'teszt2',event: 2, barcode: 121112,seller: 10, price: 31},
      {id: 83, name: 'teszt3',event: 4, barcode: 121112,seller: 50,price: 1232323},
      {id: 84, name: 'teszt4',event: 2, barcode: 121112,seller: 10, price: 1423},
      {id: 85, name: 'teszt5',event: 0, barcode: 121112,seller: 50,price: 1223},
      {id: 86, name: 'teszt6',event: 0, barcode: 121112,seller: 10, price: 1023},
      {id: 87, name: 'teszt33',event: 4, barcode: 121112,seller: 50,price: 1232323},
    ];


    this.id = this.id * (users.length + events.length) + 1;

    return {events,users,tickets};
  }


  // Overrides the genId method to ensure that an event always has an id.
  // If the events array is empty,
  // the method below returns the initial number (11).
  // if the events array is not empty, the method below returns the highest
  // event id + 1.
  genId(): number {
    this.id++;
    return this.id;
  }

} 