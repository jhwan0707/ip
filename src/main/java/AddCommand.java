public abstract class AddCommand extends Command {
    protected final String description;

    public AddCommand(String description) {
        this.description = description;
    }

    @Override
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
}

class TodoCommand extends AddCommand {
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

class DeadlineCommand extends AddCommand {
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

class EventCommand extends AddCommand {
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