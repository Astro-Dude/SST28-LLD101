import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {

    @Override
    public ExportResult export(ExportRequest req) {
        String title = req.title == null ? "" : req.title;
        String body = req.body == null ? "" : sanitizeForCsv(req.body);
        String csv = "title,body\n" + title + "," + body + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }

    private String sanitizeForCsv(String value) {
        return value.replace("\n", " ").replace(",", " ");
    }
}
