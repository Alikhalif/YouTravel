import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { Reservation } from 'src/app/Model/Reservation';
import { JourneyResp } from 'src/app/Model/Response/JourneyResp';
import { JourneyService } from 'src/app/Services/Journey/journey.service';
import { ReservationService } from 'src/app/Services/Reservation/reservation.service';
import { UserService } from 'src/app/Services/User/user.service';

@Component({
  selector: 'app-list-journey',
  templateUrl: './list-journey.component.html',
  styleUrls: ['./list-journey.component.css']
})
export class ListJourneyComponent implements OnInit{

  ListJourney: any;
  showPopup = false;
  id_journey: number = 0;

  myReservation: Reservation ={
    reservedPlaces : 1,

    user_id : 0,

    journey_id : 0,
  }

  constructor(private journeyService: JourneyService,
    private userService: UserService,
    private reservationService: ReservationService,
    private router: Router,
    private toast: NgToastService ){}

  ngOnInit(){
    this.loadJourney();

  }

  loadJourney(): void{
    this.journeyService.findAll().subscribe({
      next : (value) => {
        console.log('Retrieved value' + value);
        this.ListJourney = value
        console.log(this.ListJourney);

      },
      error : (error) => {
        alert("Error while retrieving data!");
        console.log('Error' + error);
      }

    });
  }


  saveReservation(){
    const userJson = this.userService.getUserFromLocalStorage()
    console.log(userJson);

    if (userJson && userJson.uid !== undefined) {
      if(this.id_journey > 0){
        this.myReservation.user_id = userJson.uid;
        this.myReservation.journey_id = this.id_journey;
        console.log(this.myReservation);

        this.reservationService.save(this.myReservation).subscribe({
          next: (res: Reservation) => {
            this.toast.success({detail:"SUCCESS",summary:'Your reservation has been saved successfully',duration:3000});
            console.log(res, ' car response');


          },
          error: (err: any) => {
            // this.error = err.error;
            // console.log(err.error.errors, 'errors');
          },
        });
        this.loadJourney();
      }


    } else {
      this.toast.success({detail:"WARN",summary:'Please login first',duration:5000});
      this.router.navigateByUrl('/login')
    }


    this.closePopup();

  }

  togglePopup(id:number, nbrP: number) {
    const userJson = this.userService.getUserFromLocalStorage()
    console.log(userJson);

    if (userJson && userJson.uid !== undefined) {
      if(nbrP > 0){
        this.showPopup = !this.showPopup;
        console.log(id);
        this.id_journey = id;
      }else{
        alert("All seats are full")
      }
    } else {
      this.toast.success({detail:"WARN",summary:'Please login first',duration:5000});
      this.router.navigateByUrl('/login')
    }


  }

  closePopup() {
    this.showPopup = false;
  }

}
