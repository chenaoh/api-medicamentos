package com.ejemplo.api_medicamentos.repository;

import com.ejemplo.api_medicamentos.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository
        extends JpaRepository<Medicamento, Long> {

}