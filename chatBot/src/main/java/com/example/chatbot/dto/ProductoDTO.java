package com.example.chatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Anotaciones Lombok para generar getter, setters, constructor, builder etc.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagenUrl;  // URL o path de la imagen que se mostrará en frontend
    private boolean disponible;
    private LocalDateTime fechaCreacion;  // para saber si está activo o agotado
}