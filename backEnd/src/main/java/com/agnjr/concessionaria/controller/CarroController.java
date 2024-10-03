package com.agnjr.concessionaria.controller;

import com.agnjr.concessionaria.model.Carro;
import com.agnjr.concessionaria.model.Categoria;
import com.agnjr.concessionaria.service.CarroService;
import com.agnjr.concessionaria.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Carro> incluirCarro(@Valid @RequestBody Carro carro) {
        if (carro.getCategoria() == null || carro.getCategoria().getId() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Categoria categoria = categoriaService.obterPorId(carro.getCategoria().getId());
        if (categoria == null) {
            return ResponseEntity.badRequest().body(null);
        }
        carro.setCategoria(categoria);
        carroService.incluir(carro);
        return ResponseEntity.ok(carro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCarro(@PathVariable Long id) {
        Carro carro = carroService.obterPorId(id);
        if (carro == null) {
            return ResponseEntity.notFound().build();
        }
        carroService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    // Find Carros ordenados
    @GetMapping
    public ResponseEntity<List<Carro>> obterCarros(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction) {
        Sort sort = Sort.unsorted();
        if (sortBy != null && direction != null) {
            if (direction.equalsIgnoreCase("asc")) {
                sort = Sort.by(sortBy).ascending();
            } else if (direction.equalsIgnoreCase("desc")) {
                sort = Sort.by(sortBy).descending();
            }
        }
        List<Carro> carros = carroService.obterListaComOrdenacao(sort);
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> obterCarroPorId(@PathVariable Long id) {
        Carro carro = carroService.obterPorId(id);
        if (carro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carro);
    }

    // Find Carros por fabricante
    @GetMapping("/fabricante/{fabricante}")
    public ResponseEntity<List<Carro>> buscarCarrosPorFabricante(@PathVariable String fabricante) {
        List<Carro> carros = (List<Carro>) carroService.buscarPorFabricante(fabricante);
        if (carros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carros);
    }
}