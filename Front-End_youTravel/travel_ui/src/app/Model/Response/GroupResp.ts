import { UserResp } from "./UserResp";

export interface GroupResp{
  num: number,
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

  userDTOResp: UserResp;
}
