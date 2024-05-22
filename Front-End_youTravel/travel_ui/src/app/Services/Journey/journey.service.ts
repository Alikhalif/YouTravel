import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Journey } from 'src/app/Model/Journey';
import { JourneySearch } from 'src/app/Model/JourneySearch';
import { JourneyResp } from 'src/app/Model/Response/JourneyResp';

@Injectable({
  providedIn: 'root'
})
export class JourneyService {

  constructor(private httpClient: HttpClient) { }

  private apiUrl = 'http://localhost:8083/api/journey';

  save(inputData: Journey) : Observable<any>{
    return this.httpClient.post(`${this.apiUrl}`,inputData);
  }


  findAll(): Observable<JourneyResp> {
    return this.httpClient.get<JourneyResp>(`${this.apiUrl}/all`);
  }


  search(inputData: JourneySearch): Observable<JourneyResp>{
    return this.httpClient.post<JourneyResp>(`${this.apiUrl}/search`,inputData);
  }



}
