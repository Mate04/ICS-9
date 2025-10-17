package TDD_CompraEntradas_tp6.demo.classes;

public class AgeValidator {

    public boolean isValidAge(int edad) {
        // Válida: 0 hasta 101 inclusive
        return edad >= 0 && edad <= 101;
    }

    public int subtotalAPagarPorEdad(int edad, int precioEntradaSinDescuento) {
        // Menores de 3 años no pagan
        if (edad < 3) return 0;
        // Descuento 50% para mayores de 60
        if (edad > 60) return precioEntradaSinDescuento / 2;
        // Resto paga precio completo
        return precioEntradaSinDescuento;

    }
    
}
