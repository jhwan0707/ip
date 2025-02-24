package johan.command;

import johan.command.AddCommand;
import johan.storage.Storage;
import johan.task.Task;
import johan.task.TaskList;
import johan.task.Todo;
import johan.ui.Ui;

public class TodoCommand extends AddCommand {
    public TodoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (description.isEmpty()) throw new IllegalArgumentException("The description of a todo cannot be empty.");
        Task task = new Todo(description);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }
}
