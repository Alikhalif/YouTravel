import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservation } from 'src/app/Model/Reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private httpClient: HttpClient) { }

  private apiUrl = 'http://localhost:8083/api/reservation';

  save(inputData: Reservation) : Observable<any>{
    return this.httpClient.post(`${this.apiUrl}`,inputData);
  }
}
