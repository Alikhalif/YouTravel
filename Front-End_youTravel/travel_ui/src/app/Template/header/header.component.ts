import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { UserService } from 'src/app/Services/User/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy{

  isLoggedIn$!: Observable<boolean>;
  private subscriptions: Subscription = new Subscription();

  constructor(private userService: UserService, private router: Router) {
    this.isLoggedIn$ = this.userService.getLoggedInStatus();
  }


  // isLoggedIn = false;
  isAdmin = false;

  ngOnInit(){
    this.isAdmin = this.userService.isAdmin();
    this.isLoggedIn$ = this.userService.getLoggedInStatus();
    console.log(this.isLoggedIn$);


    this.subscriptions.add(this.isLoggedIn$.subscribe());

  }

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



  logout() {
    this.userService.logout();
    localStorage.removeItem('user')
    this.userService.loggedIn.next(false)
    this.router.navigate(['/auth/login']);
  }

  ngOnDestroy() {
    this.subscriptions.unsubscribe();
  }
}
