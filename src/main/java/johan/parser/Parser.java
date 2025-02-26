package johan.parser;

import johan.command.Command;
import johan.command.ExitCommand;
import johan.command.ListCommand;
import johan.command.MarkCommand;
import johan.command.DeleteCommand;
import johan.command.OnDateCommand;
import johan.command.DeadlineCommand;
import johan.command.EventCommand;
import johan.command.TodoCommand;

/**
 * Parses user input into executable commands.
 */
public class Parser {
    /**
     * Parses the input string into a corresponding Command object.
     * @param input The user input string to parse
     * @return The corresponding Command object
     * @throws Exception If the input cannot be parsed
     */
    public Command parse(String input) throws Exception {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("mark ")) {
            int id = Integer.parseInt(input.substring(5)) - 1;
            return new MarkCommand(id, true);
        } else if (input.startsWith("unmark ")) {
            int id = Integer.parseInt(input.substring(7)) - 1;
            return new MarkCommand(id, false);
        } else if (input.startsWith("todo ")) {
            String desc = input.substring(5).trim();
            return new TodoCommand(desc);
        } else if (input.startsWith("deadline ")) {
            int byIndex = input.indexOf("/by");
            if (byIndex == -1) {
                throw new IllegalArgumentException("Please specify a deadline with /by.");
            }
            String desc = input.substring(9, byIndex).trim();
            String by = input.substring(byIndex + 4).trim();
            return new DeadlineCommand(desc, by);
        } else if (input.startsWith("event ")) {
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            if (fromIndex == -1 || toIndex == -1) {
                throw new IllegalArgumentException("Please specify /from and /to.");
            }
            String desc = input.substring(6, fromIndex).trim();
            String from = input.substring(fromIndex + 6, toIndex).trim();
            String to = input.substring(toIndex + 4).trim();
            return new EventCommand(desc, from, to);
        } else if (input.startsWith("delete ")) {
            int id = Integer.parseInt(input.substring(7)) - 1;
            return new DeleteCommand(id);
        } else if (input.startsWith("on ")) {
            String dateStr = input.substring(3).trim();
            return new OnDateCommand(dateStr);
        } else {
            throw new IllegalArgumentException("I'm sorry, but I don't know what that means :-(");
        }
    }
}

/*
import java.time.LocalDate;

public class johan.parser.Parser {
    public boolean parseAndExecute(String input, johan.task.TaskList tasks, johan.ui.Ui ui, johan.storage.Storage storage) {
        if (input.equals("bye")) {
            return false;
        } else if (input.equals("list")) {
            ui.showTaskList(tasks);
        } else if (input.startsWith("mark ")) {
            int id = Integer.parseInt(input.substring(5)) - 1;
            johan.task.Task task = tasks.getTask(id);
            task.markAsDone();
            ui.showTaskMarked(task, true);
            storage.saveTasks(tasks.getTasks());
        } else if (input.startsWith("unmark ")) {
            int id = Integer.parseInt(input.substring(7)) - 1;
            johan.task.Task task = tasks.getTask(id);
            task.markAsNotDone();
            ui.showTaskMarked(task, false);
            storage.saveTasks(tasks.getTasks());
        } else if (input.startsWith("todo ")) {
            String desc = input.substring(5).trim();
            if (desc.isEmpty()) throw new IllegalArgumentException("The description of a todo cannot be empty.");
            johan.task.Task task = new johan.task.Todo(desc);
            tasks.addTask(task);
            ui.showTaskAdded(task, tasks.size());
            storage.saveTasks(tasks.getTasks());
        } else if (input.startsWith("deadline ")) {
            int byIndex = input.indexOf("/by");
            if (byIndex == -1) throw new IllegalArgumentException("Please specify a deadline with /by.");
            String desc = input.substring(9, byIndex).trim();
            String by = input.substring(byIndex + 4).trim();
            johan.task.Task task = new johan.task.Deadline(desc, by);
            tasks.addTask(task);
            ui.showTaskAdded(task, tasks.size());
            storage.saveTasks(tasks.getTasks());
        } else if (input.startsWith("event ")) {
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            if (fromIndex == -1 || toIndex == -1) throw new IllegalArgumentException("Please specify /from and /to.");
            String desc = input.substring(6, fromIndex).trim();
            String from = input.substring(fromIndex + 6, toIndex).trim();
            String to = input.substring(toIndex + 4).trim();
            johan.task.Task task = new johan.task.Event(desc, from, to);
            tasks.addTask(task);
            ui.showTaskAdded(task, tasks.size());
            storage.saveTasks(tasks.getTasks());
        } else if (input.startsWith("delete ")) {
            int id = Integer.parseInt(input.substring(7)) - 1;
            johan.task.Task task = tasks.deleteTask(id);
            ui.showTaskDeleted(task, tasks.size());
            storage.saveTasks(tasks.getTasks());
        } else if (input.startsWith("on ")) {
            String dateStr = input.substring(3).trim();
            LocalDate targetDate = johan.Johan.parseDate(dateStr);
            ui.showTasksOnDate(tasks, targetDate);
        } else {
            throw new IllegalArgumentException("I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }
}
*/
