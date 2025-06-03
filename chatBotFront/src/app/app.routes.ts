import { Routes } from '@angular/router';
import { HomeComponent } from './home.component';
import { ConoceMasComponent } from './conoce-mas.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'conoce-mas', component: ConoceMasComponent }
];
