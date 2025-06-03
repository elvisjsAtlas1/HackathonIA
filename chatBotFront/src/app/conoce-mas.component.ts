import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-conoce-mas',
  imports: [RouterModule],  // 👈 IMPORTANTE
  templateUrl: './conoce-mas.component.html',
  styleUrls: ['./conoce-mas.component.css']
})
export class ConoceMasComponent {}
