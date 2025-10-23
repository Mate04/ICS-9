package TDD_CompraEntradas_tp6.demo.utils;

import TDD_CompraEntradas_tp6.demo.clases.Visitante;
import TDD_CompraEntradas_tp6.demo.controller.dto.req.VisitanteDTO;

import java.util.List;

public class CantidadEntradasValidator {

    public static boolean esCantidadValida(List<VisitanteDTO> visitantes) {
        if (visitantes == null || visitantes.isEmpty()) return false;
        int cantidad = visitantes.size();
        return cantidad >= 1 && cantidad <= 10;
    }
}
