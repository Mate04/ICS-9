package TDD_CompraEntradas_tp6.demo;


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
        assertEquals(0, validator.subtotalPorEdad(2, "REGULAR"));
        assertEquals(0, validator.subtotalPorEdad(2, "VIP"));
    }

    @Test
    void testCalculoedad4a16() {
        assertEquals(5000, validator.subtotalPorEdad(4, "VIP"));
        assertEquals(2500, validator.subtotalPorEdad(15, "REGULAR"));
    }

    @Test
    void testcalculoedad16a59() {
        assertEquals(10000, validator.subtotalPorEdad(16, "VIP"));
        assertEquals(5000, validator.subtotalPorEdad(59,"REGULAR"));
    }

    @Test
    void testCalculoedadmayor60() {
        assertEquals(5000, validator.subtotalPorEdad(60,"VIP"));
        assertEquals(2500, validator.subtotalPorEdad(99, "REGULAR"));
    }

    @Test
    void testEdadNull() {
        assertThrows(IllegalArgumentException.class, () -> validator.subtotalPorEdad(null, "VIP"));
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

    


