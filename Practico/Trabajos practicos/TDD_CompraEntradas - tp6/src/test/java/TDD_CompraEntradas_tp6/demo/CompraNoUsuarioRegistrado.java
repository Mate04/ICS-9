import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicketPurchaseUnauthorizedTest {

    private final TicketPurchaseService service = new TicketPurchaseService();

    @Test
    void test_compra_usuario_no_registrado() {

        User usuario = new User("visitante@correo.com", false); // no registrado
        PurchaseRequest request = new PurchaseRequest("2025-12-10", 2, "vip", "tarjeta");

        Exception exception = assertThrows(SecurityException.class, () -> {
            service.purchaseTickets(usuario, request);
        });

        assertEquals("Usuario no registrado. No puede realizar la compra.", exception.getMessage());
    }
}
