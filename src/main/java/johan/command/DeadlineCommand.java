package johan.command;

import johan.storage.Storage;
import johan.task.Deadline;
import johan.task.Task;
import johan.task.TaskList;
import johan.ui.Ui;

/**
 * Command to add a deadline task to the task list.
 */
public class DeadlineCommand extends AddCommand {
    private final String by;

    /**
     * Constructs a DeadlineCommand with the specified description and deadline.
     * @param description The description of the deadline task
     * @param by The deadline date/time string
     */
    public DeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Adds a deadline task to the task list and updates storage.
     * @param tasks The task list to modify
     * @param ui The user interface for displaying output
     * @param storage The storage system for persisting tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }
}
