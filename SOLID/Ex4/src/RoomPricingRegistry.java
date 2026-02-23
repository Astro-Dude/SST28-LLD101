import java.util.HashMap;
import java.util.Map;

public class RoomPricingRegistry implements RoomPricing {
    private final Map<Integer, Money> prices = new HashMap<>();
    private final Money defaultPrice;

    public RoomPricingRegistry(Money defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public void register(int roomType, Money price) {
        prices.put(roomType, price);
    }

    @Override
    public Money getBasePrice(int roomType) {
        return prices.getOrDefault(roomType, defaultPrice);
    }
}
