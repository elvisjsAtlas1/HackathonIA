package com.example.chatbot.Repository;


import com.example.chatbot.modelo.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Long> {

    // Puedes agregar métodos personalizados si los necesitas, por ejemplo:
    // List<Catalogo> findByNombreContainingIgnoreCase(String nombre);

}