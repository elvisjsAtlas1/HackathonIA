package com.example.chatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatalogoDTO {
    private Long id;
    private String nombre;
    private String descripcion;


    // Lista de productos directamente como mapa de datos (si no usas ProductoDTO)
    private List<ProductoDTO> productos;
}