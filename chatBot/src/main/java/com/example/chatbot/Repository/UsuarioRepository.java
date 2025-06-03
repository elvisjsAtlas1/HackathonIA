package com.example.chatbot.Repository;


import com.example.chatbot.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Si necesitas buscar por username, email u otro campo, puedes agregar métodos como:
    // Optional<Usuario> findByUsername(String username);

}