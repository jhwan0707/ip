import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event extends Task {

    protected LocalDate startDate;
    protected LocalDate endDate;

    public Event(String description, String startDate, String endDate) {
        super(description);
        // DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        // this.startDate = LocalDate.parse(startDate, inputFormatter);
        // this.endDate = LocalDate.parse(endDate, inputFormatter);
        this.startDate = parseDate(startDate);
        this.endDate = parseDate(endDate);
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
        return "[E]" + super.toString() +
                " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
