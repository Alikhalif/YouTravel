import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Observable } from 'rxjs';
import { ReservationService } from 'src/app/Services/Reservation/reservation.service';
import { UserService } from 'src/app/Services/User/user.service';

@Component({
  selector: 'app-my-journey',
  templateUrl: './my-journey.component.html',
  styleUrls: ['./my-journey.component.css']
})
export class MyJourneyComponent implements OnInit{

  ListReservation: any;
  ListReservationGroup: any;
  ListJourneyCreated: any;
  ListGroupCreated: any;
  user_id: any;

  constructor(private reservationService: ReservationService,
    private userService: UserService,
    private router: Router,
    private toast: NgToastService ){}

  ngOnInit(): void {
    this.user_id = this.userService.getUserId();
    this.loadReservation(this.user_id);
  }

  loadReservation(id:number): void{
    this.reservationService.getReservationByUser(id).subscribe({
      next : (value) => {
        console.log('Retrieved value' + value);
        this.ListReservation = value
        console.log(this.ListReservation);

      },
      error : (error) => {
        console.log('Error' + error);
      }

    });
  }


}
