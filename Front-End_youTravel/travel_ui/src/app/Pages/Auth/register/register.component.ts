import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { User } from 'src/app/Model/User';
import { UserService } from 'src/app/Services/User/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(private userService: UserService,
              private router: Router,
              private toast: NgToastService){}

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
        localStorage.setItem('user', JSON.stringify(authenticationResponse))
        this.router.navigateByUrl('/')
        this.toast.success({detail:"SUCCESS",summary:'Register Success',duration:5000});
        window.location.reload()


      },
      error: (err: any) => {
        this.error = err.error;
        console.log(err.error.errors, 'errors');
        this.toast.success({detail:"WARN",summary:'All fields are mandatory',duration:5000});
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
