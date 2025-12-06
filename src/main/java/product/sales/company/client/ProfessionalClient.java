package product.sales.company.client;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import product.sales.company.pricing.PricingStrategy;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class ProfessionalClient extends Client {

    private final String companyName;
    private final Optional<String> intraCommunityVatNumber;
    private final String registrationNumber;
    private final double annualRevenue;

    private static final int HIGH_REVENUE_THRESHOLD = 10_000_000;

    public ProfessionalClient(int clientId,
        String companyName,
        String intraCommunityVatNumber,
        String registrationNumber,
        double annualRevenue) {
        super(clientId);

        Validate.notBlank(companyName, "companyName must not be blank");
        Validate.notBlank(registrationNumber, "registrationNumber must not be blank");
        Validate.isTrue(annualRevenue >= 0d, "annualRevenue must be >= 0");

        this.companyName = companyName;
        this.intraCommunityVatNumber = Optional.ofNullable(
            StringUtils.isBlank(intraCommunityVatNumber) ? null : intraCommunityVatNumber);
        this.registrationNumber = registrationNumber;
        this.annualRevenue = annualRevenue;
    }

    @Override
    public PricingStrategy getPricingStrategy() {
        if (this.annualRevenue > HIGH_REVENUE_THRESHOLD) {
            return new PricingStrategy.ProfessionalHighRevenue();
        } else {
            return new PricingStrategy.ProfessionalLowRevenue();
        }
    }
}
