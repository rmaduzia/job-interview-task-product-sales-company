package product.sales.company;

import product.sales.company.cart.ShoppingCart;
import product.sales.company.client.Client;
import product.sales.company.client.IndividualClient;
import product.sales.company.client.ProfessionalClient;
import product.sales.company.product.ProductType;

public class ProductSalesCompanyApplication {

    static void main() {

        // Individual client example
        Client individual = new IndividualClient(1, "Alice", "Johnson");
        ShoppingCart cart1 = new ShoppingCart(individual);
        cart1.addItem(ProductType.HIGH_END_PHONE, 1);
        cart1.addItem(ProductType.LAPTOP, 2);
        double total1 = cart1.calculateTotal();
        System.out.println("Total for individual: " + total1);

        // Professional client (high revenue) example
        Client proHigh = new ProfessionalClient(2, "TechCorp", null, "REG-123", 15_000_000.0);
        ShoppingCart cart2 = new ShoppingCart(proHigh);
        cart2.addItem(ProductType.MID_RANGE_PHONE, 3);
        cart2.addItem(ProductType.LAPTOP, 1);
        double total2 = cart2.calculateTotal();
        System.out.println("Total for professional (high revenue): " + total2);

        // Professional client (low revenue) example
        Client proLow = new ProfessionalClient(3, "RetailCo", "VAT-987", "REG-456", 5_000_000.0);
        ShoppingCart cart3 = new ShoppingCart(proLow);
        cart3.addItem(ProductType.HIGH_END_PHONE, 2);
        cart3.addItem(ProductType.MID_RANGE_PHONE, 2);
        double total3 = cart3.calculateTotal();
        System.out.println("Total for professional (low revenue): " + total3);
    }

}