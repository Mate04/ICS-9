package TDD_CompraEntradas_tp6.demo.utils;

import TDD_CompraEntradas_tp6.demo.entities.Pedido;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.regex.Pattern;

public class EnviarMail {

    // 🔐 Configuración de tu cuenta Gmail
    private static final String GMAIL_USER = "ecoharmony219@gmail.com"; // TODO: reemplazar
    private static final String GMAIL_APP_PASSWORD = "tzmq bbco dmnc yrdo"; // TODO: reemplazar

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

        // 🧩 Construcción de la tabla de resumen
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

        // 💳 Mensaje según tipo de pago
        String mensajePago = "";
        if (pedido.getTipoPago() != null) {
            switch (pedido.getTipoPago()) {
                case EFECTIVO -> mensajePago = "Recordá abonar en boletería el día de tu visita";
                case MERCADO_PAGO -> mensajePago = "Tu pago fue confirmado exitosamente con Mercado Pago";
            }
        }

        // 🧾 Mensaje según estado del pedido
        String mensajeEstado = "";
        if (pedido.getEstado() != null) {
            switch (pedido.getEstado()) {
                case CREADO -> mensajeEstado = "Tu pedido fue creado correctamente y está siendo procesado";
                case PENDIENTE_EFECTIVO -> mensajeEstado = "Tu pedido está pendiente de pago en boletería. Recordá abonarlo el día de tu visita";
                case PENDIENTE_MERCADO_PAGO -> mensajeEstado = "Tu pedido está pendiente de confirmación de Mercado Pago. En cuanto se acredite, te avisaremos";
                case FINALIZADO -> mensajeEstado = "¡Tu compra fue finalizada con éxito! Te esperamos para disfrutar de tu experiencia";
                case RECHAZADO -> mensajeEstado = "Lamentablemente, tu pedido fue rechazado. Si creés que se trata de un error, por favor contactanos.";
                default -> mensajeEstado = "El estado actual de tu pedido es: " + pedido.getEstado();
            }
        }

        // 🧠 Construcción del cuerpo del correo
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
                + resumen
                + "<p style=\"margin-top: 10px; font-size: 16px;\"><strong>Monto Total:</strong> $ "
                + String.format("%.2f", pedido.getMontoTotal()) + "</p>"
                + "<p>Gracias por confiar en nosotros. ¡Te esperamos pronto!</p>"
                + "<p style=\"font-size: 12px; color: #888;\">Este correo es informativo, por favor no respondas a este mensaje.</p>"
                + "</div>";

        // ✉️ Envío del correo usando SMTP de Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(GMAIL_USER, GMAIL_APP_PASSWORD);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(GMAIL_USER, "Entradas")); // nombre visible
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(usuario));
            message.setSubject("Confirmación de pedido #" + pedido.getId());
            message.setContent(cuerpo, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("Correo enviado correctamente a " + usuario);

        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
