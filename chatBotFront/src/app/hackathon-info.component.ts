import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-hackathon-info',
  templateUrl: './hackathon-info.component.html',
  styleUrls: ['./hackathon-info.component.css']
})
export class HackathonInfoComponent {
  info = 'Selecciona una opción para cargar información.';

  constructor(private http: HttpClient) {}

  cargar(tipo: string) {
    this.info = 'Cargando...';
    this.http.get(`/api/hackathon/${tipo}`, { responseType: 'text' })
      .subscribe({
        next: data => this.info = data,
        error: err => this.info = 'Error al cargar información.'
      });
  }
}
