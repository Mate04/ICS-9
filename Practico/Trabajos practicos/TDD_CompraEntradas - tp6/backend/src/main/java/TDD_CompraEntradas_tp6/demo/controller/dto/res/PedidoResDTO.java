package TDD_CompraEntradas_tp6.demo.controller.dto.res;

import TDD_CompraEntradas_tp6.demo.entities.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResDTO {
    long idPedido;
    double importeTotal;
    Date fecha;
    List<DetallePedidoDTO> resumen;

    public PedidoResDTO(Pedido pedido) {
        this.idPedido = pedido.getId();
        this.resumen = pedido.getDetallesPedidos()
                .stream()
                .map(DetallePedidoDTO::new)
                .collect(Collectors.toList());
        this.fecha = new Date();
        this.importeTotal = pedido.getMontoTotal();
    }

}
