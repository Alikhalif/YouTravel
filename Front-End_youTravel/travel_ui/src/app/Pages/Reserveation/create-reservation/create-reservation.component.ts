import { Component, Input } from '@angular/core';
import { Car } from 'src/app/Model/Car';
import { Journey } from 'src/app/Model/Journey';
import { UserRespo } from 'src/app/Model/UserRespo';
import { Country } from 'src/app/Model/country-api/Country';
import { CarService } from 'src/app/Services/Car/car.service';
import { CountryApiService } from 'src/app/Services/CountryApi/country-api.service';
import { JourneyService } from 'src/app/Services/Journey/journey.service';
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

  my_journey: Journey = {
    id:0,
    countryStarting : '',

    stateStarting: '',

    cityStarting : '',

    countryArrival : '',

    stateArrival: '',

    cityArrival : '',

    fromPlace : '',

    toPlace : '',

    startDate : new  Date(),

    endDate : new Date(),

    costTotal : 0,

    nbrPlaces : 0,

    car_id : 0,

    user_id : 0,
  }

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
    private journeyService: JourneyService){}


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
      console.log(this.my_journey.countryStarting);
console.log(x);

      if (x < 3 ) {
        this.my_journey.countryStarting = this.api_country[x].name;
      } else {
        this.my_journey.countryArrival = this.api_country[x].name;
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
      this.my_journey.stateStarting = this.api_country[x].name;
    }else{
      this.my_journey.stateArrival = this.api_country[x].name
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
      this.my_journey.cityStarting = this.api_country[x].name;
    }else{
      this.my_journey.cityArrival = this.api_country[x].name;
    }
    console.log(this.my_journey);

  }


}
