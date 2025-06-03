package com.example.chatbot.service;

import com.example.chatbot.Repository.CatalogoRepository;
import com.example.chatbot.Repository.ProductoRepository;
import com.example.chatbot.dto.CatalogoDTO;

import com.example.chatbot.dto.ProductoDTO;
import com.example.chatbot.modelo.Catalogo;
import com.example.chatbot.modelo.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogoService {

    private final CatalogoRepository catalogoRepository;
    private final ProductoRepository productoRepository;

    // Obtener todos los catálogos
    public List<CatalogoDTO> getAllCatalogos() {
        List<Catalogo> catalogos = catalogoRepository.findAll();
        return catalogos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener catálogo por ID
    public Optional<CatalogoDTO> getCatalogoById(Long id) {
        return catalogoRepository.findById(id).map(this::convertToDTO);
    }

    // Crear nuevo catálogo
    public CatalogoDTO createCatalogo(CatalogoDTO dto) {
        Catalogo catalogo = convertToEntity(dto);
        Catalogo saved = catalogoRepository.save(catalogo);
        return convertToDTO(saved);
    }

    // Actualizar catálogo
    public Optional<CatalogoDTO> updateCatalogo(Long id, CatalogoDTO dto) {
        Optional<Catalogo> optCatalogo = catalogoRepository.findById(id);
        if (optCatalogo.isEmpty()) {
            return Optional.empty();
        }
        Catalogo catalogo = optCatalogo.get();

        // Actualizar campos simples
        catalogo.setNombre(dto.getNombre());
        catalogo.setDescripcion(dto.getDescripcion());

        // Actualizar productos
        if (dto.getProductos() != null) {
            List<Long> productoIds = dto.getProductos().stream()
                    .map(ProductoDTO::getId)
                    .collect(Collectors.toList());
            List<Producto> productos = productoRepository.findAllById(productoIds);
            catalogo.setProductos(Set.copyOf(productos));
        }

        Catalogo updated = catalogoRepository.save(catalogo);
        return Optional.of(convertToDTO(updated));
    }

    // Eliminar catálogo
    public void deleteCatalogo(Long id) {
        catalogoRepository.deleteById(id);
    }


    private Catalogo convertToEntity(CatalogoDTO dto) {
        Catalogo catalogo = new Catalogo();
        catalogo.setNombre(dto.getNombre());
        catalogo.setDescripcion(dto.getDescripcion());

        if (dto.getProductos() != null && !dto.getProductos().isEmpty()) {
            // Extraemos solo los IDs de productos del DTO
            List<Long> productoIds = dto.getProductos().stream()
                    .map(ProductoDTO::getId)
                    .collect(Collectors.toList());
            // Traemos las entidades Producto por esos IDs
            List<Producto> productos = productoRepository.findAllById(productoIds);
            catalogo.setProductos(Set.copyOf(productos));
        }

        return catalogo;
    }

    private CatalogoDTO convertToDTO(Catalogo catalogo) {
        CatalogoDTO dto = new CatalogoDTO();
        dto.setId(catalogo.getId());
        dto.setNombre(catalogo.getNombre());
        dto.setDescripcion(catalogo.getDescripcion());

        if (catalogo.getProductos() != null) {
            List<ProductoDTO> productosDTO = catalogo.getProductos().stream()
                    .map(producto -> {
                        ProductoDTO productoDTO = new ProductoDTO();
                        productoDTO.setId(producto.getId());
                        productoDTO.setNombre(producto.getNombre());
                        productoDTO.setDescripcion(producto.getDescripcion());
                        productoDTO.setPrecio(producto.getPrecio());
                        productoDTO.setImagenUrl(producto.getImagenUrl());
                        productoDTO.setDisponible(producto.isDisponible());
                        productoDTO.setFechaCreacion(producto.getFechaCreacion());
                        return productoDTO;
                    })
                    .collect(Collectors.toList());

            dto.setProductos(productosDTO);
        }

        return dto;
    }


}