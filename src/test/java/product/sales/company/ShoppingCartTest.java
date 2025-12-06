package product.sales.company;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import product.sales.company.cart.ShoppingCart;
import product.sales.company.client.Client;
import product.sales.company.client.IndividualClient;
import product.sales.company.client.ProfessionalClient;
import product.sales.company.pricing.PricingStrategy;
import product.sales.company.product.ProductType;

public class ShoppingCartTest {

    @Test
    void testEmptyCart() {
        Client client = new IndividualClient(1, "John", "Nowak");
        ShoppingCart cart = new ShoppingCart(client);
        assertEquals(0.0, cart.calculateTotal());
    }

    @ParameterizedTest
    @CsvSource({
        // clientType, qHigh, qMid, qLaptop, expectedTotal
        "INDIVIDUAL, 2, 1, 1, 5000.0",
        "PRO_HIGH,   2, 1, 1, 3450.0",
        "PRO_LOW,    2, 1, 1, 3900.0"
    })
    void testCalculateTotal(String clientType, int qHigh, int qMid, int qLaptop, double expectedTotal) {
        Client client;
        if ("INDIVIDUAL".equals(clientType)) {
            client = new IndividualClient(1, "Alice", "Smith");
        } else if ("PRO_HIGH".equals(clientType)) {
            client = new ProfessionalClient(2, "Acme Inc.", null, "REG-001", 20_000_000.0);
        } else {
            client = new ProfessionalClient(3, "Beta LLC", null, "REG-002", 5_000_000.0);
        }
        ShoppingCart cart = new ShoppingCart(client);
        if (qHigh > 0)  cart.addItem(ProductType.HIGH_END_PHONE, qHigh);
        if (qMid > 0)   cart.addItem(ProductType.MID_RANGE_PHONE, qMid);
        if (qLaptop > 0) cart.addItem(ProductType.LAPTOP, qLaptop);

        assertEquals(expectedTotal, cart.calculateTotal());
    }

    @ParameterizedTest
    @CsvSource({
        "HIGH_END_PHONE, 1500.0, 1000.0, 1150.0",
        "MID_RANGE_PHONE, 800.0, 550.0, 600.0",
        "LAPTOP,        1200.0, 900.0, 1000.0"
    })
    void testUnitPrices(ProductType type,
        double expectedInd,
        double expectedProHigh,
        double expectedProLow) {
        assertEquals(expectedInd,
            new PricingStrategy.Individual().getUnitPrice(type));
        assertEquals(expectedProHigh,
            new PricingStrategy.ProfessionalHighRevenue().getUnitPrice(type));
        assertEquals(expectedProLow,
            new PricingStrategy.ProfessionalLowRevenue().getUnitPrice(type));
    }

}
