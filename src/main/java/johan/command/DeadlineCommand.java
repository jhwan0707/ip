package johan.command;

import johan.storage.Storage;
import johan.task.Deadline;
import johan.task.Task;
import johan.task.TaskList;
import johan.ui.Ui;

public class DeadlineCommand extends AddCommand {
    private final String by;

    public DeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }
}
