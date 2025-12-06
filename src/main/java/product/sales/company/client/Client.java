package product.sales.company.client;

import lombok.Getter;
import org.apache.commons.lang3.Validate;
import product.sales.company.pricing.PricingStrategy;

@Getter
public abstract class Client {
    private final int clientId;

    protected Client(int clientId) {
        Validate.isTrue(clientId > 0, "clientId must be positive");
        this.clientId = clientId;
    }

    public abstract PricingStrategy getPricingStrategy();
}
