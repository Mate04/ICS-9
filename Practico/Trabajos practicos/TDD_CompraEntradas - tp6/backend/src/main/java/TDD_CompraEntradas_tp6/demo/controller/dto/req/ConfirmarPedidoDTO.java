package TDD_CompraEntradas_tp6.demo.controller.dto.req;

import TDD_CompraEntradas_tp6.demo.entities.MetodoPago;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmarPedidoDTO {
    @NotNull(message = "Orden de pedido debe ser obligatoria")
    @Schema(description = "Orden de pedido", example = "1")
    int idPedido;

    @NotNull(message = "Metodo de pago debe ser obligatoria")
    @Schema(description = "Metodo de pago", example = "MERCADO_PAGO")
    MetodoPago metodoPago;

}
