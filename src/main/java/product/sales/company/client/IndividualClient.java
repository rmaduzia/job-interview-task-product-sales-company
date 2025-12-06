package product.sales.company.client;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.Validate;
import product.sales.company.pricing.PricingStrategy;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class IndividualClient extends Client {

    private final String firstName;
    private final String lastName;

    public IndividualClient(int clientId, String firstName, String lastName) {
        super(clientId);
        Validate.notBlank(firstName, "firstName must not be blank");
        Validate.notBlank(lastName, "lastName must not be blank");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public PricingStrategy getPricingStrategy() {
        return new PricingStrategy.Individual();
    }
}