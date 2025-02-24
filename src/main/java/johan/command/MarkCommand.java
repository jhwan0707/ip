package johan.command;

import johan.storage.Storage;
import johan.task.Task;
import johan.task.TaskList;
import johan.ui.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;
    private final boolean markAsDone;

    public MarkCommand(int taskIndex, boolean markAsDone) {
        this.taskIndex = taskIndex;
        this.markAsDone = markAsDone;
    }

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