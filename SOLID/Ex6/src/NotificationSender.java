public abstract class NotificationSender {
    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        this.audit = audit;
    }

    public ValidationResult canSend(Notification n) {
        return ValidationResult.ok();
    }

    public abstract void send(Notification n);
}
