package TDD_CompraEntradas_tp6.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TDD_CompraEntradas_tp6.demo.classes.ClasesParaValidaciones.Resumen;
import TDD_CompraEntradas_tp6.demo.classes.ConfirmacionYResumen;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.List;

public class ConfirmacionYResumenTest {

    private ConfirmacionYResumen resumen;

    @BeforeEach
    void setUp() {
        resumen = new ConfirmacionYResumen();
    }

    @Test
    void testGenerarCodigoCompraUnico() {
        
        List<String> codigosExistentes = Arrays.asList("ABC123", "DEF456", "GHI789");

        assertTrue(resumen.validateCodigoUnico(codigosExistentes, "ADFDFF"));
    }

    @Test
    void testGenerarCodigoCompraNoUnico() {
        
        List<String> codigosExistentes = Arrays.asList("ABC123", "DEF456", "GHI789");
        
        assertFalse(resumen.validateCodigoUnico(codigosExistentes, "DEF456"));
    }
    
    @Test
    void testGenerarResumenCompra() {
        // Implementar test para generar resumen de compra
        // Verificar que el resumen contenga la cantidad correcta de entradas y la fecha
    
        assertTrue(resumen.validateResumen(new Resumen(3, java.time.LocalDate.now())));
        assertFalse(resumen.validateResumen(null));
        assertFalse(resumen.validateResumen(new Resumen(0, java.time.LocalDate.now())));
        assertFalse(resumen.validateResumen(new Resumen(3, null)));
    }

}

