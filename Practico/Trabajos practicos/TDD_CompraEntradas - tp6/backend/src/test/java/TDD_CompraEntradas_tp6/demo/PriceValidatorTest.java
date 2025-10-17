package TDD_CompraEntradas_tp6.demo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import TDD_CompraEntradas_tp6.demo.classes.PriceValidator;
import java.util.List;

public class PriceValidatorTest {

    private PriceValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PriceValidator();
    }

    @Test
    void testCalculoTotalUnaEntradaConDescuento() {
        assertEquals(5000, validator.calcularTotal(List.of(10), List.of("VIP")));
        assertEquals(2500, validator.calcularTotal(List.of(10), List.of("REGULAR")));
        assertEquals(5000, validator.calcularTotal(List.of(65), List.of("REGULAR")));
        assertEquals(0, validator.calcularTotal(List.of(2), List.of("VIP")));
    }

    @Test
    void testCalculoTotalMultiplesEntradasConDescuento() {
        List<Integer> edades = List.of(10, 2, 65, 30);
        List<String> tipos = List.of("VIP", "REGULAR", "VIP", "REGULAR");
        assertEquals(15000, validator.calcularTotal(edades, tipos));
    }

    @Test
    void testTotalSinDescuentosUnaEntrada() {
        assertEquals(10000, validator.calcularTotalSinDescuentos(List.of("VIP")));
        assertEquals(5000, validator.calcularTotalSinDescuentos(List.of("REGULAR")));
    }

    @Test
    void testTotalSinDescuentosMultiplesEntradas() {
        List<String> tipos = List.of("VIP", "REGULAR", "VIP", "REGULAR");
        assertEquals(30000, validator.calcularTotalSinDescuentos(tipos));
    }
}

    


