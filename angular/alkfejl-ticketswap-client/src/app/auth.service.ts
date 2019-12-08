import { Injectable } from '@angular/core';
import { User } from './user';
import { HttpHeaders, HttpClient } from '@angular/common/http';

export const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': ''
  })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public isLoggedIn: boolean = false;
  public user: User;
  public redirectUrl: string;

  private authUrl: string = 'http://localhost:8080/users';

  constructor(
    private http: HttpClient
  ) { }

  async login(username: string, password: string): Promise<User> {
    try {
      const token = btoa(`${username}:${password}`);
      httpOptions.headers = httpOptions.headers.set('Authorization', `Basic ${token}`);
      const user = await this.http.post<User>(`${this.authUrl}/login`, {}, httpOptions).toPromise();
      this.isLoggedIn = true;
      this.user = user;
      console.log(user);
      return Promise.resolve(this.user);
    } catch (e) {
      console.log(e);
      return Promise.reject();
    }
  }
  
  logout() {
    httpOptions.headers = httpOptions.headers.set('Authorization', ``);
    this.isLoggedIn = false;
    this.user = null;
  }
}
