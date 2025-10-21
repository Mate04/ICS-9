package TDD_CompraEntradas_tp6.demo;

import TDD_CompraEntradas_tp6.demo.entities.TipoEntrada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import TDD_CompraEntradas_tp6.demo.utils.SubTotalPorVisitante;

public class SubTotalPorVisitanteTest {

    private SubTotalPorVisitante validator;

    @BeforeEach
    void setUp() {
        validator = new SubTotalPorVisitante();
    }

    @Test
    void testCalculoMenores() {
        assertEquals(0, validator.subtotalPorEdad(2, TipoEntrada.REGULAR));
        assertEquals(0, validator.subtotalPorEdad(2, TipoEntrada.VIP));
    }

    @Test
    void testCalculoEdad4a16() {
        assertEquals(5000, validator.subtotalPorEdad(4, TipoEntrada.VIP));
        assertEquals(2500, validator.subtotalPorEdad(15, TipoEntrada.REGULAR));
    }

    @Test
    void testCalculoEdad16a59() {
        assertEquals(10000, validator.subtotalPorEdad(16, TipoEntrada.VIP));
        assertEquals(5000, validator.subtotalPorEdad(59, TipoEntrada.REGULAR));
    }

    @Test
    void testCalculoEdadMayor60() {
        assertEquals(5000, validator.subtotalPorEdad(60, TipoEntrada.VIP));
        assertEquals(2500, validator.subtotalPorEdad(99, TipoEntrada.REGULAR));
    }

    @Test
    void testEdadNull() {
        assertThrows(IllegalArgumentException.class, () -> validator.subtotalPorEdad(null, TipoEntrada.VIP));
    }

    @Test
    void testTipoEntradaNull() {
        assertThrows(IllegalArgumentException.class, () -> validator.subtotalPorEdad(25, null));
    }

    @Test
    void testAmbosNull() {
        assertThrows(IllegalArgumentException.class, () -> validator.subtotalPorEdad(null, null));
    }
}
