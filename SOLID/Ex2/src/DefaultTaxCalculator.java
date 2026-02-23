public class DefaultTaxCalculator implements TaxCalculator {
    @Override
    public double taxPercent(String customerType) {
        return TaxRules.taxPercent(customerType);
    }
}
