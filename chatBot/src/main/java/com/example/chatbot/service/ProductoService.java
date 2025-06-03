package com.example.chatbot.service;
import com.example.chatbot.Repository.ProductoRepository;
import com.example.chatbot.dto.ProductoDTO;

import com.example.chatbot.mapper.ProductoMapper;
import com.example.chatbot.modelo.Producto;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ProductoService(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    // Obtener todos los productos
    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar producto por ID
    public ProductoDTO getProductoById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        return productoMapper.toDTO(producto);
    }

    // Crear nuevo producto
    public ProductoDTO createProducto(ProductoDTO productoDTO) {
        Producto producto = productoMapper.toEntity(productoDTO);
        Producto savedProducto = productoRepository.save(producto);
        return productoMapper.toDTO(savedProducto);
    }

    // Actualizar producto
    public ProductoDTO updateProducto(Long id, ProductoDTO productoDTO) {
        Producto existingProducto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        // Actualiza campos
        existingProducto.setNombre(productoDTO.getNombre());
        existingProducto.setDescripcion(productoDTO.getDescripcion());
        existingProducto.setPrecio(productoDTO.getPrecio());
        existingProducto.setImagenUrl(productoDTO.getImagenUrl());
        existingProducto.setDisponible(productoDTO.isDisponible());

        Producto updatedProducto = productoRepository.save(existingProducto);
        return productoMapper.toDTO(updatedProducto);
    }

    // Eliminar producto
    public void deleteProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }

    public List<ProductoDTO> getProductosNuevos() {
        List<Producto> nuevosProductos = productoRepository.findTop10ByOrderByFechaCreacionDesc();
        return nuevosProductos.stream()
                .map(productoMapper::toDTO)  // usa el mapper que ya tienes
                .collect(Collectors.toList());
    }

}