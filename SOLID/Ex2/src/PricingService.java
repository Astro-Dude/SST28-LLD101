import java.util.*;

public class PricingService {
    private final TaxCalculator taxCalculator;
    private final DiscountCalculator discountCalculator;

    public PricingService(TaxCalculator taxCalculator, DiscountCalculator discountCalculator) {
        this.taxCalculator = taxCalculator;
        this.discountCalculator = discountCalculator;
    }

    public double subtotal(Map<String, MenuItem> menu, List<OrderLine> lines) {
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            subtotal += item.price * l.qty;
        }
        return subtotal;
    }

    public double taxPercent(String customerType) {
        return taxCalculator.taxPercent(customerType);
    }

    public double tax(double subtotal, String customerType) {
        double pct = taxCalculator.taxPercent(customerType);
        return subtotal * (pct / 100.0);
    }

    public double discount(String customerType, double subtotal, int distinctLines) {
        return discountCalculator.discountAmount(customerType, subtotal, distinctLines);
    }

    public double total(double subtotal, double tax, double discount) {
        return subtotal + tax - discount;
    }
}
