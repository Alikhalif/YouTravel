import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/Services/User/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
  constructor(private userService: UserService, private router: Router) { }



  showMenu = false;
  toggleNavbar(){
    this.showMenu = !this.showMenu;
  }


  isLoggedIn = false;
  isAdmin = false;

  ngOnInit(){
    this.isLoggedIn = this.userService.isLoggedIn();
    this.isAdmin = this.userService.isAdmin();
  }

  logout() {
    this.userService.logout();
    this.router.navigateByUrl('/login');

  }
}
