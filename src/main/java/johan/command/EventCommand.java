package johan.command;

import johan.storage.Storage;
import johan.task.Event;
import johan.task.Task;
import johan.task.TaskList;
import johan.ui.Ui;

/**
 * Command to add an event task to the task list.
 */
public class EventCommand extends AddCommand {
    private final String from;
    private final String to;

    /**
     * Constructs an EventCommand with the specified description and time range.
     * @param description The description of the event
     * @param from The start date/time string
     * @param to The end date/time string
     */
    public EventCommand(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Adds an event task to the task list and updates storage.
     * @param tasks The task list to modify
     * @param ui The user interface for displaying output
     * @param storage The storage system for persisting tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }
}
