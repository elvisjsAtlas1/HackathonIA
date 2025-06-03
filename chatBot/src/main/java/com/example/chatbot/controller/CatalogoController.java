package com.example.chatbot.controller;
import com.example.chatbot.dto.CatalogoDTO;
import com.example.chatbot.service.CatalogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogos")
public class CatalogoController {

    private final CatalogoService catalogoService;

    public CatalogoController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    @GetMapping
    public ResponseEntity<List<CatalogoDTO>> getAllCatalogos() {
        List<CatalogoDTO> catalogos = catalogoService.getAllCatalogos();
        return ResponseEntity.ok(catalogos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogoDTO> getCatalogo(@PathVariable Long id) {
        return catalogoService.getCatalogoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CatalogoDTO> createCatalogo(@RequestBody CatalogoDTO catalogoDTO) {
        CatalogoDTO nuevoCatalogo = catalogoService.createCatalogo(catalogoDTO);
        return ResponseEntity.ok(nuevoCatalogo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatalogoDTO> updateCatalogo(@PathVariable Long id, @RequestBody CatalogoDTO catalogoDTO) {
        return catalogoService.updateCatalogo(id, catalogoDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCatalogo(@PathVariable Long id) {
        catalogoService.deleteCatalogo(id);
        return ResponseEntity.noContent().build();
    }
}