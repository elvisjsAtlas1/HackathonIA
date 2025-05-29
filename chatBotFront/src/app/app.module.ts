import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HackathonInfoComponent } from './hackathon-info.component';
import {AppComponent} from './app.component';
import {RouterModule} from '@angular/router';


@NgModule({
  declarations: [
    AppComponent,
    HackathonInfoComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,    // <--- Debe estar aquí
    RouterModule.forRoot([])
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
