import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserRespo } from '../Model/UserRespo';

@Injectable()
export class CustomInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const storedUser = localStorage.getItem('user');

    console.log(storedUser);

    if(storedUser) {
      const userParse = JSON.parse(storedUser);
      const token = userParse.token;
      const authResponse: UserRespo = userParse.userDTOResp;

      console.log(token);
      console.log(authResponse);



      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      return next.handle(request);

    }else{
      return next.handle(request);
    }
    

  }
}
