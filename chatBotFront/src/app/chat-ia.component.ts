import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import {CommonModule} from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chat-ia',
  templateUrl: './chat-ia.component.html',
  styleUrls: ['./chat-ia.component.css'],
  standalone: true,
  imports: [FormsModule,CommonModule] // necesario para [(ngModel)]
})
export class ChatIaComponent {
  showChat: boolean = false; // para controlar el despliegue
  message: string = '';
  response: string = '';
  userId: number = 1;

  constructor(private http: HttpClient, private router: Router) {}

  // Muestra u oculta el chat al presionar el ícono
  toggleChat() {
    this.showChat = !this.showChat;
  }

  // Limpia el contenido del chat
  clearChat() {
    this.message = '';
    this.response = '';
  }

  // Envía mensaje al backend
  enviarMensaje() {
    if (!this.message.trim()) return;

    const payload = {
      message: this.message,
      userId: this.userId
    };

    this.http.post('http://localhost:8081/api/chat/message', payload, { responseType: 'text' })
      .subscribe({
        next: (res) => {
          this.response = res;

          // Detectar intención directamente en el mensaje del usuario
          const lowerMessage = this.message.toLowerCase();
          if (
            lowerMessage.includes('conocer más sobre la empresa') ||
            lowerMessage.includes('sobre la empresa') ||
            lowerMessage.includes('empresa infotel')
          ) {
            this.highlightConoceMas();
            this.router.navigate(['/conoce-mas']);
          }
        },
        error: (err) => {
          this.response = 'Error al comunicarse con el servidor';
          console.error(err);
        }
      });
  }

  highlightConoceMas() {
    const button = document.getElementById('conoceBtn');
    if (button) {
      // Scroll suave y centrado (scrollIntoView con opciones)
      button.scrollIntoView({ behavior: 'smooth', block: 'center', inline: 'nearest' });

      // Añadir clase para iluminación
      button.classList.add('highlight');

      // Quitar clase después de 3 segundos
      setTimeout(() => {
        button.classList.remove('highlight');
      }, 3000);
    }
  }


}
