package TDD_CompraEntradas_tp6.demo.utils;

import TDD_CompraEntradas_tp6.demo.entities.Pedido;
import com.resend.*;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class EnviarMail {

    private static final Resend resend = new Resend("re_5WrjFDqt_GKJEoW9jPcFHyPBniKfBsSHT");

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$",
            Pattern.CASE_INSENSITIVE
    );

    public static class InvalidEmailException extends RuntimeException {
        public InvalidEmailException(String message) {
            super(message);
        }
    }

    public static void enviar(String usuario, Pedido pedido) {
        if (usuario == null || !EMAIL_PATTERN.matcher(usuario).matches()) {
            throw new InvalidEmailException("El correo electrónico '" + usuario + "' no es válido.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaVisita = pedido.getFechaEmision().format(formatter);

        // Construimos la tabla sin columna de cantidad
        StringBuilder resumen = new StringBuilder();
        resumen.append("<table style=\"width:100%; border-collapse: collapse; margin-top: 10px;\">")
                .append("<thead><tr style=\"background-color: #f4f4f4;\">")
                .append("<th style=\"border: 1px solid #ddd; padding: 8px;\">Tipo Persona</th>")
                .append("<th style=\"border: 1px solid #ddd; padding: 8px;\">Tipo Entrada</th>")
                .append("<th style=\"border: 1px solid #ddd; padding: 8px;\">Subtotal</th>")
                .append("</tr></thead><tbody>");

        for (var detalle : pedido.getDetallesPedidos()) {
            resumen.append("<tr>")
                    .append("<td style=\"border: 1px solid #ddd; padding: 8px;\">").append(detalle.getTipoPersona()).append("</td>")
                    .append("<td style=\"border: 1px solid #ddd; padding: 8px;\">").append(detalle.getTipoEntrada()).append("</td>")
                    .append("<td style=\"border: 1px solid #ddd; padding: 8px;\">$ ").append(String.format("%.2f", detalle.getPrecio())).append("</td>")
                    .append("</tr>");
        }
        resumen.append("</tbody></table>");

        // Mensaje según tipo de pago
        String mensajePago = "";
        if (pedido.getTipoPago() != null) {
            switch (pedido.getTipoPago()) {
                case EFECTIVO:
                    mensajePago = "Recordá abonar en boletería el día de tu visita";
                    break;
                case MERCADO_PAGO:
                    mensajePago = "Tu pago fue confirmado exitosamente con Mercado Pago";
                    break;
            }
        }

// Mensaje según estado del pedido
        String mensajeEstado = "";
        if (pedido.getEstado() != null) {
            switch (pedido.getEstado()) {
                case CREADO:
                    mensajeEstado = "Tu pedido fue creado correctamente y está siendo procesado";
                    break;
                case PENDIENTE_EFECTIVO:
                    mensajeEstado = "Tu pedido está pendiente de pago en boletería. Recordá abonarlo el día de tu visita";
                    break;
                case PENDIENTE_MERCADO_PAGO:
                    mensajeEstado = "Tu pedido está pendiente de confirmación de Mercado Pago. En cuanto se acredite, te avisaremos";
                    break;
                case FINALIZADO:
                    mensajeEstado = "¡Tu compra fue finalizada con éxito! Te esperamos para disfrutar de tu experiencia";
                    break;
                case RECHAZADO:
                    mensajeEstado = "Lamentablemente, tu pedido fue rechazado. Si creés que se trata de un error, por favor contactanos.";
                    break;
                default:
                    mensajeEstado = "El estado actual de tu pedido es: " + pedido.getEstado();
                    break;
            }
        }

// Construcción del cuerpo del mail
        String cuerpo = "<div style=\"font-family: Arial, sans-serif; color: #333; line-height: 1.6;\">"
                + "<h2>¡Gracias por tu compra!</h2>"
                + "<p>Hola " + usuario + ",</p>"
                + "<p>Tu pedido ha sido procesado exitosamente. A continuación encontrarás los detalles:</p>"
                + "<p><strong>N° de Pedido:</strong> " + pedido.getId() + "<br>"
                + "<strong>Fecha de Visita:</strong> " + fechaVisita + "</p>"
                + "<p><strong>Estado:</strong> " + pedido.getEstado() + "<br>"
                + mensajeEstado + "</p>"
                + (mensajePago.isEmpty() ? "" : "<p>" + mensajePago + "</p>")
                + "<h3>Resumen de tu compra:</h3>"
                + "<p><strong>Cantidad:</strong> " + pedido.getDetallesPedidos().size() + "</p>"
                + resumen.toString()
                + "<p style=\"margin-top: 10px; font-size: 16px;\"><strong>Monto Total:</strong> $ "
                + String.format("%.2f", pedido.getMontoTotal()) + "</p>"
                + "<p>Gracias por confiar en nosotros. ¡Te esperamos pronto! 💫</p>"
                + "<p style=\"font-size: 12px; color: #888;\">Este correo es informativo, por favor no respondas a este mensaje.</p>"
                + "</div>";


        // Debug: imprimir cuerpo antes de enviar
        System.out.println("CORREO HTML:");
        System.out.println(cuerpo);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Entradas <onboarding@resend.dev>")
                .to(usuario)
                .subject("Confirmación de pedido #" + pedido.getId())
                .html(cuerpo)
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println("Email enviado con ID: " + data.getId());
        } catch (ResendException e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
