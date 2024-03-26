import { Component, ElementRef, Input, OnInit, ViewChild,AfterViewInit, NgZone } from '@angular/core';
import { Country } from 'src/app/Model/country-api/Country';
import { CountryApiService } from 'src/app/Services/CountryApi/country-api.service';
import {FormBuilder, Validators, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import { Journey } from 'src/app/Model/Journey';
import { Car } from 'src/app/Model/Car';
import { User } from 'src/app/Model/User';
import { UserRespo } from 'src/app/Model/UserRespo';
import { UserService } from 'src/app/Services/User/user.service';
import { GoogleMapsService } from 'src/app/Services/Google-maps/google-maps.service';
import { CarService } from 'src/app/Services/Car/car.service';
import { JourneyService } from 'src/app/Services/Journey/journey.service';


@Component({
  selector: 'app-create-journey',
  templateUrl: './create-journey.component.html',
  styleUrls: ['./create-journey.component.css'],


})
export class CreateJourneyComponent implements OnInit{
  formGroup = this._formBuilder.group({
    firstCtrl: ['', Validators.required],
  });


  // v-MiON22GoY8ZGV4Q9WtC6D-fpJpUIn_inhuR7V3E6XTJPyGLya2_zsN3YqK2ZmFlKQ
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
  searchTerm: string | undefined;
  map: any;
  @Input() placeholder = '';

  @ViewChild('inputField')
  public searchElementRef!: ElementRef;

  autocomplete : google.maps.places.Autocomplete | undefined;



  constructor(private capiService: CountryApiService,
    private _formBuilder: FormBuilder,
    private userService: UserService,
    private carService: CarService,
    private journeyService: JourneyService,
    private ngZone: NgZone,
    private googleMapsService: GoogleMapsService,

    ) { }

  ngOnInit(): void {
    this.loadCountries();
    console.log(this.selectedCity);

    this.loadGoogleMaps(() => {
      this.searchPlace();
    });
  }

/////////////////////////////////

    display: any;
    center: google.maps.LatLngLiteral = {
        lat: 32.300880132436255,
        lng: -9.23572459757585
    };

    zoom = 4;

    /*------------------------------------------
    --------------------------------------------
    moveMap()
    --------------------------------------------
    --------------------------------------------*/
    moveMap(event: google.maps.MapMouseEvent) {
        if (event.latLng != null) this.center = (event.latLng.toJSON());
    }

    /*------------------------------------------
    --------------------------------------------
    move()
    --------------------------------------------
    --------------------------------------------*/
    move(event: google.maps.MapMouseEvent) {
        if (event.latLng != null) this.display = event.latLng.toJSON();
    }



    markers: { position: google.maps.LatLngLiteral
              options?:google.maps.Animation }[] = [];

    onMapClick(event: google.maps.MapMouseEvent) {
      this.markers = []
      const position = event.latLng!.toJSON();

      this.markers.push({
        position,
        options:google.maps.Animation.BOUNCE
      });

      console.log(this.markers);
    }



    // searche place
    loadGoogleMaps(callback: () => void) {
      if (typeof google === 'undefined' || typeof google.maps === 'undefined') {
        const script = document.createElement('script');
        script.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyCaKbVhcX_22R_pRKDYuNA7vox-PtGaDkI';
        script.onload = callback;
        document.body.appendChild(script);
      } else {
        callback();
      }
    }

    searchPlace() {
      console.log("test");
      console.log(this.searchElementRef.nativeElement.value);


      const autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement);
      autocomplete.addListener('place_changed', () => {
        this.ngZone.run(() => {
          const place: google.maps.places.PlaceResult = autocomplete.getPlace();
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }
          // You can access place.geometry.location.latitude and place.geometry.location.longitude here
          console.log(place);
        });
      });
    }



    // load country and stats and city

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

  clear(){
    // this.api_country=[];
  }






  // search for place and zoomIn
  search(searchTerm: string) {
    // if (this.searchTerm === '') return;

    // this.mapsAPILoader.load().then(() => {
    //   const geocoder = new google.maps.Geocoder();
    //   geocoder.geocode({ address: this.searchTerm }, (results, status) => {
    //     if (status === 'OK' && results[0]) {
    //       const location = results[0].geometry.location;
    //       this.zoomIn(location);
    //     } else {
    //       alert('Location not found.');
    //     }
    //   });
    // });
  }

  zoomIn(location: google.maps.LatLng) {
    if (!this.map) return;

    this.map.setCenter(location);
    this.map.setZoom(15); // Adjust zoom level as needed
  }

  getUserFromLocalStorage(): UserRespo | null{
    const userJson = localStorage.getItem('user');
    if (userJson) {
      const userParse = JSON.parse(userJson);
      this.user = userParse.userDTOResp;
      // console.log('User Object:', this.user);
      return this.user;
    } else {
      console.log('User object not found in localStorage');
      return null;
    }
  }

  save(){
    const userJson = this.userService.getUserFromLocalStorage()
    if (userJson && userJson.uid !== undefined) {
      this.my_journey.user_id = userJson.uid;
      this.my_car.user_id = userJson.uid;
      this.carService.save(this.my_car).subscribe({
        next: (res: Car) => {

          console.log(res.id, ' car response');

          this.my_journey.car_id = res.id
          console.log('response', res);
          this.save_Journey();

        },
        error: (err: any) => {
          // this.error = err.error;
          // console.log(err.error.errors, 'errors');
        },
      });


    } else {
      alert("Please login first");
      window.location.href='/login';
    }

    console.log(this.my_car);
    console.log(this.my_journey);

  }

  save_Journey(){
    const userJson = this.userService.getUserFromLocalStorage()
    if (userJson && userJson.uid !== undefined) {
      this.my_journey.user_id = userJson.uid;
      this.journeyService.save(this.my_journey).subscribe({
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
