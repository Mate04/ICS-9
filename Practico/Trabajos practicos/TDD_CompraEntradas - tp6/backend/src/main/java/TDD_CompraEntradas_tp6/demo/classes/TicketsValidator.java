package TDD_CompraEntradas_tp6.demo.classes;

public class TicketsValidator {
    public static boolean esCantidadValida(String cantidadStr) {
        if (cantidadStr == null || cantidadStr.trim().isEmpty()) return false;
        try {
            int cantidad = Integer.parseInt(cantidadStr.trim());
            return cantidad >= 1 && cantidad <= 10;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    public static boolean esEntradaValida(String tipoEntrada) {
        return tipoEntrada != null && !tipoEntrada.trim().isEmpty();
    }
}
