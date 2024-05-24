import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { User } from 'src/app/Model/User';
import { Auth } from 'src/app/Model/Auth';
import { AuthResponse } from 'src/app/Model/AuthResponse';
import { UserRespo } from 'src/app/Model/UserRespo';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  public loggedIn = new BehaviorSubject<boolean>(this.isLoggedIn());

  constructor(private httpClient: HttpClient) { }

  private apiUrl = 'http://localhost:8083'
  user: UserRespo | any= {
    uid:0,
    firstname : '',

    lastname : '',

    phone : '',

    email: '',

    username: '',

    role : ''
  }

  register(inputData: User) : Observable<AuthResponse>{
    return this.httpClient.post<AuthResponse>(`${this.apiUrl}/register`,inputData);
  }


  login(inputData: Auth) : Observable<AuthResponse>{
    return this.httpClient.post<AuthResponse>(`${this.apiUrl}/login`,inputData);
  }

  getUserFromLocalStorage(): UserRespo | null{
    const userJson = localStorage.getItem('user');
    if (userJson) {
      const userParse = JSON.parse(userJson);
      this.user = userParse.userDTOResp;
      // console.log('User Object:', this.user);
      return this.user;
    } else {
      console.log('User object not found in localStorage');
      return null;
    }
  }


  isLoggedIn(): boolean {

    return localStorage.getItem('user') !== null;
  }

  isAdmin(): boolean {
    const userJson = this.getUserFromLocalStorage();
    return userJson?.role === 'ADMIN';
  }

  /////////////
  getLoggedInStatus(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  isMemeber(): boolean{
    const userJson = this.getUserFromLocalStorage();
    if (userJson && userJson.role) {
      return userJson.role === "ADMIN";
    } else {
      return false;
    }
  }

  //logout

  logout(): Observable<any> {
    return this.httpClient.post(`${this.apiUrl}/auth/logout`, {}).pipe(
      tap(() => {
        localStorage.removeItem('user');
        this.loggedIn.next(false);
      })
    );
  }

  getRole():string|null{
    const userJson = this.getUserFromLocalStorage();
    if (userJson && userJson.role) {
      return userJson.role;
    } else {
      return null;
    }
  }

  getUsername():string|null{
    return localStorage.getItem("username");
  }

  getId():string | null{
    const userJson = this.getUserFromLocalStorage();
    if (userJson && userJson.role) {
      return userJson.role;
    } else {
      return null;
    }
  }

  getToken():string|null{
    const userJson = localStorage.getItem('user');
    if (userJson) {
      const userParse = JSON.parse(userJson);
      return userParse.token;

    } else {
      console.log('User object not found in localStorage');
      return null;
    }
  }

  getUserId():number|null{
    const userJson = localStorage.getItem('user');
    if (userJson) {
      const userParse = JSON.parse(userJson);
      console.log(userParse.userDTOResp.uid);

      return userParse.userDTOResp.uid;

    } else {
      console.log('User object not found in localStorage');
      return null;
    }
  }


}
