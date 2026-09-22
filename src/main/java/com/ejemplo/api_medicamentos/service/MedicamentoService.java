package com.ejemplo.api_medicamentos.service;

import com.ejemplo.api_medicamentos.model.Medicamento;
import com.ejemplo.api_medicamentos.repository.MedicamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    private final MedicamentoRepository repository;

    public MedicamentoService(MedicamentoRepository repository) {
        this.repository = repository;
    }

    public List<Medicamento> listar() {
        return repository.findAll();
    }

    public Optional<Medicamento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Medicamento guardar(Medicamento medicamento) {
        return repository.save(medicamento);
    }

    public Medicamento actualizar(Long id, Medicamento medicamento) {

        Medicamento existente = repository.findById(id)
                .orElseThrow();

        existente.setNombre(medicamento.getNombre());
        existente.setPrincipioActivo(medicamento.getPrincipioActivo());
        existente.setLaboratorio(medicamento.getLaboratorio());
        existente.setPrecio(medicamento.getPrecio());
        existente.setStock(medicamento.getStock());

        return repository.save(existente);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}