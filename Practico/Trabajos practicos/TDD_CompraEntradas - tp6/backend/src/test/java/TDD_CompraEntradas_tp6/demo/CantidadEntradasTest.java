package TDD_CompraEntradas_tp6.demo;

import TDD_CompraEntradas_tp6.demo.clases.Visitante;
import TDD_CompraEntradas_tp6.demo.controller.dto.req.VisitanteDTO;
import TDD_CompraEntradas_tp6.demo.utils.CantidadEntradasValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CantidadEntradasTest {

    private CantidadEntradasValidator validator;

    @BeforeEach
    void setUp() {
        validator = new CantidadEntradasValidator();
    }

    @Test
    void testCantidadEntradasValidas1() {
        List<VisitanteDTO> visitantes = List.of(new VisitanteDTO());
        assertTrue(validator.esCantidadValida(visitantes), "La cantidad 1 debe ser válida");
    }

    @Test
    void testCantidadEntradasValidas10() {
        List<VisitanteDTO> visitantes = java.util.stream.IntStream.range(0, 10)
                .mapToObj(i -> new VisitanteDTO())
                .toList();
        assertTrue(validator.esCantidadValida(visitantes), "La cantidad 10 debe ser válida");
    }

    @Test
    void testCantidadEntradasValidas11() {
        List<VisitanteDTO> visitantes = java.util.stream.IntStream.range(0, 11)
                .mapToObj(i -> new VisitanteDTO())
                .toList();
        assertFalse(validator.esCantidadValida(visitantes), "La cantidad 11 debe ser inválida");
    }

    @Test
    void testCantidadEntradasValidas0() {
        List<VisitanteDTO> visitantes = List.of();
        assertFalse(validator.esCantidadValida(visitantes), "La cantidad 0 debe ser inválida");
    }

    @Test
    void testCantidadEntradasValidasnull() {
        assertFalse(validator.esCantidadValida(null), "La cantidad no puede ser null");
    }

}
