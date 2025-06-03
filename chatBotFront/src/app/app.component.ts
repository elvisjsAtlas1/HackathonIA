import { Component } from '@angular/core';
import { ChatService } from './chat.service';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common'; // ✅ Importa el servicio
import { CommonModule } from '@angular/common'; // 👈 Agrega esto

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  imports: [CommonModule, FormsModule],
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'chatBotFront';
  mensaje = '';
  respuesta: any;

  constructor(private chatService: ChatService) {}

  enviar() {
    this.chatService.enviarMensaje(this.mensaje).subscribe((res: any) => {
      try {
        this.respuesta = res;
      } catch (e) {
        console.error('Error al parsear la respuesta:', e);
        this.respuesta = 'Error al interpretar la respuesta del servidor.';
      }
    });
  }
}
