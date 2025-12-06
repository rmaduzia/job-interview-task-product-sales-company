package product.sales.company.cart;

import org.apache.commons.lang3.Validate;
import product.sales.company.product.ProductType;

public record CartItem(ProductType type, int quantity) {

    public CartItem {
        Validate.notNull(type, "type must not be null");
        Validate.isTrue(quantity > 0, "quantity must be > 0");
    }
}