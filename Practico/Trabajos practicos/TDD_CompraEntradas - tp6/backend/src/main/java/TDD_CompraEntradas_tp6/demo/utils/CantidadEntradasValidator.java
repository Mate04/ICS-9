package TDD_CompraEntradas_tp6.demo.utils;

import java.util.List;

import TDD_CompraEntradas_tp6.demo.clases.Visitante;

public class CantidadEntradasValidator {

    public static boolean esCantidadValida(List<Visitante> visitantes) {
        if (visitantes == null || visitantes.isEmpty()) return false;
        int cantidad = visitantes.size();
        return cantidad >= 1 && cantidad <= 10;
    }
}
