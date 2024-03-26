export interface Journey{
  id: number,
  countryStarting : String,

  stateStarting: String,

  cityStarting : String,

  countryArrival : String,

  stateArrival: String,

  cityArrival : String,

  fromPlace : String,

  toPlace : String,

  startDate : Date,

  endDate : Date,

  costTotal : number,

  nbrPlaces : number,

  car_id : number,

  user_id : number,
}
