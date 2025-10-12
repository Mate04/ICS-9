package TDD_CompraEntradas_tp6.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public record ConfirmacionYResumen() {

    @BeforeEach
    void setUp() {
        ConfirmacionYResumen resumen = new ConfirmacionYResumen();
    }

    @Test
    void testGenerarCodigoCompraUnico() {
        
        List<String> codigosExistentes = Arrays.asList("ABC123", "DEF456", "GHI789");
        
        assertTrue(resumen.verificarCodigoUnico(codigosExistentes, "ADFDFF"));
    }

    @Test
    
    
}
