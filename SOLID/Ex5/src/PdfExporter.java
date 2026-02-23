import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {

    private static final int MAX_BODY_LENGTH = 20;

    @Override
    public ValidationResult validate(ExportRequest req) {
        ValidationResult base = super.validate(req);
        if (!base.isValid()) {
            return base;
        }
        String body = req.body == null ? "" : req.body;
        if (body.length() > MAX_BODY_LENGTH) {
            return ValidationResult.error("PDF cannot handle content > " + MAX_BODY_LENGTH + " chars");
        }
        return ValidationResult.ok();
    }

    @Override
    public ExportResult export(ExportRequest req) {
        String title = req.title == null ? "" : req.title;
        String body = req.body == null ? "" : req.body;
        String fakePdf = "PDF(" + title + "):" + body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
