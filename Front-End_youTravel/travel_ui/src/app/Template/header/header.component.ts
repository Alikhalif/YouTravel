import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { UserService } from 'src/app/Services/User/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
  // private isLoggedIn = false;
  private authState = new BehaviorSubject<boolean>(this.userService.isLoggedIn());

  constructor(private userService: UserService, private router: Router) { }


  showMenu = false;
  toggleNavbar(){
    this.showMenu = !this.showMenu;
  }

  toggleDropdown() {
    const userMenu = document.getElementById('userMenu');
    if (userMenu) {
      userMenu.classList.toggle('hidden');
    }
  }

  // isLoggedIn = false;
  isAdmin = false;

  ngOnInit(){
    // this.isLoggedIn = this.userService.isLoggedIn();
    this.isAdmin = this.userService.isAdmin();
  }

  logout() {
    this.userService.logout();
    localStorage.removeItem('user')
    window.location.reload()

  }
}
