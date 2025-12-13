package product.sales.company;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import product.sales.company.cart.ShoppingCart;
import product.sales.company.client.Client;
import product.sales.company.client.IndividualClient;
import product.sales.company.client.ProfessionalClient;
import product.sales.company.pricing.PricingStrategy;
import product.sales.company.product.ProductType;

@DisplayName("ShoppingCart BigDecimal totals")
public class ShoppingCartTest {

    @Test
    void testEmptyCart() {
        Client client = new IndividualClient(1, "John", "Nowak");
        ShoppingCart cart = new ShoppingCart(client);
        assertEquals(BigDecimal.ZERO.setScale(2), cart.calculateTotal());
    }

    @ParameterizedTest(name = "{0} quantities: hi={1}, mid={2}, lap={3}")
    @CsvSource({
        // clientType, qHigh, qMid, qLaptop, expectedTotal
        "INDIVIDUAL, 2, 1, 1, 5000.0",
        "PRO_HIGH,   2, 1, 1, 3450.0",
        "PRO_LOW,    2, 1, 1, 3900.0"
    })
    void testCalculateTotal(String clientType, int qHigh, int qMid, int qLaptop, String expectedTotalStr) {
        Client client = createClientFromType(clientType);

        ShoppingCart cart = new ShoppingCart(client);
        if (qHigh > 0)  cart.addItem(ProductType.HIGH_END_PHONE, qHigh);
        if (qMid > 0)   cart.addItem(ProductType.MID_RANGE_PHONE, qMid);
        if (qLaptop > 0) cart.addItem(ProductType.LAPTOP, qLaptop);

        BigDecimal expected = new BigDecimal(expectedTotalStr).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal actual = cart.calculateTotal();


        assertEquals(0, expected.compareTo(actual), () -> "expected=" + expected + " actual=" + actual);
    }

    private static Client createClientFromType(String clientType) {
        if ("INDIVIDUAL".equals(clientType)) {
            return new IndividualClient(1, "Alice", "Smith");
        } else if ("PRO_HIGH".equals(clientType)) {
            return new ProfessionalClient(2, "Acme Inc.", null, "REG-001", BigDecimal.valueOf(20_000_000.0));
        } else {
            return new ProfessionalClient(3, "Beta LLC", null, "REG-002", BigDecimal.valueOf(5_000_000.0));
        }
    }

    @ParameterizedTest
    @CsvSource({
        "HIGH_END_PHONE, 1500.0, 1000.0, 1150.0",
        "MID_RANGE_PHONE, 800.0, 550.0, 600.0",
        "LAPTOP,        1200.0, 900.0, 1000.0"
    })
    void testUnitPrices(ProductType type,
        String expectedIndStr,
        String expectedProHighStr,
        String expectedProLowStr) {

        BigDecimal expectedInd = new BigDecimal(expectedIndStr).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal expectedProHigh = new BigDecimal(expectedProHighStr).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal expectedProLow = new BigDecimal(expectedProLowStr).setScale(2, RoundingMode.HALF_EVEN);

        BigDecimal actualInd = new PricingStrategy.Individual().getUnitPrice(type).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal actualProHigh = new PricingStrategy.ProfessionalHighRevenue().getUnitPrice(type).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal actualProLow = new PricingStrategy.ProfessionalLowRevenue().getUnitPrice(type).setScale(2, RoundingMode.HALF_EVEN);


        assertEquals(0, expectedInd.compareTo(actualInd), () -> "expectedInd=" + expectedInd + " actual=" + actualInd);
        assertEquals(0, expectedProHigh.compareTo(actualProHigh), () -> "expectedProHigh=" + expectedProHigh + " actual=" + actualProHigh);
        assertEquals(0, expectedProLow.compareTo(actualProLow), () -> "expectedProLow=" + expectedProLow + " actual=" + actualProLow);
    }

}
