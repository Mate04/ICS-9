package TDD_CompraEntradas_tp6.demo.utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FechaValidator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Valida si una fecha cumple con el formato yyyy-MM-dd y no es anterior a la fecha actual.
     * @param dateString la fecha en texto
     * @return true si la fecha es válida, false si no lo es
     */
    public static boolean isValidDate(String dateString) {
        if (dateString == null) return false;
        try {
            LocalDate date = LocalDate.parse(dateString, FORMATTER);
            if (date.isBefore(LocalDate.now())) return false;
            if (date.getDayOfWeek() == java.time.DayOfWeek.MONDAY) return false;
            if ((date.getMonthValue() == 12 && date.getDayOfMonth() == 25) || (date.getMonthValue() == 1 && date.getDayOfMonth() == 1)) return false;
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}