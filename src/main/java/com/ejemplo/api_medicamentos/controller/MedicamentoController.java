package com.ejemplo.api_medicamentos.controller;

import com.ejemplo.api_medicamentos.model.Medicamento;
import com.ejemplo.api_medicamentos.service.MedicamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService service;

    public MedicamentoController(MedicamentoService service) {
        this.service = service;
    }

    @GetMapping("saludo")
    public String pruebaAPI() {
        return "Este es el nuevo cambio desde nuestra API";
    }

    @GetMapping
    public List<Medicamento> listar() {
        System.out.println("Va a listar");
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> buscarPorId(@PathVariable Long id) {

        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Medicamento guardar(@RequestBody Medicamento medicamento) {
        return service.guardar(medicamento);
    }

    @PutMapping("/{id}")
    public Medicamento actualizar(
            @PathVariable Long id,
            @RequestBody Medicamento medicamento) {

        return service.actualizar(id, medicamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}