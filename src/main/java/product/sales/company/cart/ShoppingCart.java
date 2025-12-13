package product.sales.company.cart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import product.sales.company.client.Client;
import product.sales.company.pricing.PricingStrategy;
import product.sales.company.product.ProductType;

@Slf4j
public class ShoppingCart {

    private final Client client;
    private final List<CartItem> items = new ArrayList<>();

    public ShoppingCart(Client client) {
        this.client = client;
        log.info("Created shopping cart for client ID {}", client.getClientId());
    }

    public void addItem(ProductType type, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        items.add(new CartItem(type, quantity));
        log.info("Added {} x {} to cart", quantity, type);
    }

    public BigDecimal calculateTotal() {
        log.info("Calculating total for client {}", client.getClientId());
        BigDecimal total = BigDecimal.ZERO;
        PricingStrategy strategy = client.getPricingStrategy();

        for (CartItem item : items) {
            BigDecimal unitPrice = strategy.getUnitPrice(item.type());
            BigDecimal line = unitPrice.multiply(BigDecimal.valueOf(item.quantity()));
            log.debug("Line: {} x {} = {}", item.quantity(), unitPrice, line);
            total = total.add(line);
        }

        BigDecimal rounded = total.setScale(2, RoundingMode.HALF_EVEN);
        log.info("Total amount: {}", rounded);
        return rounded;
    }
}