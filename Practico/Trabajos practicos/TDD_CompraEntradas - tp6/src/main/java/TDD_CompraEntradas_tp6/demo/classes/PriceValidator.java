package TDD_CompraEntradas_tp6.demo.classes;

import java.util.List;

public class PriceValidator {
    public static final int VIP_PRICE = 10000;
    public static final int REGULAR_PRICE = 5000;

    public int subtotalPorEdad(int edad, int precioEntrada) {
        if (edad < 3) return 0;
        if ((edad >= 5 && edad <= 15) || edad > 60) return precioEntrada / 2;
        return precioEntrada;
    }

    public int calcularTotal(List<Integer> edades, List<String> tiposDeEntrada) {
        if (edades.size() != tiposDeEntrada.size()) {
            throw new IllegalArgumentException("La lista de edades y tipos debe tener la misma cantidad de elementos");
        }

        int total = 0;
        for (int i = 0; i < edades.size(); i++) {
            int precio = tiposDeEntrada.get(i).equalsIgnoreCase("VIP") ? VIP_PRICE : REGULAR_PRICE;
            total += subtotalPorEdad(edades.get(i), precio);
        }
        return total;
    }

    public int calcularTotalSinDescuentos(List<String> tiposDeEntrada) {
        int total = 0;
        for (String tipo : tiposDeEntrada) {
            total += tipo.equalsIgnoreCase("VIP") ? VIP_PRICE : REGULAR_PRICE;
        }
        return total;
    }
    
}
