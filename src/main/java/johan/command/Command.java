package johan.command;

import johan.storage.Storage;
import johan.task.TaskList;
import johan.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
    public boolean isExit() {
        return false;
    }
}