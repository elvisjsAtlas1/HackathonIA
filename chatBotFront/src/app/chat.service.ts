import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private apiUrl = 'http://localhost:8081/api/chat/message';

  constructor(private http: HttpClient) {}

  enviarMensaje(mensaje: string): Observable<any> {
    const body = {
      message: mensaje,
      userId: 1
    };
    return this.http.post(this.apiUrl, body, { responseType: 'text' });
  }
}
