import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/Model/User';
import { UserService } from 'src/app/Services/User/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(private userService: UserService, private router: Router){}

  error!: any[]

  myUser: User = {

    firstname : '',

    lastname : '',

    phone : '',

    email : '',

    username : '',

    password : '',

  }


  submitForm(){
    this.userService.register(this.myUser).subscribe({
      next: (authenticationResponse) => {
        alert("created successfuly");
        localStorage.setItem('user', JSON.stringify(authenticationResponse))
        this.router.navigateByUrl('/')
        console.log(authenticationResponse.token, 'response');

      },
      error: (err: any) => {
        this.error = err.error;
        console.log(err.error.errors, 'errors');
      },
    });

    this.clear()
  }


  clear(){
    this.myUser = {
      firstname : '',

      lastname : '',

      phone : '',

      email : '',

      username : '',

      password : '',

    }
  }
}
