import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {BrowserModule} from '@angular/platform-browser';
import {ChatIaComponent} from './chat-ia.component';
import { FeaturedProductsComponent } from './featured-products/featured-products.component';

@NgModule({
  declarations: [AppComponent, ChatIaComponent, FeaturedProductsComponent],
  imports: [BrowserModule, FormsModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
