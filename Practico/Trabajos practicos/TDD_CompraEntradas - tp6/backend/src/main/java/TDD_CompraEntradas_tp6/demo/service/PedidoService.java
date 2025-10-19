package TDD_CompraEntradas_tp6.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import TDD_CompraEntradas_tp6.demo.clases.Visitante;
import TDD_CompraEntradas_tp6.demo.controller.dto.req.ValidarDatoDTO;
import TDD_CompraEntradas_tp6.demo.controller.dto.req.VisitanteDTO;
import TDD_CompraEntradas_tp6.demo.controller.dto.res.DetallePedidoDTO;
import TDD_CompraEntradas_tp6.demo.controller.dto.res.PedidoResDTO;
import TDD_CompraEntradas_tp6.demo.entities.DetallePedido;
import TDD_CompraEntradas_tp6.demo.entities.EstadoPedido;
import TDD_CompraEntradas_tp6.demo.entities.MetodoPago;
import TDD_CompraEntradas_tp6.demo.entities.Pedido;
import TDD_CompraEntradas_tp6.demo.entities.TipoEntrada;
import TDD_CompraEntradas_tp6.demo.entities.TipoPersona;
import TDD_CompraEntradas_tp6.demo.repository.PedidoRepository;
import TDD_CompraEntradas_tp6.demo.utils.SubTotalPorVisitante;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidorepository;


    public String[] getMetodoPago() {
        return Arrays.stream(MetodoPago.values())
                .map(Enum::name)
                .toArray(String[]::new);
    }

    public String[] getTipoEntrada() {
        return Arrays.stream(TipoEntrada.values())
                .map(Enum::name)
                .toArray(String[]::new);
    }

    public Object validarDatos(ValidarDatoDTO validarDatoDTO) {
        // todo: no esta andando despues ver
//        if(!FechaValidator.isValidDate(validarDatoDTO.getFechaVisita().toString())){
//            throw new IllegalArgumentException("La fecha no es un valida");
//        };
        List<DetallePedidoDTO> detallerPedidoRes = new ArrayList<>();
        Pedido pedido = Pedido.builder()
                .montoTotal(0)
                .estado(EstadoPedido.CREADO)
                .build();
        for (VisitanteDTO visitanteDTO : validarDatoDTO.getVisitantes()) {

            // acá accedés a cada elemento de la lista
            Visitante visitante  = new Visitante(visitanteDTO.getEdadVisitante(),visitanteDTO.getTipoEntrada().name());
            TipoPersona tipoPersona = visitante.getTipoPersona();
            float subtotal = SubTotalPorVisitante.subtotalPorEdad(visitante.getEdad(), visitanteDTO.getTipoEntrada());
            //Contruimos detallePedido atravez del parametro builder
            DetallePedido detallePedido = DetallePedido.builder()
                    .tipoEntrada(visitanteDTO.getTipoEntrada())
                    .precio(subtotal)
                    .tipoPersona(tipoPersona)
                    .build();
            pedido.AddDetallePedido(detallePedido);
            pedido.setMontoTotal(subtotal + pedido.getMontoTotal());
            detallerPedidoRes.add(new DetallePedidoDTO(detallePedido));
        }

        Pedido savePedido = this.pedidorepository.save(pedido);

        //Pedido pedido = createPedido(montoTotal, detallerPedido);
        return new PedidoResDTO(savePedido);
    }
    private Pedido createPedido(double montoTotal, List<DetallePedido> detallePedidos){
        Pedido pedido = Pedido.builder()
                .detallesPedidos(detallePedidos)
                .montoTotal(montoTotal)
                .build();
        return pedidorepository.save(pedido);
    }
}
