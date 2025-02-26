package johan.command;

import johan.storage.Storage;
import johan.task.Task;
import johan.task.TaskList;
import johan.ui.Ui;

/**
 * Command to mark a task as done or not done.
 */
public class MarkCommand extends Command {
    private final int taskIndex;
    private final boolean markAsDone;

    /**
     * Constructs a MarkCommand for the specified task and mark status.
     * @param taskIndex The zero-based index of the task to mark
     * @param markAsDone True to mark as done, false to mark as not done
     */
    public MarkCommand(int taskIndex, boolean markAsDone) {
        this.taskIndex = taskIndex;
        this.markAsDone = markAsDone;
    }

    /**
     * Marks the specified task and updates storage.
     * @param tasks The task list to operate on
     * @param ui The user interface for displaying output
     * @param storage The storage system for persisting tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(taskIndex);
        if (markAsDone) {
            task.markAsDone();
            ui.showTaskMarked(task, true);
        } else {
            task.markAsNotDone();
            ui.showTaskMarked(task, false);
        }
        storage.saveTasks(tasks.getTasks());
    }
}
