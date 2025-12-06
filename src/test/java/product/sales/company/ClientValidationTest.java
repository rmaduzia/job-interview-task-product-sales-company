package product.sales.company;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import product.sales.company.cart.CartItem;
import product.sales.company.client.IndividualClient;
import product.sales.company.client.ProfessionalClient;
import product.sales.company.product.ProductType;

public class ClientValidationTest {

    @Test
    void individualClientBlankNamesShouldFail() {
        assertThrows(IllegalArgumentException.class, () -> new IndividualClient(1, "", "Smith"));
        assertThrows(IllegalArgumentException.class, () -> new IndividualClient(1, "John", " "));
    }

    @Test
    void professionalClientInvalidShouldFail() {
        assertThrows(IllegalArgumentException.class, () ->
            new ProfessionalClient(2, "", null, "REG-1", 1000d));
        assertThrows(IllegalArgumentException.class, () ->
            new ProfessionalClient(2, "Company", null, "", 1000d));
        assertThrows(IllegalArgumentException.class, () ->
            new ProfessionalClient(2, "Company", null, "REG-1", -1d));
    }

    @Test
    void cartItemInvalidShouldFail() {
        assertThrows(NullPointerException.class, () -> new CartItem(null, 1));

        assertThrows(IllegalArgumentException.class, () ->
            new CartItem(ProductType.LAPTOP, 0));
    }

}
