import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-chat-ia',
  templateUrl: './chat-ia.component.html',
  imports: [
    FormsModule
  ],
  styleUrls: ['./chat-ia.component.css']
})
export class ChatIaComponent {
  showChat = false;
  message = '';
  response = '';
  userId = 1; // Puedes cambiar esto por el usuario logueado si luego haces login

  constructor(private http: HttpClient) {}

  toggleChat() {
    this.showChat = !this.showChat;
  }

  enviarMensaje() {
    if (!this.message.trim()) return;

    const payload = {
      message: this.message,
      userId: this.userId
    };

    this.http.post<string>('http://localhost:8081/api/chat/message', payload)
      .subscribe({
        next: (res) => {
          this.response = res;
        },
        error: (err) => {
          this.response = 'Error al comunicarse con el servidor';
          console.error(err);
        }
      });
  }
}
