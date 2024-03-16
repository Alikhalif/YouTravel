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

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    ListJourneyComponent,
    CreateJourneyComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    GoogleMapsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
