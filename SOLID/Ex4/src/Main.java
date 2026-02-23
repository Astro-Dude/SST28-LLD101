import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        RoomPricingRegistry roomPricing = new RoomPricingRegistry(new Money(16000.0));
        roomPricing.register(LegacyRoomTypes.SINGLE, new Money(14000.0));
        roomPricing.register(LegacyRoomTypes.DOUBLE, new Money(15000.0));
        roomPricing.register(LegacyRoomTypes.TRIPLE, new Money(12000.0));

        AddOnPricingRegistry addOnPricing = new AddOnPricingRegistry();
        addOnPricing.register(AddOn.MESS, new Money(1000.0));
        addOnPricing.register(AddOn.LAUNDRY, new Money(500.0));
        addOnPricing.register(AddOn.GYM, new Money(300.0));

        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        HostelFeeCalculator calc = new HostelFeeCalculator(new FakeBookingRepo(), roomPricing, addOnPricing);
        calc.process(req);
    }
}
