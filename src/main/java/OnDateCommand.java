import java.time.LocalDate;

public class OnDateCommand extends Command {
    private final LocalDate targetDate;

    public OnDateCommand(String dateStr) {
        this.targetDate = Johan.parseDate(dateStr);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasksOnDate(tasks, targetDate);
    }
}
