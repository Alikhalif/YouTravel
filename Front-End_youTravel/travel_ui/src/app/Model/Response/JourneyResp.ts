import { Car } from "../Car";
import { UserResp } from "./UserResp";

export interface JourneyResp {
  code: number;
  countryStarting: string;
  stateStarting: string;
  cityStarting: string;
  countryArrival: string;
  stateArrival: string;
  cityArrival: string;
  fromPlace: string;
  toPlace: string;
  startDate: Date;
  endDate: Date;
  costTotal: number;
  nbrPlaces: number;
  carDTOResp: Car;
  userDTOResp: UserResp;
}
