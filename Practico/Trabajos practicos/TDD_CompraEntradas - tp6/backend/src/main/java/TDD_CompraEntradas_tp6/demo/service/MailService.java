package TDD_CompraEntradas_tp6.demo.service;

import TDD_CompraEntradas_tp6.demo.entities.Pedido;
import TDD_CompraEntradas_tp6.demo.repository.PedidoRepository;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MailService {

    private final PedidoRepository pedidoRepository;
    private final JavaMailSender mailSender;

    @Value("${mail.from.address:${spring.mail.username:no-reply@example.com}}")
    private String fromAddress;

    @Value("${mail.from.name:Entradas}")
    private String fromName;



    public static class InvalidEmailException extends RuntimeException {
        public InvalidEmailException(String message) { super(message); }
    }

    @Async
    @Transactional(readOnly = true)
    public void enviar(String usuario, Pedido pedidoDTO) {
        var pedido = pedidoRepository.findById((int) pedidoDTO.getId())
                .orElseThrow(() -> new IllegalStateException("Pedido no encontrado"));


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaVisita = pedido.getFechaVisita().format(formatter);

        StringBuilder resumen = new StringBuilder();
        resumen.append("<table style=\"width:100%; border-collapse: collapse; margin-top: 10px;\">")
                .append("<thead><tr style=\"background-color: #f4f4f4;\">")
                .append("<th style=\"border: 1px solid #ddd; padding: 8px; text-align:left;\">Tipo Persona</th>")
                .append("<th style=\"border: 1px solid #ddd; padding: 8px; text-align:left;\">Tipo Entrada</th>")
                .append("<th style=\"border: 1px solid #ddd; padding: 8px; text-align:right;\">Subtotal</th>")
                .append("</tr></thead><tbody>");

        for (var detalle : pedido.getDetallesPedidos()) {
            resumen.append("<tr>")
                    .append("<td style=\"border: 1px solid #ddd; padding: 8px;\">").append(detalle.getTipoPersona()).append("</td>")
                    .append("<td style=\"border: 1px solid #ddd; padding: 8px;\">").append(detalle.getTipoEntrada()).append("</td>")
                    .append("<td style=\"border: 1px solid #ddd; padding: 8px; text-align:right;\">$ ").append(String.format("%.2f", detalle.getPrecio())).append("</td>")
                    .append("</tr>");
        }
        resumen.append("</tbody></table>");

        String mensajePago = "";
        if (pedido.getTipoPago() != null) {
            switch (pedido.getTipoPago()) {
                case EFECTIVO -> mensajePago = "Recordá abonar en boletería el día de tu visita.";
                case MERCADO_PAGO -> mensajePago = "Tu pago fue confirmado exitosamente con Mercado Pago.";
            }
        }

        String mensajeEstado = "";
        if (pedido.getEstado() != null) {
            switch (pedido.getEstado()) {
                case CREADO -> mensajeEstado = "Tu pedido fue creado correctamente y está siendo procesado.";
                case PENDIENTE_EFECTIVO -> mensajeEstado = "Pendiente de pago en efectivo";
                case PENDIENTE_MERCADO_PAGO -> mensajeEstado = "Tu pedido está pendiente de confirmación de Mercado Pago. En cuanto se acredite, te avisaremos.";
                case FINALIZADO -> mensajeEstado = "Pagado";
                case RECHAZADO -> mensajeEstado = "Lamentablemente, tu pedido fue rechazado. Si creés que se trata de un error, por favor contactanos.";
                default -> mensajeEstado = "El estado actual de tu pedido es: " + pedido.getEstado();
            }
        }

        String cuerpo = "<!doctype html>"
                + "<html lang=\"es\">"
                + "<head>"
                + "<meta charset=\"utf-8\">"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
                + "<style>"
                + "  body { font-family: Arial, sans-serif; color: #333; margin:0; padding:0; }"
                + "  .container { max-width:700px; margin:20px auto; padding:20px; background:#ffffff; border-radius:8px; box-shadow:0 2px 6px rgba(0,0,0,0.05); }"
                + "  .header { text-align:center; padding:20px 0; border-bottom:2px solid #2b7a78; margin-bottom:20px; }"
                + "  .header h1 { color:#2b7a78; margin:0; font-size:32px; }"
                + "  .header small { color:#666; font-size:14px; }"
                + "  h2 { color:#2b7a78; margin:14px 0 6px 0; }"
                + "  p { margin:6px 0; }"
                + "  .meta { background:#f9f9f9; padding:12px; border-radius:6px; margin-top:12px; }"
                + "  .total { font-size:18px; font-weight:700; margin-top:12px; }"
                + "  .footer { font-size:12px; color:#888; margin-top:18px; }"
                + "  @media (max-width:600px) { .container { padding:12px; } }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class=\"container\">"
                + "  <div class=\"header\">"
                + "    <h1>Entradas</h1>"
                + "    <small>Confirmación de compra</small>"
                + "  </div>"
                + "  <h2>¡Gracias por tu compra!</h2>"
                + "  <p>Hola " + usuario + ",</p>"
                + "  <p>Tu pedido ha sido procesado. A continuación encontrarás los detalles:</p>"
                + "  <div class=\"meta\">"
                + "    <p><strong>N° de Pedido:</strong> " + pedido.getId() + "</p>"
                + "    <p><strong>Fecha de Visita:</strong> " + fechaVisita + "</p>"
                + "    <p><strong>Estado:</strong> " + mensajeEstado + "</p>"
                + (mensajePago.isEmpty() ? "" : "<p><strong>Pago:</strong> " + mensajePago + "</p>")
                + "  </div>"
                + "  <h3 style=\"margin-top:16px;\">Resumen de tu compra</h3>"
                + "  <p><strong>Cantidad:</strong> " + pedido.getDetallesPedidos().size() + "</p>"
                + resumen.toString()
                + "  <p class=\"total\">Monto Total: $ " + String.format("%.2f", pedido.getMontoTotal()) + "</p>"
                + "  <p>Te recomendamos guardar este correo como comprobante. Presentalo en boletería si corresponde.</p>"
                + "  <div class=\"footer\">"
                + "    <p>Si tienes alguna consulta, no dudes en contactarnos a través de nuestro sitio web.</p>"
                + "    <p>Este correo es informativo, por favor no respondas a este mensaje.</p>"
                + "  </div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(fromAddress, fromName);
            helper.setTo(usuario);
            helper.setSubject("Confirmación de pedido #" + pedido.getId());
            helper.setText(cuerpo, true);

            mailSender.send(message);
            System.out.println("Correo enviado correctamente a " + usuario);
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}