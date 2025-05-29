import { Component } from '@angular/core';
import { ChatService } from './chat-service';
import {HackathonInfoComponent} from './hackathon-info.component';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [HackathonInfoComponent, FormsModule], // <-- Aquí importas el standalone component y FormsModule
  standalone: true  // Y también este flag
})
export class AppComponent {
  title = 'chatBotFront';

  mensaje = '';
  respuesta: any;

  constructor(private chatService: ChatService) {}

  enviar() {
    this.chatService.enviarMensaje(this.mensaje).subscribe((res: any) => {
      try {
        const json = JSON.parse(res);
        const content = json.choices?.[0]?.message?.content || 'Sin respuesta válida';
        this.respuesta = content;
      } catch (e) {
        console.error('Error al parsear JSON:', e);
        this.respuesta = 'Error al interpretar la respuesta del servidor.';
      }
    });
  }
}
