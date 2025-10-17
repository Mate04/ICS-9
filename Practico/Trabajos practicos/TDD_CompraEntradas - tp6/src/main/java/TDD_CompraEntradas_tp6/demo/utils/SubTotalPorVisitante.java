package TDD_CompraEntradas_tp6.demo.utils;


public class SubTotalPorVisitante {
    public static final int VIP_PRICE = 10000;
    public static final int REGULAR_PRICE = 5000;

    public static int subtotalPorEdad(Integer edad, String tipoEntrada) {
        if (edad == null || tipoEntrada == null) {
            throw new IllegalArgumentException("Edad y tipoEntrada no pueden ser null");
        }
        if (edad < 4) return 0;
        if ((edad <= 15) || (edad > 59)) {
            if (tipoEntrada.equals("VIP")) { return VIP_PRICE / 2; }
            else { return REGULAR_PRICE / 2; }
        } else {
            if (tipoEntrada.equals("VIP")) { return VIP_PRICE; }
            else { return REGULAR_PRICE; }
        }
    }
};
