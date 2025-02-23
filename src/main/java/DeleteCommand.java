public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.deleteTask(taskIndex);
        ui.showTaskDeleted(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }
}