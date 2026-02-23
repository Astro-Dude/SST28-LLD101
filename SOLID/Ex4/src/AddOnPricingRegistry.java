import java.util.HashMap;
import java.util.Map;

public class AddOnPricingRegistry implements AddOnPricing {
    private final Map<AddOn, Money> prices = new HashMap<>();

    public void register(AddOn addOn, Money price) {
        prices.put(addOn, price);
    }

    @Override
    public Money getPrice(AddOn addOn) {
        return prices.getOrDefault(addOn, new Money(0.0));
    }
}
