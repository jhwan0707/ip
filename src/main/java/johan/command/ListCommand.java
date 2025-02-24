package johan.command;

import johan.storage.Storage;
import johan.task.TaskList;
import johan.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}