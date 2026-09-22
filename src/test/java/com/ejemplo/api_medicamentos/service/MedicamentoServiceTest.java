package com.ejemplo.api_medicamentos.service;

import com.ejemplo.api_medicamentos.model.Medicamento;
import com.ejemplo.api_medicamentos.repository.MedicamentoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MedicamentoServiceTest {

    @Mock
    private MedicamentoRepository repository;

    @InjectMocks
    private MedicamentoService service;

    @Test
    void primeraPrueba() {
        System.out.println("La prueba funciona");
    }

    @Test
    void deberiaGuardarMedicamento() {

        Medicamento medicamento = new Medicamento(
                "Acetaminofén 500 mg",
                "Paracetamol",
                "Genfar",
                8500.0,
                100
        );

        when(repository.save(medicamento))
                .thenReturn(medicamento);

        Medicamento resultado = service.guardar(medicamento);

        assertEquals(medicamento, resultado);

        verify(repository).save(medicamento);
    }

    @Test
    void deberiaListarMedicamentos() {

        Medicamento medicamento1 = new Medicamento(
                "Acetaminofén 500 mg",
                "Paracetamol",
                "Genfar",
                8500.0,
                100
        );

        Medicamento medicamento2 = new Medicamento(
                "Ibuprofeno 400 mg",
                "Ibuprofeno",
                "MK",
                12000.0,
                80
        );

        List<Medicamento> medicamentos = List.of(medicamento1, medicamento2);

        when(repository.findAll()).thenReturn(medicamentos);

        List<Medicamento> resultado = service.listar();

        assertEquals(2, resultado.size());
        assertEquals(medicamentos, resultado);

        verify(repository).findAll();
    }

    @Test
    void deberiaBuscarMedicamentoPorId() {

        Medicamento medicamento = new Medicamento(
                "Acetaminofén 500 mg",
                "Paracetamol",
                "Genfar",
                8500.0,
                100
        );

        when(repository.findById(1L)).thenReturn(Optional.of(medicamento));

        Optional<Medicamento> resultado = service.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(medicamento, resultado.get());

        verify(repository).findById(1L);
    }

    @Test
    void deberiaRetornarVacioCuandoMedicamentoNoExiste() {

        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<Medicamento> resultado =service.buscarPorId(99L);

        assertTrue(resultado.isEmpty());

        verify(repository).findById(99L);
    }

    @Test
    void deberiaActualizarMedicamento() {

        Medicamento existente = new Medicamento(
                "Acetaminofén 500 mg",
                "Paracetamol",
                "Genfar",
                8500.0,
                100
        );

        Medicamento actualizado = new Medicamento(
                "Acetaminofén 500 mg",
                "Paracetamol",
                "Genfar",
                9000.0,
                150
        );

        when(repository.findById(1L)).thenReturn(Optional.of(existente));

        when(repository.save(existente)).thenReturn(existente);

        Medicamento resultado =service.actualizar(1L, actualizado);

        assertEquals("Acetaminofén 500 mg", resultado.getNombre());
        assertEquals(9000.0, resultado.getPrecio());
        assertEquals(150, resultado.getStock());

        verify(repository).findById(1L);
        verify(repository).save(existente);
    }

    @Test
    void deberiaEliminarMedicamento() {

        service.eliminar(1L);

        verify(repository).deleteById(1L);
    }

}