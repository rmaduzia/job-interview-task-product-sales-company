package product.sales.company.pricing;

import java.math.BigDecimal;
import product.sales.company.product.ProductType;

public sealed interface PricingStrategy {

    BigDecimal getUnitPrice(ProductType productType);

    record Individual() implements PricingStrategy {

        private static final BigDecimal HIGH = BigDecimal.valueOf(1500);
        private static final BigDecimal MID = BigDecimal.valueOf(800);
        private static final BigDecimal LAPTOP = BigDecimal.valueOf(1200);

        @Override
        public BigDecimal getUnitPrice(ProductType productType) {
            return switch (productType) {
                case HIGH_END_PHONE -> HIGH;
                case MID_RANGE_PHONE -> MID;
                case LAPTOP -> LAPTOP;
            };
        }
    }

    record ProfessionalHighRevenue() implements PricingStrategy {

        private static final BigDecimal HIGH = BigDecimal.valueOf(1000);
        private static final BigDecimal MID = BigDecimal.valueOf(550);
        private static final BigDecimal LAPTOP = BigDecimal.valueOf(900);

        @Override
        public BigDecimal getUnitPrice(ProductType productType) {
            return switch (productType) {
                case HIGH_END_PHONE -> HIGH;
                case MID_RANGE_PHONE -> MID;
                case LAPTOP -> LAPTOP;
            };
        }
    }

    record ProfessionalLowRevenue() implements PricingStrategy {

        private static final BigDecimal HIGH = BigDecimal.valueOf(1150);
        private static final BigDecimal MID = BigDecimal.valueOf(600);
        private static final BigDecimal LAPTOP = BigDecimal.valueOf(1000);

        @Override
        public BigDecimal getUnitPrice(ProductType type) {
            return switch (type) {
                case HIGH_END_PHONE -> HIGH;
                case MID_RANGE_PHONE -> MID;
                case LAPTOP -> LAPTOP;
            };
        }
    }




}
