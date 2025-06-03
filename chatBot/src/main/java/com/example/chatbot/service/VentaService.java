package com.example.chatbot.service;

import com.example.chatbot.Repository.ProductoRepository;
import com.example.chatbot.Repository.UsuarioRepository;
import com.example.chatbot.Repository.VentaRepository;
import com.example.chatbot.dto.VentaDTO;
import com.example.chatbot.modelo.Venta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    public VentaService(VentaRepository ventaRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    public List<VentaDTO> getVentasByUsuarioId(Long usuarioId) {
        List<Venta> ventas = ventaRepository.findByUsuarioId(usuarioId);
        return ventas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<VentaDTO> getAllVentas() {
        List<Venta> ventas = ventaRepository.findAll();
        return ventas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public VentaDTO getVentaById(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Venta no encontrada con id: " + id));
        return convertToDTO(venta);
    }

    public VentaDTO createVenta(VentaDTO ventaDTO) {
        Venta venta = new Venta();
        venta.setCantidad(ventaDTO.getCantidad());
        venta.setFechaVenta(ventaDTO.getFechaVenta());
        venta.setTotal(ventaDTO.getTotal());

        venta.setUsuario(usuarioRepository.findById(ventaDTO.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + ventaDTO.getUsuarioId())));
        venta.setProducto(productoRepository.findById(ventaDTO.getProductoId())
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con id: " + ventaDTO.getProductoId())));

        Venta ventaGuardada = ventaRepository.save(venta);
        return convertToDTO(ventaGuardada);
    }

    public VentaDTO updateVenta(Long id, VentaDTO ventaDTO) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Venta no encontrada con id: " + id));

        venta.setCantidad(ventaDTO.getCantidad());
        venta.setFechaVenta(ventaDTO.getFechaVenta());
        venta.setTotal(ventaDTO.getTotal());

        venta.setUsuario(usuarioRepository.findById(ventaDTO.getUsuarioId())
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + ventaDTO.getUsuarioId())));
        venta.setProducto(productoRepository.findById(ventaDTO.getProductoId())
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con id: " + ventaDTO.getProductoId())));

        Venta ventaActualizada = ventaRepository.save(venta);
        return convertToDTO(ventaActualizada);
    }

    public void deleteVenta(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new NoSuchElementException("Venta no encontrada con id: " + id);
        }
        ventaRepository.deleteById(id);
    }

    private VentaDTO convertToDTO(Venta venta) {
        VentaDTO dto = new VentaDTO();
        dto.setId(venta.getId());
        dto.setUsuarioId(venta.getUsuario().getId());
        dto.setProductoId(venta.getProducto().getId());
        dto.setCantidad(venta.getCantidad());
        dto.setFechaVenta(venta.getFechaVenta());
        dto.setTotal(venta.getTotal());
        return dto;
    }
}
