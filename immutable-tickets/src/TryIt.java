import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        // Demonstrate immutable updates through service — returns NEW instances
        IncidentTicket assigned = service.assign(t, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nOriginal after service calls: " + t);
        System.out.println("Assigned (new instance)    : " + assigned);
        System.out.println("Escalated (new instance)   : " + escalated);

        System.out.println("\nOriginal identity : " + System.identityHashCode(t));
        System.out.println("Assigned identity : " + System.identityHashCode(assigned));
        System.out.println("Escalated identity: " + System.identityHashCode(escalated));

        // Demonstrate external mutation via leaked list reference is now blocked
        List<String> tags = escalated.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("\nERROR: external mutation was not blocked!");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nExternal tag mutation blocked (UnsupportedOperationException)");
        }

        System.out.println("\nTicket after attempted mutation: " + escalated);
    }
}
