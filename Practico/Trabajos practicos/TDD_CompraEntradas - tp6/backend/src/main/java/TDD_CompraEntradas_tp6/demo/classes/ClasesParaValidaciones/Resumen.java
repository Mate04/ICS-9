package TDD_CompraEntradas_tp6.demo.classes.ClasesParaValidaciones;

import java.time.LocalDate;

public class Resumen {

    private int cantidadEntradas;
    private LocalDate fecha;

    public Resumen(int cantidadEntradas, LocalDate fecha) {
        this.cantidadEntradas = cantidadEntradas;
        this.fecha = fecha;
    }
    
    public int getCantidadEntradas() {
        return cantidadEntradas;
    }
    public LocalDate getFecha() {
        return fecha;
    }
}