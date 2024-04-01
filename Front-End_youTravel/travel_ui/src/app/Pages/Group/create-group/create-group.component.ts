import { Component, Input } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { Car } from 'src/app/Model/Car';
import { Group } from 'src/app/Model/Group';
import { UserRespo } from 'src/app/Model/UserRespo';
import { Country } from 'src/app/Model/country-api/Country';
import { CarService } from 'src/app/Services/Car/car.service';
import { CountryApiService } from 'src/app/Services/CountryApi/country-api.service';
import { GroupService } from 'src/app/Services/Group/group.service';
import { UserService } from 'src/app/Services/User/user.service';

@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.css']
})
export class CreateGroupComponent {

  api_country: Country [] = [];
  index: number = 0;
  x: number = 0;
  error!: string;

  ListJourney: any;
  showPopup = false;
  id_journey: number = 0;


  my_group: Group = {
    num: 0,
    countryStarting : '',

    stateStarting: '',

    cityStarting : '',

    countryArrival : '',

    stateArrival: '',

    cityArrival : '',

    fromPlace : '',

    toPlace : '',

    startDate : new Date(),

    endDate : new Date(),

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
    private groupService: GroupService,
    // private reservationService: ReservationService,
    private toast: NgToastService){}



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
      console.log(x);

      if (x < 3 ) {
        this.my_group.countryStarting = this.api_country[x].name;
      } else {
        this.my_group.countryArrival = this.api_country[x].name;
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
      this.my_group.stateStarting = this.api_country[x].name;
    }else{
      this.my_group.stateArrival = this.api_country[x].name
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
      this.my_group.cityStarting = this.api_country[x].name;
    }else{
      this.my_group.cityArrival = this.api_country[x].name;
    }
    console.log(this.my_group);
  }

  clear(){
    // this.api_country=[];
  }


  save(){
    const userJson = this.userService.getUserFromLocalStorage()
    if (userJson && userJson.uid !== undefined) {
      this.my_group.user_id = userJson.uid;
      this.groupService.save(this.my_group).subscribe({
        next: (res: any) => {

          console.log('response', res);

        },
        error: (err: any) => {
          // this.error = err.error;
          // console.log(err.error.errors, 'errors');
        },
      });
    }
  }


}
