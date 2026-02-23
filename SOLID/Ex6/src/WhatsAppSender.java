public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) {
        super(audit);
    }

    @Override
    public ValidationResult canSend(Notification n) {
        if (n.phone == null || !n.phone.startsWith("+")) {
            return ValidationResult.fail("phone must start with + and country code");
        }
        return ValidationResult.ok();
    }

    @Override
    public void send(Notification n) {
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
    }
}
