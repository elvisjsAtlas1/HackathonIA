package com.example.chatbot.mapper;


import com.example.chatbot.dto.ProductoDTO;
import com.example.chatbot.modelo.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoDTO toDTO(Producto producto);

    Producto toEntity(ProductoDTO productoDTO);

}