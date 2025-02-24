package johan.command;

import johan.storage.Storage;
import johan.task.Event;
import johan.task.Task;
import johan.task.TaskList;
import johan.ui.Ui;

public class EventCommand extends AddCommand {
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Event(description, from, to);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }
}
