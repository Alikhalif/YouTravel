import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Join } from 'src/app/Model/Join';

@Injectable({
  providedIn: 'root'
})
export class JoinService {

  constructor(private httpClient: HttpClient) { }

  private apiUrl = 'http://localhost:8083/api/join';

  save(inputData: Join) : Observable<any>{
    return this.httpClient.post(`${this.apiUrl}`,inputData);
  }


}
