package TDD_CompraEntradas_tp6.demo.controller.dto.res;

import TDD_CompraEntradas_tp6.demo.entities.DetallePedido;
import TDD_CompraEntradas_tp6.demo.entities.TipoEntrada;
import TDD_CompraEntradas_tp6.demo.entities.TipoPersona;
import lombok.Data;

@Data
public class DetallePedidoDTO {
    TipoPersona tipoPersona;
    TipoEntrada tipoEntrada;
    double monto;

    public DetallePedidoDTO(DetallePedido detallePedido) {
        this.tipoPersona = detallePedido.getTipoPersona();
        this.tipoEntrada = detallePedido.getTipoEntrada();
        this.monto = detallePedido.getPrecio();
    }
}
