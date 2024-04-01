import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Auth } from 'src/app/Model/Auth';
import { UserService } from 'src/app/Services/User/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private userService: UserService,
              private router: Router,
              private toast: NgToastService){}



  roles: string[] = [];
  username: string | null = null;

  error!: any[]

  myAuth: Auth = {

    username: '',

    password: '',
  }

  // ngOnInit(){

  // }

  loginForm(){
    this.userService.login(this.myAuth).subscribe({
      next: (res) => {
        this.toast.success({detail:"SUCCESS",summary:'Login Success',duration:5000});
        localStorage.setItem('user', JSON.stringify(res))

        this.router.navigateByUrl('/')

      },
      error: (err: any) => {
        this.error = err.error;
        console.log(err.error.errors, 'errors');
        this.toast.success({detail:"WARN",summary:' Login or password incorrect',duration:5000});
      },
    });

    this.clear()
  }


  clear(){
    this.myAuth = {
      username: '',

      password: '',

    }
  }
}
