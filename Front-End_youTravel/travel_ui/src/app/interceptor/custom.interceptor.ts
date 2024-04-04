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
    // const storedUser = localStorage.getItem('user');

    // if(storedUser) {
    //   const userParse = JSON.parse(storedUser);
    //   const token = userParse.token;
    //   const authResponse: UserRespo = userParse.userDTOResp;

    //   if(token && authResponse){
    //     const newCloneRequest = request.clone({
    //       setHeaders:{
    //         Authorization: `Bearer ${token}`
    //       }
    //     })
    //     return next.handle(newCloneRequest);
    //   }
    //   else{
    //     return next.handle(request);
    //   }
    // }else{
    //   return next.handle(request);
    // }

    return next.handle(request);
  }
}
