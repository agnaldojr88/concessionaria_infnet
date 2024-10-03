package com.agnjr.concessionaria.controller;

import com.agnjr.concessionaria.model.Categoria;
import com.agnjr.concessionaria.model.Moto;
import com.agnjr.concessionaria.service.CategoriaService;
import com.agnjr.concessionaria.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/api/motos")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Moto> incluirMoto(@Valid @RequestBody Moto moto) {
        Categoria categoria = categoriaService.obterPorId(moto.getCategoria().getId());
        if (categoria == null) {
            return ResponseEntity.badRequest().build();
        }
        moto.setCategoria(categoria);
        motoService.incluir(moto);
        return ResponseEntity.ok(moto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirMoto(@PathVariable Long id) {
        Moto moto = motoService.obterPorId(id);
        if (moto == null) {
            return ResponseEntity.notFound().build();
        }
        motoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Moto>> obterMotos() {
        Collection<Moto> motos = motoService.obterLista();
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> obterMotoPorId(@PathVariable Long id) {
        Moto moto = motoService.obterPorId(id);
        if (moto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moto);
    }
}