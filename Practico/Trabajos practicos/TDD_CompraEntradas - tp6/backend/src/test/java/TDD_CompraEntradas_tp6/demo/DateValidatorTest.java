package TDD_CompraEntradas_tp6.demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import TDD_CompraEntradas_tp6.demo.classes.DateValidator;

import static org.junit.jupiter.api.Assertions.*;

public class DateValidatorTest {

    private DateValidator validator;

    @BeforeEach
    void setUp() {
        validator = new DateValidator();
    }

    @Test
    void testFechaValidaHoy() {
        String today = java.time.LocalDate.now().toString(); // formato yyyy-MM-dd
        assertTrue(validator.isValidDate(today), "La fecha de hoy debe ser válida");
    }

    @Test
    void testFechaFuturaValida() {
        String futureDate = java.time.LocalDate.now().plusDays(5).toString();
        assertTrue(validator.isValidDate(futureDate), "Una fecha futura debe ser válida");
    }

    @Test
    void testFechaPasadaInvalida() {
        String pastDate = java.time.LocalDate.now().minusDays(3).toString();
        assertFalse(validator.isValidDate(pastDate), "Una fecha pasada debe ser inválida");
    }

    @Test
    void testFormatoIncorrecto() {
        String invalidFormat = "09-10-2025"; // formato incorrecto
        assertFalse(validator.isValidDate(invalidFormat), "El formato incorrecto debe ser inválido");
    }

    @Test
    void testFechaNula() {
        assertFalse(validator.isValidDate(null), "Una fecha nula debe ser inválida");
    }

    @Test
    void testFechaLaboralInvalida(){
        String date = "2025-11-03"; //lunes
        String dateNavidad = "2025-12-25";
        String dateAñoNuevo = "2025-01-01";
        assertFalse(validator.isValidDate(date), "Una fecha en dia no laboral del parque debe ser inválida");
        assertFalse(validator.isValidDate(dateNavidad), "Una fecha navideña en dia no laboral del parque debe ser inválida");
        assertFalse(validator.isValidDate(dateAñoNuevo), "Una fecha  año nuevo en dia no laboral del parque debe ser inválida");
    }

    @Test
    void testFechaLaboralValida(){
        String date = "2025-11-04"; // Martes
        assertTrue(validator.isValidDate(date), "Una fecha en dia laboral del parque debe ser válida");
    }
}

