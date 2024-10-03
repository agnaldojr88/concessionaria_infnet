package com.agnjr.concessionaria.controller;

import com.agnjr.concessionaria.model.Caminhao;
import com.agnjr.concessionaria.model.Categoria;
import com.agnjr.concessionaria.service.CaminhaoService;
import com.agnjr.concessionaria.service.CategoriaService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/api/caminhoes")
public class CaminhaoController {

    @Autowired
    private CaminhaoService caminhaoService;

    @Autowired
    private CategoriaService categoriaService;


    @PostMapping
    public ResponseEntity<Caminhao> incluirCaminhao(@Valid @RequestBody Caminhao caminhao) {
        Categoria categoria = categoriaService.obterPorId(caminhao.getCategoria().getId());
        if (categoria == null) {
            return ResponseEntity.badRequest().build();
        }
        caminhao.setCategoria(categoria);
        caminhaoService.incluir(caminhao);
        return ResponseEntity.ok(caminhao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCaminhao(@PathVariable Long id) {
        Caminhao caminhao = caminhaoService.obterPorId(id);
        if (caminhao == null) {
            return ResponseEntity.notFound().build();
        }
        caminhaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Caminhao>> obterCaminhoes() {
        Collection<Caminhao> caminhoes = caminhaoService.obterLista();
        return ResponseEntity.ok(caminhoes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Caminhao> obterCaminhaoPorId(@PathVariable Long id) {
        Caminhao caminhao = caminhaoService.obterPorId(id);
        if (caminhao == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(caminhao);
    }
}