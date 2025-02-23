public class Parser {
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
            if (byIndex == -1) throw new IllegalArgumentException("Please specify a deadline with /by.");
            String desc = input.substring(9, byIndex).trim();
            String by = input.substring(byIndex + 4).trim();
            return new DeadlineCommand(desc, by);
        } else if (input.startsWith("event ")) {
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            if (fromIndex == -1 || toIndex == -1) throw new IllegalArgumentException("Please specify /from and /to.");
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
//import java.time.LocalDate;
//
//public class Parser {
//    public boolean parseAndExecute(String input, TaskList tasks, Ui ui, Storage storage) {
//        if (input.equals("bye")) {
//            return false;
//        } else if (input.equals("list")) {
//            ui.showTaskList(tasks);
//        } else if (input.startsWith("mark ")) {
//            int id = Integer.parseInt(input.substring(5)) - 1;
//            Task task = tasks.getTask(id);
//            task.markAsDone();
//            ui.showTaskMarked(task, true);
//            storage.saveTasks(tasks.getTasks());
//        } else if (input.startsWith("unmark ")) {
//            int id = Integer.parseInt(input.substring(7)) - 1;
//            Task task = tasks.getTask(id);
//            task.markAsNotDone();
//            ui.showTaskMarked(task, false);
//            storage.saveTasks(tasks.getTasks());
//        } else if (input.startsWith("todo ")) {
//            String desc = input.substring(5).trim();
//            if (desc.isEmpty()) throw new IllegalArgumentException("The description of a todo cannot be empty.");
//            Task task = new Todo(desc);
//            tasks.addTask(task);
//            ui.showTaskAdded(task, tasks.size());
//            storage.saveTasks(tasks.getTasks());
//        } else if (input.startsWith("deadline ")) {
//            int byIndex = input.indexOf("/by");
//            if (byIndex == -1) throw new IllegalArgumentException("Please specify a deadline with /by.");
//            String desc = input.substring(9, byIndex).trim();
//            String by = input.substring(byIndex + 4).trim();
//            Task task = new Deadline(desc, by);
//            tasks.addTask(task);
//            ui.showTaskAdded(task, tasks.size());
//            storage.saveTasks(tasks.getTasks());
//        } else if (input.startsWith("event ")) {
//            int fromIndex = input.indexOf("/from");
//            int toIndex = input.indexOf("/to");
//            if (fromIndex == -1 || toIndex == -1) throw new IllegalArgumentException("Please specify /from and /to.");
//            String desc = input.substring(6, fromIndex).trim();
//            String from = input.substring(fromIndex + 6, toIndex).trim();
//            String to = input.substring(toIndex + 4).trim();
//            Task task = new Event(desc, from, to);
//            tasks.addTask(task);
//            ui.showTaskAdded(task, tasks.size());
//            storage.saveTasks(tasks.getTasks());
//        } else if (input.startsWith("delete ")) {
//            int id = Integer.parseInt(input.substring(7)) - 1;
//            Task task = tasks.deleteTask(id);
//            ui.showTaskDeleted(task, tasks.size());
//            storage.saveTasks(tasks.getTasks());
//        } else if (input.startsWith("on ")) {
//            String dateStr = input.substring(3).trim();
//            LocalDate targetDate = Johan.parseDate(dateStr);
//            ui.showTasksOnDate(tasks, targetDate);
//        } else {
//            throw new IllegalArgumentException("I'm sorry, but I don't know what that means :-(");
//        }
//        return true;
//    }
//}