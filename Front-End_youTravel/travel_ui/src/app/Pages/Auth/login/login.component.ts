import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Auth } from 'src/app/Model/Auth';
import { UserRespo } from 'src/app/Model/UserRespo';
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

    email: '',

    password: '',
  }

  // ngOnInit(){

  // }

  loginForm(){
    this.userService.login(this.myAuth).subscribe({
      next: (res) => {
        this.toast.success({detail:"SUCCESS",summary:'Login Success',duration:5000});
        localStorage.setItem('user', JSON.stringify(res))

        const storedUser = localStorage.getItem('user');
        if(storedUser!=null){
          const userParse = JSON.parse(storedUser);
          const token = userParse.token;
          const authResponse: UserRespo = userParse.userDTOResp;
          this.router.navigateByUrl('/')

        }

        this.userService.loggedIn.next(true)


      },
      error: (err: any) => {
        this.error = err;
        console.log(err, 'errors');
        this.toast.warning({detail:"WARN",summary:' Login or password incorrect',duration:5000});
      },
    });

    this.clear()
  }


  clear(){
    this.myAuth = {
      email: '',

      password: '',

    }
  }
}
