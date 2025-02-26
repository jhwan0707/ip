package johan.command;

import java.time.LocalDate;

import johan.Johan;
import johan.storage.Storage;
import johan.task.TaskList;
import johan.ui.Ui;

/**
 * Command to show tasks occurring on a specific date.
 */
public class OnDateCommand extends Command {
    private final LocalDate targetDate;

    /**
     * Constructs an OnDateCommand for the specified date string.
     * @param dateStr The date string to parse
     */
    public OnDateCommand(String dateStr) {
        this.targetDate = Johan.parseDate(dateStr);
    }

    /**
     * Displays all tasks occurring on the target date.
     * @param tasks The task list to operate on
     * @param ui The user interface for displaying output
     * @param storage The storage system for persisting tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasksOnDate(tasks, targetDate);
    }
}
