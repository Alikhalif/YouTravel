import { Component, Input } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { Car } from 'src/app/Model/Car';
import { Journey } from 'src/app/Model/Journey';
import { JourneySearch } from 'src/app/Model/JourneySearch';
import { Reservation } from 'src/app/Model/Reservation';
import { UserRespo } from 'src/app/Model/UserRespo';
import { Country } from 'src/app/Model/country-api/Country';
import { CarService } from 'src/app/Services/Car/car.service';
import { CountryApiService } from 'src/app/Services/CountryApi/country-api.service';
import { JourneyService } from 'src/app/Services/Journey/journey.service';
import { ReservationService } from 'src/app/Services/Reservation/reservation.service';
import { UserService } from 'src/app/Services/User/user.service';

@Component({
  selector: 'app-create-reservation',
  templateUrl: './create-reservation.component.html',
  styleUrls: ['./create-reservation.component.css']
})
export class CreateReservationComponent {

  api_country: Country [] = [];
  index: number = 0;
  x: number = 0;
  error!: string;

  ListJourney: any;
  showPopup = false;
  id_journey: number = 0;



  my_car: Car = {
    id: 0,
    name : '',

    color : '',

    carType : '',

    energyType : '',

    user_id : 0,
  }

  user: UserRespo | any= {
    firstname : '',

    lastname : '',

    phone : '',

    email: '',

    username: '',

    role : ''
  }

  journeySearch: JourneySearch ={
    countryStarting: "",
    stateStarting: "",
    cityStarting: "",
    countryArrival: "",
    stateArrival: "",
    cityArrival: "",
    startDate: new Date(),
    endDate: new Date(),
  }

  myReservation: Reservation ={
    reservedPlaces : 1,

    user_id : 0,

    journey_id : 0,
  }

  countries: Country[] = [];
  states: Country[] = [];
  cities: Country[] = [];
  selectedCountry : any;
  selectedState: any;
  selectedCity: any;
  @Input() placeholder = '';

  constructor(private capiService: CountryApiService,
    private userService: UserService,
    private carService: CarService,
    private journeyService: JourneyService,
    private reservationService: ReservationService,
    private toast: NgToastService){}


  ////////////////////////////////////////////
  ngOnInit(): void {
    this.loadCountries();

  }

  loadCountries() {
    this.capiService.getCountries().subscribe((data: any) => {
      this.countries = data;
      // console.log(this.api_country[0]);

    });

    // this.search(this.selectedCountry)

  }

  loadStates(x:number) {
    this.states = []; // Clear previous states
    this.cities = []; // Clear previous cities
    // if (this.api_country) {
      console.log(this.api_country[x].iso2);
      console.log(this.journeySearch.countryStarting);
console.log(x);

      if (x < 3 ) {
        this.journeySearch.countryStarting = this.api_country[x].name;
      } else {
        this.journeySearch.countryArrival = this.api_country[x].name;
      }

      this.capiService.getStates(this.api_country[x].iso2).subscribe((data: any) => {
        this.states = data;
        console.log(this.states);

      });
    // }
  }

  loadCities(x:number) {
    this.cities = []; // Clear previous cities
    console.log(this.selectedState);

    console.log(x);

    if(x < 3){
      this.journeySearch.stateStarting = this.api_country[x].name;
    }else{
      this.journeySearch.stateArrival = this.api_country[x].name
    }
    // if (this.selectedCountry && this.selectedState) {
      this.capiService.getCities(this.api_country[x-1].iso2, this.api_country[x].iso2).subscribe((data: any) => {
        this.cities = data;
      });
    // }

  }


  load(x:number){
    console.log(x);
    if(x < 3){
      this.journeySearch.cityStarting = this.api_country[x].name;
    }else{
      this.journeySearch.cityArrival = this.api_country[x].name;
    }
    console.log(this.journeySearch);
  }


  // search

  search(){

    this.journeyService.search(this.journeySearch).subscribe({
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
    if (userJson && userJson.uid !== undefined) {
      if(this.id_journey > 0){
        this.myReservation.user_id = userJson.uid;
        this.myReservation.journey_id = this.id_journey;
        console.log(this.myReservation);

        this.reservationService.save(this.myReservation).subscribe({
          next: (res: Reservation) => {

            console.log(res, ' car response');
            this.toast.success({detail:"SUCCESS",summary:'Your reservation has been saved successfully',duration:3000});


          },
          error: (err: any) => {
            // this.error = err.error;
            // console.log(err.error.errors, 'errors');
          },
        });
      }


    } else {
      alert("Please login first");
      window.location.href='/login';
    }


    this.closePopup();

  }


  togglePopup(id:number, nbrP: number) {
    if(nbrP > 0){
      this.showPopup = !this.showPopup;
      console.log(id);
      this.id_journey = id;
    }else{
      alert("All seats are full")
    }



  }

  closePopup() {
    this.showPopup = false;
  }


}
