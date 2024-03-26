import { Component, OnInit } from '@angular/core';
import { JourneyResp } from 'src/app/Model/Response/JourneyResp';
import { JourneyService } from 'src/app/Services/Journey/journey.service';

@Component({
  selector: 'app-list-journey',
  templateUrl: './list-journey.component.html',
  styleUrls: ['./list-journey.component.css']
})
export class ListJourneyComponent implements OnInit{

  ListJourney: any;

  constructor(private journeyService: JourneyService){}

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

}
