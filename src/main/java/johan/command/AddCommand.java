package johan.command;

import johan.storage.Storage;
import johan.task.*;
import johan.ui.Ui;

public abstract class AddCommand extends Command {
    protected final String description;

    public AddCommand(String description) {
        this.description = description;
    }

    @Override
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
}




