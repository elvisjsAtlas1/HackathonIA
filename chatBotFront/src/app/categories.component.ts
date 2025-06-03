import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css'],
  standalone: true,
  imports: [CommonModule]
})
export class CategoriesComponent {
  scrollTo(sectionId: string) {
    const element = document.getElementById(sectionId);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  }
  ropaProductos = [
    {
      category: 'Ponchos',
      models: ['Poncho Tortuga', 'Poncho Sada', 'Ponchos Greca', 'Ponchos Andinos'],
      sizes: ['14', '16', 'S', 'M', 'L'],
      colors: ['Azul', 'Beige', 'Café', 'Jaspeado', 'Llamita', 'Gris', 'Marengo', 'Negro', 'Plomo Plata'],
      image: 'assets/poncho.jpg'
    },
    {
      category: 'Capas',
      models: ['Capa Misa', 'Capa Amaris', 'Capa Hino', 'Capa Sumaq'],
      sizes: ['Estándar'],
      colors: ['Azul', 'Beige', 'Café', 'Jaspeado', 'Llamita', 'Gris', 'Marengo', 'Negro', 'Plomo Plata'],
      image: 'assets/capas.jpg'
    },
    {
      category: 'Chalecos',
      models: ['Chaleco Cielo', 'Chaleco Bela', 'Chaleco Estrella'],
      sizes: ['S', 'M', 'L'],
      colors: ['Azul', 'Beige', 'Café', 'Jaspeado', 'Llamita', 'Gris', 'Marengo', 'Negro', 'Plomo Plata'],
      image: 'assets/chaleco.jpg'
    },
    {
      category: 'Cardigan',
      models: ['Cardigan Camila', 'Cardigan Chino', 'Cardigan Andrea'],
      sizes: ['S', 'M', 'L'],
      colors: ['Azul', 'Beige', 'Café', 'Jaspeado', 'Llamita', 'Gris', 'Marengo', 'Negro', 'Plomo Plata'],
      image: 'assets/cardigan.jpg'
    },
    {
      category: 'Polivestido',
      models: ['Polivestido Tortuga', 'Polivestido Oriana', 'Polivestido Capela'],
      sizes: ['Estándar'],
      colors: ['Azul', 'Beige', 'Café', 'Jaspeado', 'Llamita', 'Gris', 'Marengo', 'Negro', 'Plomo Plata'],
      image: 'assets/polivestido.jpg'
    },
    {
      category: 'Sweater',
      models: ['Sweater Capucha'],
      sizes: ['S', 'M', 'L'],
      colors: ['Azul', 'Beige', 'Café', 'Jaspeado', 'Llamita', 'Gris', 'Marengo', 'Negro', 'Plomo Plata'],
      image: 'assets/sweater.jpg'
    }
  ];


  accesoriosProductos = [
    {
      category: 'Mochilas',
      models: ['Mochila Camila', 'Machila A4'],
      colors: ['Amarillo', 'Azul', 'Bolívar', 'Celeste', 'Morado', 'Negro', 'Plomo', 'Plomo Plata', 'Rojo', 'Rosado', 'Verde'],
      image: 'assets/mochilas.jpg'
    },
    {
      category: 'Neceser',
      models: ['Neceser Serena', 'Neceser Aria'],
      colors: ['Amarillo', 'Azul', 'Bolívar', 'Celeste', 'Morado', 'Negro', 'Plomo', 'Plomo Plata', 'Rojo', 'Rosado', 'Verde'],
      image: 'assets/neceser.jpg'
    },
    {
      category: 'Chalinas',
      models: ['Chalina Alpacril', 'Chalina de Dralón'],
      colors: ['Amarillo', 'Azul', 'Bolívar', 'Celeste', 'Morado', 'Negro', 'Plomo', 'Plomo Plata', 'Rojo', 'Rosado', 'Verde'],
      image: 'assets/chalinas.jpg'
    },
    {
      category: 'Monederos',
      models: [
        'Sol: Todo el cuerpo es con tela tejida andina',
        'Luna: Con cuero alrededor de la parte media'
      ],
      colors: ['Amarillo', 'Azul', 'Bolívar', 'Celeste', 'Morado', 'Negro', 'Plomo', 'Plomo Plata', 'Rojo', 'Rosado', 'Verde'],
      image: 'assets/monederos.jpg'
    },
    {
      category: 'Boina y Manoplas',
      models: [
        'Manoplas y boina tejidas para realzar tu esencia.',
        'Dale a tu outfit un toque sofisticado con este set hecho para destacar.'
      ],
      colors: ['Amarillo', 'Azul', 'Bolívar', 'Celeste', 'Morado', 'Negro', 'Plomo', 'Plomo Plata', 'Rojo', 'Rosado', 'Verde'],
      image: 'assets/boina-manoplas.jpg'
    }
  ];

  hogarProductos = [
    {
      category: 'Sobrecama',
      models: [
        'Mantas de alpacril versátiles y multifuncionales, acogedoras y súper suaves son perfectas para mantenerte cómodo en el sofá, relajarte mientras ves una película o añadir un toque de estilo y decoración a tu hogar. Diseñadas para un uso durante todo el año, estas mantas ligeras son fáciles de cuidar y son el regalo perfecto.'
      ],
      talla: 'Estándar',
      image: 'assets/sobrecama.jpg'
    },
    {
      category: 'Cojín Alpacril',
      models: [
        'Mantas de alpacril versátiles y multifuncionales, acogedoras y súper suaves son perfectas para mantenerte cómodo en el sofá, relajarte mientras ves una película o añadir un toque de estilo y decoración a tu hogar. Diseñadas para un uso durante todo el año, estas mantas ligeras son fáciles de cuidar y son el regalo perfecto.'
      ],
      talla: 'Estándar',
      colors: ['Azul', 'Beige', 'Café', 'Jaspeado', 'Llamita', 'Gris', 'Marengo', 'Negro', 'Plomo Plata'],
      image: 'assets/cojin-alpacril.jpg'
    },
    {
      category: 'Mantas Multicolor',
      talla: 'Estándar',
      colors: ['Azul', 'Beige', 'Café', 'Jaspeado', 'Llamita', 'Gris', 'Marengo', 'Negro', 'Plomo Plata'],
      image: 'assets/mantas-multicolor.jpg'
    },
    {
      category: 'Cintas Multicolor',
      talla: 'Estándar',
      colors: ['Azul', 'Beige', 'Café', 'Jaspeado', 'Llamita', 'Gris', 'Marengo', 'Negro', 'Plomo Plata'],
      image: 'assets/cintas-multicolor.jpg'
    },
    {
      category: 'Alpaquitas',
      talla: 'Estándar',
      colors: ['Beige', 'Café', 'Blanco'],
      image: 'assets/alpaquitas.jpg'
    }
  ];
  serviciosProductos = [
    {
      category: 'Impresión',
      models: [
        'Sublimado',
        'Vinil Textil',
        'DTF'
      ],
      image: 'assets/impresion.jpg'
    },
    {
      category: 'Sellado al vacío',
      models: [
        'Reduce hasta el 50%',
        'Aplica en prendas y alimentos'
      ],
      image: 'assets/sellado-vacio.jpg'
    }
  ];




}
