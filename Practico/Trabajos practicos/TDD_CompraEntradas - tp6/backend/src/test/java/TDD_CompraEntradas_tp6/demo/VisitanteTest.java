package TDD_CompraEntradas_tp6.demo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import TDD_CompraEntradas_tp6.demo.clases.Visitante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class VisitanteTest {

    private Visitante validator;

    @BeforeEach
    void setUp() {
        validator = new Visitante();
    }

    @Test
    void testEdadNoNegativa() {
        assertFalse(validator.isValidAge(-1), "La edad negativa debe ser inválida");
        assertTrue(validator.isValidAge(0), "La edad cero debe ser válida");
        assertTrue(validator.isValidAge(100), "Una edad positiva debe ser válida");
    }


    @Test
    void testEdadMaximaValida() {
        assertFalse(validator.isValidAge(101), "La edad mayor a 100 debe ser inválida");
    }

    @Test
    void testEdadNull() {
        assertThrows(IllegalArgumentException.class, () ->validator.isValidAge(null));
    }

    @Test
    void testCaracterDistintoNumerico() {
        assertThrows(IllegalArgumentException.class, () -> validator.isValidAge("aa"));
    }

    @Test
    void testTipoEntradaNull() {
        assertThrows(IllegalArgumentException.class, () -> validator.isValidTipoEntrada(null));
    }

    @Test
    void testTipoRegularyVIP() {
        assertTrue(validator.isValidTipoEntrada("REGULAR"), "La entrda es Regular");
        assertFalse(validator.isValidTipoEntrada("REGULARR"), "No se reconoce el tipo de entrada");
        assertTrue(validator.isValidTipoEntrada("VIP"), "La entrda es VIP");
    }


}
