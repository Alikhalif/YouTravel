import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CountryApiService {

  private apiUrl = 'https://api.countrystatecity.in/v1';
  private apiKey = 'cFZhZG15cWxZOU9Kb0Y0WHJoVnoyZDJ2b2dJN2RtYVFkZlQ4ZGxlcQ==';

  constructor(private http: HttpClient) { }

  getCountries(): Observable<any> {
    const headers = new HttpHeaders().set('X-CSCAPI-KEY', this.apiKey);
    return this.http.get(`${this.apiUrl}/countries`, { headers });
  }

  getStates(countryCode: string): Observable<any> {
    const headers = new HttpHeaders().set('X-CSCAPI-KEY', this.apiKey);
    return this.http.get(`${this.apiUrl}/countries/${countryCode}/states`, { headers });
  }

  getCities(countryCode: string, stateCode: string): Observable<any> {
    const headers = new HttpHeaders().set('X-CSCAPI-KEY', this.apiKey);
    return this.http.get(`${this.apiUrl}/countries/${countryCode}/states/${stateCode}/cities`, { headers });
  }
}
