package johan.command;

import java.util.ArrayList;

import johan.storage.Storage;
import johan.task.Task;
import johan.task.TaskList;
import johan.ui.Ui;

/**
 * Command to find tasks containing a specific keyword in their description.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified search keyword.
     *
     * @param keyword The keyword to search for in task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find and display tasks matching the keyword.
     *
     * @param tasks The task list to search
     * @param ui The user interface for displaying results
     * @param storage The storage system (unused in this command)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().toLowerCase().contains(this.keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        ui.showFoundTasks(matchingTasks);
    }
}
