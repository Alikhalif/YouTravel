import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Auth } from 'src/app/Model/Auth';
import { UserService } from 'src/app/Services/User/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private userService: UserService, private router: Router){}



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
    // this.userService.login(this.myAuth).subscribe({
    //   next: (res: any) => {


    //     // this.authService.setId(res.user_id);
    //     // this.authService.setRole(res.role);
    //     // this.authService.setToken(res.token);

    //     // this.roles =this.authService.generateRoleTok();
    //     // this.username = this.authService.generateUsernameTok();

    //     // alert(this.username)


    // //return roles.includes('ROLE_MANAGER');

    //     //localStorage.setItem('token', res.token)

    //     this.router.navigateByUrl('/')
    //     console.log(res.token, 'response');

    //   },
    //   error: (err: any) => {
    //     this.error = err.error;
    //     console.log(err.error.errors, 'errors');
    //   },
    // });

    // this.clear()
  }


  clear(){
    this.myAuth = {
      username: '',

      password: '',

    }
  }
}
