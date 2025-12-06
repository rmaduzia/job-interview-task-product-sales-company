package product.sales.company.pricing;

import product.sales.company.product.ProductType;

public sealed interface PricingStrategy {

    double getUnitPrice(ProductType productType);

    record Individual() implements PricingStrategy {
        @Override
        public double getUnitPrice(ProductType productType) {
            return switch (productType) {
                case HIGH_END_PHONE -> 1500.0;
                case MID_RANGE_PHONE -> 800.0;
                case LAPTOP -> 1200.0;
            };
        }
    }

    record ProfessionalHighRevenue() implements PricingStrategy {
        @Override
        public double getUnitPrice(ProductType productType) {
            return switch (productType) {
                case HIGH_END_PHONE -> 1000.0;
                case MID_RANGE_PHONE -> 550.0;
                case LAPTOP -> 900.0;
            };
        }
    }

    record ProfessionalLowRevenue() implements PricingStrategy {
        @Override
        public double getUnitPrice(ProductType type) {
            return switch (type) {
                case HIGH_END_PHONE -> 1150.0;
                case MID_RANGE_PHONE -> 600.0;
                case LAPTOP -> 1000.0;
            };
        }
    }




}
