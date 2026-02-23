public class DefaultDiscountCalculator implements DiscountCalculator {
    @Override
    public double discountAmount(String customerType, double subtotal, int distinctLines) {
        return DiscountRules.discountAmount(customerType, subtotal, distinctLines);
    }
}
