import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Car } from 'src/app/Model/Car';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(private httpClient: HttpClient) { }

  private apiUrl = 'http://localhost:8083/api/car';

  save(inputData: Car) : Observable<any>{
    return this.httpClient.post(`${this.apiUrl}`,inputData);
  }



}
