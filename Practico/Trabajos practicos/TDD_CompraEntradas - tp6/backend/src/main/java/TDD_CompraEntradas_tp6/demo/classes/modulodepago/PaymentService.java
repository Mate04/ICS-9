package TDD_CompraEntradas_tp6.demo.classes.modulodepago;

import java.security.SecureRandom;
import java.time.LocalDate;

public class PaymentService {

    private final SecureRandom random = new SecureRandom();

    public PaymentResponse iniciarPago(PaymentMethod metodo, LocalDate fechaVisita) {
        if (metodo == null) {
            return new PaymentResponse(PaymentStatus.ERROR, null, null, null);
        }

        switch (metodo) {
            case TARJETA:
                // Simula redirección a Mercado Pago
                return new PaymentResponse(PaymentStatus.REDIRECCION_MP, "https://www.mercadopago.com/checkout", null, null);
            case EFECTIVO:
                // Genera código único y fecha de vencimiento igual a fecha de visita
                String codigo = generarCodigoUnico();
                LocalDate vencimiento = fechaVisita;
                return new PaymentResponse(PaymentStatus.PENDIENTE, null, codigo, vencimiento);
            default:
                return new PaymentResponse(PaymentStatus.ERROR, null, null, null);
        }
    }

    private String generarCodigoUnico() {
        // Genera un código alfanumérico corto, p.ej., 8 chars
        final String chars = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
