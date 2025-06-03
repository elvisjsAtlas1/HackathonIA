package com.example.chatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDTO {
    private Long id;
    private Long usuarioId;    // Solo id de usuario
    private Long productoId;   // Solo id de producto
    private Integer cantidad;
    private LocalDateTime fechaVenta;
    private Double total;
}