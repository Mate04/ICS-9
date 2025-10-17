package TDD_CompraEntradas_tp6.demo.entities;

public class Visitante {
    //atributos
    int edad;
    String tipoEntrada; // 1: VIP, 2: REGULAR

    //metodos validacion
    public boolean isValidAge(Object edadObj) {
        if (edadObj == null) {
            throw new IllegalArgumentException("La edad no puede ser null");
        }
        int edad;
        if (edadObj instanceof Integer) {
            edad = (Integer) edadObj;
        } else if (edadObj instanceof String) {
            try {
                edad = Integer.parseInt((String) edadObj);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("La edad debe ser un número");
            }
        } else {
            throw new IllegalArgumentException("La edad debe ser un número");
        }
        return edad >= 0 && edad <= 100;
    }

    public boolean isValidTipoEntrada(String tipoEntrada) {
        if (tipoEntrada == null) {
            throw new IllegalArgumentException("El tipo de entrada no puede ser null");
        }
        return tipoEntrada.equals("VIP") || tipoEntrada.equals("REGULAR");
    }


}
