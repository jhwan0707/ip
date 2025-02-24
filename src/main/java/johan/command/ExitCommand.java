package johan.command;

import johan.storage.Storage;
import johan.task.TaskList;
import johan.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // No action needed; isExit() handles termination
    }

    @Override
    public boolean isExit() {
        return true;
    }
}