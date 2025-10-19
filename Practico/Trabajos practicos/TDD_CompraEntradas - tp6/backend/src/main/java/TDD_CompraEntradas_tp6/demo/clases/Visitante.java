package TDD_CompraEntradas_tp6.demo.clases;

import TDD_CompraEntradas_tp6.demo.entities.TipoPersona;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visitante {
    //atributos
    Integer edad;
    String tipoEntrada; // 1: VIP, 2: REGULAR
    TipoPersona tipoPersona;

    public Visitante(int edad, String tipoEntrada) {

        if (!isValidAge(edad)){
            throw new IllegalArgumentException("En algun participante la edad no es valida");
        }
        this.edad = edad;
        this.tipoEntrada = tipoEntrada;
        this.tipoPersona = this.calcularTipoPersona(edad);
    }

    private TipoPersona calcularTipoPersona(int edad){
        if(0 <= edad && edad <= 3){
            return TipoPersona.BEBE;
        }
        if(3 < edad && edad <= 14){
            return TipoPersona.JOVEN;
        }
        if(65 <= edad){
            return TipoPersona.JUBILADO;
        }
        return TipoPersona.ADULTO;
    }

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
