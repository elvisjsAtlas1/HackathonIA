package com.example.chatbot.service;
import com.example.chatbot.Repository.UsuarioRepository;
import com.example.chatbot.dto.UsuarioDTO;

import com.example.chatbot.modelo.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Obtener todos los usuarios
    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener usuario por ID
    public Optional<UsuarioDTO> getUsuarioById(Long id) {
        return usuarioRepository.findById(id).map(this::convertToDTO);
    }

    // Crear nuevo usuario
    public UsuarioDTO createUsuario(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario.setTelefono(dto.getTelefono());
        Usuario saved = usuarioRepository.save(usuario);
        return convertToDTO(saved);
    }

    // Actualizar usuario
    public Optional<UsuarioDTO> updateUsuario(Long id, UsuarioDTO dto) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        if (optUsuario.isEmpty()) {
            return Optional.empty();
        }
        Usuario usuario = optUsuario.get();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());

        Usuario updated = usuarioRepository.save(usuario);
        return Optional.of(convertToDTO(updated));
    }

    // Eliminar usuario
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Conversión Entity -> DTO
    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        return dto;
    }
}