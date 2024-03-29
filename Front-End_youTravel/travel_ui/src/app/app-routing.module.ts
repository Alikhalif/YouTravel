import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './Template/home/home.component';
import { LoginComponent } from './Pages/Auth/login/login.component';
import { RegisterComponent } from './Pages/Auth/register/register.component';
import { ListJourneyComponent } from './Pages/Journey/list-journey/list-journey.component';
import { CreateJourneyComponent } from './Pages/Journey/create-journey/create-journey.component';
import { CreateReservationComponent } from './Pages/Reserveation/create-reservation/create-reservation.component';

const routes: Routes = [
  { path:'', component:HomeComponent, },
  {
    path: 'auth/login',
    component: LoginComponent,
    // title: 'Login',

  },
  {
    path: 'auth/register',
    component: RegisterComponent,
    // title: 'Register',
  },
  {
    path: 'journey',
    component: ListJourneyComponent,
    // title: 'Register',
  },
  {
    path: 'journey/create',
    component: CreateJourneyComponent,
    // title: 'Register',
  },
  {
    path: 'journey/reserve',
    component: CreateReservationComponent,
    // title: 'Register',
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
