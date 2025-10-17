package TDD_CompraEntradas_tp6.demo.classes;

import java.time.LocalDate;
import java.util.Objects;
import TDD_CompraEntradas_tp6.demo.classes.ClasesParaValidaciones.Resumen;

public class ConfirmacionYResumen {

    public boolean validateCodigoUnico(java.util.List<String> codigosExistentes, String nuevoCodigo) {
        return !codigosExistentes.contains(nuevoCodigo);
    }

    public boolean validateResumen(Resumen resumen) {
        // Null check del objeto resumen
        if (resumen == null) {
            return false;
        }

        // Validar cantidad de entradas: debe existir y ser mayor a 0
        int cantidad = resumen.getCantidadEntradas();
        if (cantidad <= 0) {
            return false;
        }

        // Validar fecha: no nula y no futura
        LocalDate fecha = resumen.getFecha();
        if (Objects.isNull(fecha)) {
            return false;
        }

        LocalDate hoy = LocalDate.now();
        if (fecha.isAfter(hoy)) {
            return false;
        }

        return true;
    }

}
