package TDD_CompraEntradas_tp6.demo;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Valida si una fecha cumple con el formato yyyy-MM-dd y no es anterior a la fecha actual.
     * @param dateString la fecha en texto
     * @return true si la fecha es válida, false si no lo es
     */
    public boolean isValidDate(String dateString) {
        throw new UnsupportedOperationException("Método no implementado todavía");
    }
}