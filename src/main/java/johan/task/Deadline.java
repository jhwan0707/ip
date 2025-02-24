package johan.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        // this.by = by;
        // DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        this.by = parseDate(by);
    }

    private static LocalDate parseDate(String dateString) {
        try {
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy").withLocale(java.util.Locale.ENGLISH);
            return LocalDate.parse(dateString.trim(), formatter1);
        } catch (Exception e) {
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(java.util.Locale.ENGLISH);
            return LocalDate.parse(dateString.trim(), formatter2);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    public LocalDate getBy() {
        return by;
    }
}
