import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from 'src/app/Model/User';
import { Auth } from 'src/app/Model/Auth';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  private apiUrl = 'http://localhost:8083'

  register(inputData: User) : Observable<any>{
    return this.httpClient.post(`${this.apiUrl}/register`,inputData);
  }


  login(inputData: Auth) : Observable<any>{
    return this.httpClient.post(`${this.apiUrl}/login`,inputData);
  }
}
