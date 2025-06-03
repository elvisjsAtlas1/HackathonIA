import {Component, ElementRef, ViewChild} from '@angular/core';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-featured-products',
  templateUrl: './featured-products.component.html',
  styleUrls: ['./featured-products.component.css'],
  standalone: true,
  imports: [CommonModule] // Aquí importas CommonModule
})
export class FeaturedProductsComponent {
  @ViewChild('carousel') carousel!: ElementRef<HTMLDivElement>;

  productos = [
    {
      name: 'Producto 1',
      description: 'Descripción breve del producto 1.',
      price: 'S/ 350.00',
      image: 'assets/product1.jpg'
    },
    {
      name: 'Producto 1',
      description: 'Descripción breve del producto 1.',
      price: 'S/ 350.00',
      image: 'assets/product1.jpg'
    },
    {
      name: 'Producto 2',
      description: 'Descripción breve del producto 1.',
      price: 'S/ 350.00',
      image: 'assets/product2.jpg'
    },
    {
      name: 'Producto 3',
      description: 'Descripción breve del producto 1.',
      price: 'S/ 350.00',
      image: 'assets/product3.jpg'
    },
    {
      name: 'Producto 4',
      description: 'Descripción breve del producto 1.',
      price: 'S/ 350.00',
      image: 'assets/product4.jpg'
    },
    {
      name: 'Producto 5',
      description: 'Descripción breve del producto 1.',
      price: 'S/ 350.00',
      image: 'assets/product5.jpg'
    },
    {
      name: 'Producto 6',
      description: 'Descripción breve del producto 1.',
      price: 'S/ 350.00',
      image: 'assets/product6.jpg'
    },
    {
      name: 'Producto 7',
      description: 'Descripción breve del producto 1.',
      price: 'S/ 350.00',
      image: 'assets/product7.jpg'
    },
    // más productos...
  ];

  scrollLeft() {
    this.carousel.nativeElement.scrollBy({ left: -250, behavior: 'smooth' });
  }

  scrollRight() {
    this.carousel.nativeElement.scrollBy({ left: 250, behavior: 'smooth' });
  }
}
