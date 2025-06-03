import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { ChatIaComponent } from './chat-ia.component';
import {FeaturedProductsComponent} from './featured-products.component';
import {CategoriesComponent} from './categories.component';
import {Router, RouterLink} from '@angular/router'; // importa el componente
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ChatIaComponent, FeaturedProductsComponent, CategoriesComponent, RouterModule ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  message = '';
  response = '';
  userId = 1;

  mostrarConoceMas: boolean = false;

  constructor(private http: HttpClient, private router: Router) {}  // <-- Inyecta Router

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
  abrirSobreNosotros() {
    window.location.href = 'assets/sobre-nosotros.html';
  }




  protected readonly scrollTo = scrollTo;

  scrollToSection(sectionId: string): void {
    const element = document.getElementById(sectionId);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  }
}
