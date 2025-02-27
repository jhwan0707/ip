package johan.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     * @param description The task description
     * @param by The deadline date string
     */
    public Deadline(String description, String by) {
        super(description);
        // this.by = by;
        // DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        this.by = parseDate(by);
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
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    .withLocale(java.util.Locale.ENGLISH);
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
    /**
     * Compares deadlines chronologically by due date.
     *
     * @param other The other task to compare to
     * @return Negative if this deadline is earlier, positive if later, zero if same
     */
    @Override
    public int compareTo(Task other) {
        if (other instanceof Deadline) {
            return this.by.compareTo(((Deadline) other).by);
        }
        return super.compareTo(other);
    }
}
