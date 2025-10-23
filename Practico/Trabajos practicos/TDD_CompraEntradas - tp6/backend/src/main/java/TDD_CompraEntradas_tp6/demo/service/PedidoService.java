package TDD_CompraEntradas_tp6.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import TDD_CompraEntradas_tp6.demo.controller.dto.req.ConfirmarPedidoDTO;
import TDD_CompraEntradas_tp6.demo.utils.FechaValidator;
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
    private final MailService mailService;


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

    public PedidoResDTO validarDatos(ValidarDatoDTO validarDatoDTO) {
        if(!FechaValidator.isValidDate(validarDatoDTO.getFechaVisita().toString())){
            throw new IllegalArgumentException("La fecha no es un valida");
        };
        List<DetallePedidoDTO> detallerPedidoRes = new ArrayList<>();
        Pedido pedido = Pedido.builder()
                .montoTotal(0)
                .estado(EstadoPedido.CREADO)
                .fechaVisita(validarDatoDTO.getFechaVisita())
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

    public void confirmarPago(ConfirmarPedidoDTO confirmarPedidoDTO) {

        Pedido pedido = this.pedidorepository.findById(confirmarPedidoDTO.getIdPedido()).orElse(null);
        if (pedido == null) {
            throw new IllegalStateException("Pedido no encontrado");
        }

        Pedido pedidoVerificado = verificarPedido(pedido, confirmarPedidoDTO.getMetodoPago());

        pedido.setEstado(pedidoVerificado.getEstado());
        pedido.setTipoPago(confirmarPedidoDTO.getMetodoPago());
        this.pedidorepository.save(pedido);

        //Funcion asincrona
        this.mailService.enviar("91461@sistemas.frc.utn.edu.ar", pedido);
        System.out.println("Finish");


    }

    private Pedido verificarPedido(Pedido pedido, MetodoPago metodoPago) {
        if(pedido.getEstado() == EstadoPedido.CREADO){
            return switch (metodoPago) {
                case MetodoPago.MERCADO_PAGO -> {
                    //TODO: se procesa el pago como exitoso en mercado pago
                    pedido.setEstado(EstadoPedido.FINALIZADO);
                    yield pedido;
                }
                case MetodoPago.EFECTIVO -> {
                    pedido.setEstado(EstadoPedido.PENDIENTE_EFECTIVO);
                    yield pedido;
                }
                default ->
                        throw new IllegalStateException("Unexpected value: " + metodoPago.name());
            };
        }
        throw new IllegalStateException("El pedido ya fue confirmado: " + pedido.getEstado());
    }
}
