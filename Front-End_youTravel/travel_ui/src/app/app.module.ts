import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './Template/header/header.component';
import { HomeComponent } from './Template/home/home.component';
import { FooterComponent } from './Template/footer/footer.component';
import { LoginComponent } from './Pages/Auth/login/login.component';
import { RegisterComponent } from './Pages/Auth/register/register.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ListJourneyComponent } from './Pages/Journey/list-journey/list-journey.component';
import { CreateJourneyComponent } from './Pages/Journey/create-journey/create-journey.component';
import { GoogleMapsModule } from '@angular/google-maps';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import {MatStepperModule} from '@angular/material/stepper';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';
import { CreateReservationComponent } from './Pages/Reserveation/create-reservation/create-reservation.component';
import { NgToastModule } from 'ng-angular-popup';
import { CreateGroupComponent } from './Pages/Group/create-group/create-group.component';
import { ListGroupComponent } from './Pages/Group/list-group/list-group.component';
import { MyJourneyComponent } from './Pages/Profile/my-journey/my-journey.component';
import { ProfileSidebarComponent } from './Template/Sidebar/profile-sidebar/profile-sidebar.component';
import { NotFoundComponent } from './Pages/404/not-found/not-found.component';
// import { StoreModule } from '@ngrx/store';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    ListJourneyComponent,
    CreateJourneyComponent,
    CreateReservationComponent,
    CreateGroupComponent,
    ListGroupComponent,
    MyJourneyComponent,
    ProfileSidebarComponent,
    NotFoundComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    GoogleMapsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatInputModule,
    MatIconModule,
    MatStepperModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    CommonModule,
    NgToastModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
