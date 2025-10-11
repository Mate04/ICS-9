package TDD_CompraEntradas_tp6.demo;

import TDD_CompraEntradas_tp6.demo.classes.TicketsValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketsValidatorTest {
    // VALIDOS
    @Test
    public void testCantidadValidaMinima() {
        assertTrue(TicketsValidator.esCantidadValida("1")); // borde inferior
    }

    @Test
    public void testCantidadValidaMedia() {
        assertTrue(TicketsValidator.esCantidadValida("5")); // valor intermedio
    }

    @Test
    public void testCantidadValidaMaxima() {
        assertTrue(TicketsValidator.esCantidadValida("10")); // borde superior
    }

    // INVALIDOS
    // DE RANGO
    @Test
    public void testCantidadCero() {
        assertFalse(TicketsValidator.esCantidadValida("0")); // menor que mínimo
    }

    @Test
    public void testCantidadMayorQueDiez() {
        assertFalse(TicketsValidator.esCantidadValida("11")); // mayor que máximo
    }

    // DE FORMATO
    @Test
    public void testCantidadNoNumerica() {
        assertFalse(TicketsValidator.esCantidadValida("abc")); // texto no convertible
    }

    @Test
    public void testCantidadDecimal() {
        assertFalse(TicketsValidator.esCantidadValida("3.5")); // número decimal
    }

    @Test
    public void testCantidadNegativa() {
        assertFalse(TicketsValidator.esCantidadValida("-2")); // número negativo
    }

    @Test
    public void testCantidadVacia() {
        assertFalse(TicketsValidator.esCantidadValida("")); // string vacío
    }

    @Test
    public void testCantidadNula() {
        assertFalse(TicketsValidator.esCantidadValida(null)); // valor nulo
    }

    @Test
    public void testTipoEntradaValido() {
        assertTrue(TicketsValidator.esEntradaValida("Regular"));
    }

    @Test
    public void testTipoEntradaVacia() {
        assertFalse(TicketsValidator.esEntradaValida(""));
    }

    @Test
    public void testTipoEntradaNula() {
        assertFalse(TicketsValidator.esEntradaValida(null));
    }
}
