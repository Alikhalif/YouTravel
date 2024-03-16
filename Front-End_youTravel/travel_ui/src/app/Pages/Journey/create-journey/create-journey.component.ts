import { Component, OnInit } from '@angular/core';
import { Country } from 'src/app/Model/country-api/Country';
import { CountryApiService } from 'src/app/Services/CountryApi/country-api.service';

@Component({
  selector: 'app-create-journey',
  templateUrl: './create-journey.component.html',
  styleUrls: ['./create-journey.component.css']
})
export class CreateJourneyComponent implements OnInit{


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


    // v-MiON22GoY8ZGV4Q9WtC6D-fpJpUIn_inhuR7V3E6XTJPyGLya2_zsN3YqK2ZmFlKQ


    countries: any[] = [];
  states: any[] = [];
  cities: any[] = [];
  selectedCountry : any;
  selectedState: any;
  selectedCity: any;



  constructor(private capiService: CountryApiService) { }

  ngOnInit(): void {
    this.loadCountries();
    console.log(this.selectedCity);
  }

  loadCountries() {
    this.capiService.getCountries().subscribe((data: any) => {
      this.countries = data;
      console.log(this.countries);

    });
  }

  loadStates() {
    this.states = []; // Clear previous states
    this.cities = []; // Clear previous cities
    if (this.selectedCountry) {
      console.log(this.selectedCountry);

      this.capiService.getStates(this.selectedCountry).subscribe((data: any) => {
        this.states = data;
        console.log(this.states);

      });
    }
  }

  loadCities() {
    this.cities = []; // Clear previous cities
    console.log(this.selectedState);



    if (this.selectedCountry && this.selectedState) {
      this.capiService.getCities(this.selectedCountry, this.selectedState).subscribe((data: any) => {
        this.cities = data;
      });
    }


  }


}
