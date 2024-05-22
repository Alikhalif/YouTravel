import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Group } from 'src/app/Model/Group';
import { GroupSearch } from 'src/app/Model/GroupSearch';
import { GroupResp } from 'src/app/Model/Response/GroupResp';

@Injectable({
  providedIn: 'root'
})
export class GroupService {

  private apiUrl = 'http://localhost:8083/api/group';

  constructor(private httpClient: HttpClient) { }

  save(inputData: Group) : Observable<any>{
    return this.httpClient.post(`${this.apiUrl}`,inputData);
  }


  findAll(): Observable<GroupResp> {
    return this.httpClient.get<GroupResp>(`${this.apiUrl}/all`);
  }


  search(inputData: GroupSearch): Observable<GroupResp>{
    return this.httpClient.post<GroupResp>(`${this.apiUrl}/search`,inputData);
  }


}
