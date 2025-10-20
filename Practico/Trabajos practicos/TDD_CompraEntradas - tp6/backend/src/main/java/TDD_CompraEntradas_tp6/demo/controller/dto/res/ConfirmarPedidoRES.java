package TDD_CompraEntradas_tp6.demo.controller.dto.res;

import TDD_CompraEntradas_tp6.demo.entities.MetodoPago;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmarPedidoRES {
    boolean success;
    MetodoPago metodoPago;
    String mensaje;

    public ConfirmarPedidoRES(boolean success, String mensaje, MetodoPago metodoPago) {
        this.success = success;
        this.mensaje = mensaje;
        this.metodoPago = metodoPago;
    }
}
