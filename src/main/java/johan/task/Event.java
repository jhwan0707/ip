package johan.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a start and end date/time.
 */
public class Event extends Task {

    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructs an Event task with the specified description and time range.
     * @param description The task description
     * @param startDate The start date string
     * @param endDate The end date string
     */
    public Event(String description, String startDate, String endDate) {
        super(description);
        // DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        // this.startDate = LocalDate.parse(startDate, inputFormatter);
        // this.endDate = LocalDate.parse(endDate, inputFormatter);
        this.startDate = parseDate(startDate);
        this.endDate = parseDate(endDate);
    }

    /**
     * Parses a date string into a LocalDate object.
     * @param dateString The date string to parse
     * @return The parsed LocalDate
     */
    private static LocalDate parseDate(String dateString) {
        try {
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy").withLocale(java.util.Locale.ENGLISH);
            return LocalDate.parse(dateString.trim(), formatter1);
        } catch (Exception e) {
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(java.util.Locale.ENGLISH);
            return LocalDate.parse(dateString.trim(), formatter2);
        }
    }

    /**
     * Returns a string representation of the event task.
     * @return The formatted string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Gets the start date of the event.
     * @return The start date as a LocalDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Gets the end date of the event.
     * @return The end date as a LocalDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }
}
