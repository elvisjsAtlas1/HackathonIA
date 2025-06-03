import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ChatIaComponent } from './chat-ia.component';
import {FeaturedProductsComponent} from './featured-products.component'; // importa el componente

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ChatIaComponent, FeaturedProductsComponent],// importa el componente aquí
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  message = '';
  response = '';
  userId = 1;

  constructor(private http: HttpClient) {}

  enviarMensaje() {
    if (!this.message.trim()) return;
    const payload = { message: this.message, userId: this.userId };
    this.http.post<string>('http://localhost:8081/api/chat/message', payload)
      .subscribe({
        next: res => this.response = res,
        error: err => {
          this.response = 'Error al comunicarse con el servidor';
          console.error(err);
        }
      });
  }

  clearChat() {
    this.message = '';
    this.response = '';
  }
}
