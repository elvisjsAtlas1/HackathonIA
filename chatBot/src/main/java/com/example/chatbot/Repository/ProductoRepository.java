package com.example.chatbot.Repository;



import com.example.chatbot.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findTop10ByOrderByFechaCreacionDesc();
    // Aquí puedes agregar consultas personalizadas si necesitas, por ejemplo:
    // List<Producto> findByNombreContainingIgnoreCase(String nombre);

}