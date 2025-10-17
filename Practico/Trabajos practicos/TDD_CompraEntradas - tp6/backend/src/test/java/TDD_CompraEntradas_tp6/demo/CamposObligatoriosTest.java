import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicketPurchaseRequiredFieldsTest {

    private final TicketPurchaseService service = new TicketPurchaseService();

    @Test
    void test_campos_obligatorios_completos() {

        User usuario = new User("ana@example.com", true);
        PurchaseRequest request = new PurchaseRequest("2025-12-15", 3, "regular", "tarjeta");

        PurchaseResponse response = service.purchaseTickets(usuario, request);

        assertTrue(response.isSuccessful(), "La compra debe ser exitosa si todos los campos están completos");
        assertNotNull(response.getConfirmationCode(), "Debe generar un código de confirmación");
    }
}
