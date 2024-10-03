package com.agnjr.concessionaria.controller;

import com.agnjr.concessionaria.model.Categoria;
import com.agnjr.concessionaria.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> incluirCategoria(@Valid @RequestBody Categoria categoria) {
        categoriaService.incluir(categoria);
        return ResponseEntity.ok(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCategoria(@PathVariable Long id) {
        Categoria categoria = categoriaService.obterPorId(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        categoriaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Categoria>> obterCategorias() {
        Collection<Categoria> categorias = categoriaService.obterLista();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obterCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.obterPorId(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoria);
    }
}