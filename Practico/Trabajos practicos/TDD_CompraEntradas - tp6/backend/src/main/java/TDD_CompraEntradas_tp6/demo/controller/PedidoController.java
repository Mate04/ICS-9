package TDD_CompraEntradas_tp6.demo.controller;

import TDD_CompraEntradas_tp6.demo.controller.dto.req.ConfirmarPedidoDTO;
import TDD_CompraEntradas_tp6.demo.controller.dto.req.ValidarDatoDTO;
import TDD_CompraEntradas_tp6.demo.controller.dto.res.ConfirmarPedidoRES;
import TDD_CompraEntradas_tp6.demo.controller.dto.res.PedidoResDTO;
import TDD_CompraEntradas_tp6.demo.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
@Validated
public class PedidoController {

    private final PedidoService pedidoService;


    @GetMapping("/metodo-pago")
    public String[] getMetodoDePago() {
        return this.pedidoService.getMetodoPago();
    }

    @GetMapping("/tipo-entrada")
    public String[] getTipoEntrada() {
        return this.pedidoService.getTipoEntrada();
    }

    @PostMapping("/validar-datos")
    public PedidoResDTO validarDatos(@Valid @RequestBody ValidarDatoDTO validarDatoDTO) {
        return this.pedidoService.validarDatos(validarDatoDTO);
    }

    //TODO: Desarrollar endpoint
    @PostMapping
    public ConfirmarPedidoRES confirmarPedido(@Valid @RequestBody ConfirmarPedidoDTO confirmarPedidoDTO) {
        return new ConfirmarPedidoRES(true, "Se creo exitosamente el pedido", confirmarPedidoDTO.getMetodoPago());
    }

}
