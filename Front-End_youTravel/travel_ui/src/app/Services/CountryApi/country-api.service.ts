import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CountryApiService {

  private apiUrl = 'https://api.countrystatecity.in/v1';
  private apiKey = 'NHhvOEcyWk50N2Vna3VFTE00bFp3MjFKR0ZEOUhkZlg4RTk1MlJlaA==';

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
