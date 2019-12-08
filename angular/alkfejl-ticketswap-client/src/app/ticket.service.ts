import { Injectable } from '@angular/core';
import {Ticket} from './ticket';
import { MessageService } from './message.service';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { HttpClient, HttpHeaders } from '@angular/common/http';

 

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private ticketsUrl = 'api/tickets'; 

  constructor( private http: HttpClient, private messageService: MessageService) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }; 

   getTickets(): Observable<Ticket[]> {
    /* this.messageService.add('TicketService: fetched tickets'); */
    return this.http.get<Ticket[]>(this.ticketsUrl)
      .pipe(
        tap(_ => this.log('fetched tickets')),
        catchError(this.handleError<Ticket[]>('getTickets', []))
      );
  }


  private log(message: string) {
    this.messageService.add(`TicketService: ${message}`);
  }

  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);
  
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

   getTicket(id: number): Observable<Ticket> {
    const url = `${this.ticketsUrl}/${id}`;
    return this.http.get<Ticket>(url).pipe(
      tap(_ => this.log(`fetched ticket id=${id}`)),
      catchError(this.handleError<Ticket>(`getTicket id=${id}`))
    );
  }

  updateTicket (ticket: Ticket): Observable<any> {
    return this.http.put(this.ticketsUrl, ticket, this.httpOptions).pipe(
      tap(_ => this.log(`updated ticket id=${ticket.id}`)),
      catchError(this.handleError<any>('updateTicket'))
    );
  }

  addTicket (ticket: Ticket): Observable<Ticket> {
    return this.http.post<Ticket>(this.ticketsUrl, ticket, this.httpOptions).pipe(
      tap((newTicket: Ticket) => this.log(`added ticket w/ id=${newTicket.id}`)),
      catchError(this.handleError<Ticket>('addTicket'))
    );
  }

  deleteTicket (ticket: Ticket | number): Observable<Ticket> {
    const id = typeof ticket === 'number' ? ticket : ticket.id;
    const url = `${this.ticketsUrl}/${id}`;
  
    return this.http.delete<Ticket>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted ticket id=${id}`)),
      catchError(this.handleError<Ticket>('deleteTicket'))
    );
  }

  searchTickets(term: string): Observable<Ticket[]> {
    if (!term.trim()) {
      // if not search term, return empty ticket array.
      return of([]);
    }
    return this.http.get<Ticket[]>(`${this.ticketsUrl}/?id=${term}`).pipe(
      tap(_ => this.log(`found tickets matching "${term}"`)),
      catchError(this.handleError<Ticket[]>('searchTicket', []))
    );
  }

}
