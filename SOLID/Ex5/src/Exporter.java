public abstract class Exporter {

    public ValidationResult validate(ExportRequest req) {
        if (req == null) {
            return ValidationResult.error("Export request must not be null");
        }
        return ValidationResult.ok();
    }

    public abstract ExportResult export(ExportRequest req);
}
