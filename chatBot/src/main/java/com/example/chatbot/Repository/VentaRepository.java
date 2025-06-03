package com.example.chatbot.Repository;


import com.example.chatbot.modelo.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    // Método para obtener ventas por usuario
    List<Venta> findByUsuarioId(Long usuarioId);

    // Si quieres, puedes agregar más métodos personalizados aquí

}