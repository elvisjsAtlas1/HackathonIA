import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { routes } from './app.routes';
import { HomeComponent } from './home.component';
import { ConoceMasComponent } from './conoce-mas.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ConoceMasComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes)  // Esto es lo que activa las rutas en toda tu app
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
