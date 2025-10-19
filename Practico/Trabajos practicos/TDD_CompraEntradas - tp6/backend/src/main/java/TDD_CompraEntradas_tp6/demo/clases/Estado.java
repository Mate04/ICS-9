package TDD_CompraEntradas_tp6.demo.clases;

import TDD_CompraEntradas_tp6.demo.entities.Pedido;
import lombok.Data;

@Data
public abstract class Estado {

    private String nombre;

    public abstract Estado confirmarCompra(Pedido pedido);

}
