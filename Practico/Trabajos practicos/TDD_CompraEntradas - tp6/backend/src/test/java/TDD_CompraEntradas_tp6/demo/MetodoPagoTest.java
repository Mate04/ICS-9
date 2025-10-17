package TDD_CompraEntradas_tp6.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import TDD_CompraEntradas_tp6.demo.classes.modulodepago.PaymentMethod;
import TDD_CompraEntradas_tp6.demo.classes.modulodepago.PaymentResponse;
import TDD_CompraEntradas_tp6.demo.classes.modulodepago.PaymentService;
import TDD_CompraEntradas_tp6.demo.classes.modulodepago.PaymentStatus;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MetodoPagoTest {

    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        paymentService = new PaymentService();
    }

    // test pago con tarjeta redirige a mercado pago
    @Test
    public void test_pago_con_tarjeta_redirige_a_mercado_pago() {
        PaymentResponse resp = paymentService.iniciarPago(PaymentMethod.TARJETA, LocalDate.now());
        assertEquals(PaymentStatus.REDIRECCION_MP, resp.getStatus());
        assertNotNull(resp.getRedirectUrl());
        assertTrue(resp.getRedirectUrl().contains("mercadopago"));
        assertNull(resp.getCodigoUnico());
        assertNull(resp.getFechaVencimiento());
    }

    // test pago con efectivo estado pendiente
    @Test
    public void test_pago_con_efectivo_estado_pendiente() {
        LocalDate visita = LocalDate.now().plusDays(3);
        PaymentResponse resp = paymentService.iniciarPago(PaymentMethod.EFECTIVO, visita);
        assertEquals(PaymentStatus.PENDIENTE, resp.getStatus());
    }

    // test pago con efectivo genera código único
    @Test
    public void test_pago_con_efectivo_generar_codigo_unico() {
        LocalDate visita = LocalDate.now().plusDays(2);
        PaymentResponse resp = paymentService.iniciarPago(PaymentMethod.EFECTIVO, visita);
        assertNotNull(resp.getCodigoUnico());
        assertTrue(resp.getCodigoUnico().length() >= 6, "El código debe tener longitud mínima");
    }

    // test pago con efectivo vencimiento en fecha visita
    @Test
    public void test_pago_con_efectivo_vencimiento_en_fecha_visita() {
        LocalDate visita = LocalDate.now().plusDays(5);
        PaymentResponse resp = paymentService.iniciarPago(PaymentMethod.EFECTIVO, visita);
        assertEquals(visita, resp.getFechaVencimiento());
    }

    // test_pago_sin_medio_de_pago
    @Test
    public void test_pago_sin_medio_de_pago() {
        PaymentResponse resp = paymentService.iniciarPago(null, LocalDate.now());
        assertEquals(PaymentStatus.ERROR, resp.getStatus());
        assertNull(resp.getRedirectUrl());
        assertNull(resp.getCodigoUnico());
        assertNull(resp.getFechaVencimiento());
    }
}
