package TDD_CompraEntradas_tp6.demo.classes.modulodepago;

import java.time.LocalDate;
import java.util.Objects;

public class PaymentResponse {
    private final PaymentStatus status;
    private final String redirectUrl;
    private final String codigoUnico;
    private final LocalDate fechaVencimiento;

    public PaymentResponse(PaymentStatus status, String redirectUrl, String codigoUnico, LocalDate fechaVencimiento) {
        this.status = status;
        this.redirectUrl = redirectUrl;
        this.codigoUnico = codigoUnico;
        this.fechaVencimiento = fechaVencimiento;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentResponse)) return false;
        PaymentResponse that = (PaymentResponse) o;
        return status == that.status &&
                Objects.equals(redirectUrl, that.redirectUrl) &&
                Objects.equals(codigoUnico, that.codigoUnico) &&
                Objects.equals(fechaVencimiento, that.fechaVencimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, redirectUrl, codigoUnico, fechaVencimiento);
    }
}
