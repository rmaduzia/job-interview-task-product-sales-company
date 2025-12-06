package product.sales.company.cart;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import product.sales.company.client.Client;
import product.sales.company.pricing.PricingStrategy;
import product.sales.company.product.ProductType;

public class ShoppingCart {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCart.class);

    private final Client client;
    private final List<CartItem> items = new ArrayList<>();

    public ShoppingCart(Client client) {
        this.client = client;
        logger.info("Created shopping cart for client ID {}", client.getClientId());
    }

    public void addItem(ProductType type, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        items.add(new CartItem(type, quantity));
        logger.info("Added {} x {} to cart", quantity, type);
    }

    public double calculateTotal() {
        logger.info("Calculating total for client {}", client.getClientId());
        double total = 0.0;
        PricingStrategy strategy = client.getPricingStrategy();

        for (CartItem item : items) {
            double unitPrice = strategy.getUnitPrice(item.type());
            logger.info("  {} x {} at {} each", item.quantity(), item.type(), unitPrice);
            total += unitPrice * item.quantity();
        }

        logger.info("Total amount: {}", total);
        return total;
    }
}