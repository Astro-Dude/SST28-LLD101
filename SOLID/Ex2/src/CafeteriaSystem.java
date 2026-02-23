import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final PricingService pricingService;
    private final InvoiceFormatter formatter;
    private final InvoiceStore store;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(PricingService pricingService, InvoiceFormatter formatter, InvoiceStore store) {
        this.pricingService = pricingService;
        this.formatter = formatter;
        this.store = store;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        double subtotal = pricingService.subtotal(menu, lines);
        double taxPct = pricingService.taxPercent(customerType);
        double tax = pricingService.tax(subtotal, customerType);
        double discount = pricingService.discount(customerType, subtotal, lines.size());
        double total = pricingService.total(subtotal, tax, discount);

        String printable = formatter.format(invId, menu, lines, subtotal, taxPct, tax, discount, total);
        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
