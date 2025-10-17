import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicketPurchaseMissingFieldsTest {

    private final TicketPurchaseService service = new TicketPurchaseService();

    @Test
    void test_falta_campo_obligatorio() {
        User usuario = new User("juan@example.com", true);

        assertAll(
            // Falta la fecha
            () -> assertThrows(IllegalArgumentException.class, () -> 
                service.purchaseTickets(usuario, new PurchaseRequest(null, 2, "vip", "tarjeta"))
            ),
            // Falta la cantidad
            () -> assertThrows(IllegalArgumentException.class, () -> 
                service.purchaseTickets(usuario, new PurchaseRequest("2025-12-15", 0, "vip", "tarjeta"))
            ),
            // Falta el tipo de pase
            () -> assertThrows(IllegalArgumentException.class, () -> 
                service.purchaseTickets(usuario, new PurchaseRequest("2025-12-15", 2, null, "tarjeta"))
            ),
            // Falta la forma de pago
            () -> assertThrows(IllegalArgumentException.class, () -> 
                service.purchaseTickets(usuario, new PurchaseRequest("2025-12-15", 2, "vip", null))
            )
        );
    }
}
