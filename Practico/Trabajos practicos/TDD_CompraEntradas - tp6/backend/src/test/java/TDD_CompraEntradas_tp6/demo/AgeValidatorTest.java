package TDD_CompraEntradas_tp6.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import TDD_CompraEntradas_tp6.demo.classes.AgeValidator;

public class AgeValidatorTest {

    private AgeValidator validator;

    @BeforeEach
    void setUp() {
        validator = new AgeValidator();
    }

    @Test
    void testEdadNoNegativa() {
        assertFalse(validator.isValidAge(-1), "La edad negativa debe ser inválida");
        assertTrue(validator.isValidAge(0), "La edad cero debe ser válida");
        assertTrue(validator.isValidAge(25), "Una edad positiva debe ser válida");
    }

    @Test
    void testEdadMaximaValida() {
        assertTrue(validator.isValidAge(101), "La edad 101 debe ser válida");
        assertFalse(validator.isValidAge(102), "La edad mayor a 100 debe ser inválida");
    }

    @Test 
    void testMenorDe3NoPaga() {
        // Precondiciones
        int precioEntradaSinDescuento = 100;

        assertEquals(0, validator.subtotalAPagarPorEdad(0, precioEntradaSinDescuento), "Los menores de 3 años no pagan");
        assertEquals(0, validator.subtotalAPagarPorEdad(2, precioEntradaSinDescuento), "Los menores de 3 años no pagan");
    }

    @Test
    void testEdadEntre4y15descuento50() {
        // Precondiciones
        int precioEntradaSinDescuento = 101;
        assertEquals(50, validator.subtotalAPagarPorEdad(4, precioEntradaSinDescuento), "Los niños de 4 a 15 años tienen un descuento del 50%");
    }

    @Test
    void testMayorA60descuento50() {
        // Precondiciones
        int precioEntradaSinDescuento = 201;
        assertEquals(100, validator.subtotalAPagarPorEdad(61, precioEntradaSinDescuento), "Los mayores de 60 años tienen un descuento del 50%");
    }

    @Test
    void testEdadRegularSinDescuento() {
        // Precondiciones
        int precioEntradaSinDescuento = 150;
        assertEquals(precioEntradaSinDescuento, validator.subtotalAPagarPorEdad(30, precioEntradaSinDescuento), "Las edades entre 16 y 60 años pagan el precio completo");
    }

}

    
