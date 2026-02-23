public class Main {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());
        Exporter pdf = new PdfExporter();
        Exporter csv = new CsvExporter();
        Exporter json = new JsonExporter();

        System.out.println("PDF: " + tryExport(pdf, req));
        System.out.println("CSV: " + tryExport(csv, req));
        System.out.println("JSON: " + tryExport(json, req));
    }

    private static String tryExport(Exporter exporter, ExportRequest req) {
        ValidationResult validation = exporter.validate(req);
        if (!validation.isValid()) {
            return "ERROR: " + validation.getErrorMessage();
        }
        ExportResult result = exporter.export(req);
        return "OK bytes=" + result.bytes.length;
    }
}
