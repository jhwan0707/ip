package johan;

import johan.command.Command;
import johan.parser.Parser;
import johan.storage.Storage;
import johan.task.Task;
import johan.task.TaskList;
import johan.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Johan {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public Johan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        ArrayList<Task> loadedTasks;
        try {
            loadedTasks = storage.loadTasks();
        } catch (Exception e) {
            ui.showError("Failed to load tasks: " + e.getMessage());
            loadedTasks = new ArrayList<>();
        }
        tasks = new TaskList(loadedTasks);
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Johan("./data/johan.txt").run();
    }

    public static LocalDate parseDate(String dateStr) {
        try {
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy").withLocale(java.util.Locale.ENGLISH);
            return LocalDate.parse(dateStr.trim(), formatter1);
        } catch (Exception e) {
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(java.util.Locale.ENGLISH);
            return LocalDate.parse(dateStr.trim(), formatter2);
        }
    }
}

//import java.time.LocalDate;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.Scanner;
//import java.util.ArrayList;
//
//public class johan.Johan {
//    private static final String FILE_PATH = "./data/johan.txt";
//    private static ArrayList<johan.task.Task> tasks = new ArrayList<>();
//    private static johan.storage.Storage storage;
//
//    public static void main(String[] args) {
//        storage = new johan.storage.Storage(FILE_PATH);
//        tasks = storage.loadTasks();
//        String input;
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Hello! I'm johan.Johan");
//        System.out.println("What can I do for you?");
//        // johan.task.Task[] tasks = new johan.task.Task[100];
//        // ArrayList<johan.task.Task> tasks = new ArrayList<>();
//        // int taskCount = 0;
//
//        while (true) {
//            input = scanner.nextLine().toLowerCase().trim();
//            // String[] words = input.split("");
//            if (input.equals("bye")) {
//                break;
//            } else if (input.equals("list")) {
//                System.out.println("Here are the tasks in your list:");
//                for (int i = 0; i < tasks.size(); i++) {
//                    System.out.println((i + 1) + "." + tasks.get(i).toString());
//                }
//            } else if (input.startsWith("mark ")) {
//                String taskID = input.substring(5);
//                int id = Integer.parseInt(taskID);
//                if (id > 0 && id <= tasks.size()) {
//                    tasks.get(id - 1).markAsDone();
//                    System.out.println("Nice! I've marked this task as done:");
//                    System.out.println(tasks.get(id - 1).toString());
//                    storage.saveTasks(tasks);
//                }
//            } else if (input.startsWith("unmark ")) {
//                String taskID = input.substring(7);
//                int id = Integer.parseInt(taskID);
//                if (id > 0 && id <= tasks.size()) {
//                    tasks.get(id - 1).markAsNotDone();
//                    System.out.println("OK, I've marked this task as not done yet:");
//                    System.out.println(tasks.get(id - 1).toString());
//                    storage.saveTasks(tasks);
//                }
//            } else if (input.startsWith("todo")) {
//                String description = input.substring(4).trim();
//                if (description.isEmpty()) {
//                    System.out.println("____________________________________________________________");
//                    System.out.println(" OOPS!!! The description of a todo cannot be empty.");
//                    System.out.println("____________________________________________________________");
//                } else {
//                    tasks.add(new johan.task.Todo(description));
//                    System.out.println("Got it. I've added this task:");
//                    System.out.println(tasks.get(tasks.size() - 1).toString());
//                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
//                    storage.saveTasks(tasks);
//                }
//                // tasks[taskCount++] = new johan.task.Todo(description);
//                // System.out.println("Got it. I've added this task:");
//                // System.out.println(tasks[taskCount - 1].toString());
//                // System.out.println("Now you have " + taskCount + " tasks in the list.");
//            } else if (input.startsWith("deadline")) {
//                int byIndex = input.indexOf("/by");
//                if (byIndex == -1) {
//                    System.out.println("____________________________________________________________");
//                    System.out.println(" OOPS!!! Please specify a deadline with /by in d/M/yyyy format (e.g., 2/12/2019).");
//                    System.out.println("____________________________________________________________");
//                    continue;
//                }
//                String description = input.substring(9, byIndex).trim();
//                String by = input.substring(byIndex + 4);
//                try {
//                    // DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
//                    // LocalDate dateTime = LocalDate.parse(by, inputFormatter);
//                    tasks.add(new johan.task.Deadline(description, by));
//                    System.out.println("Got it. I've added this task:");
//                    System.out.println(tasks.get(tasks.size() - 1).toString());
//                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
//                    storage.saveTasks(tasks);
//                } catch (Exception e) {
//                    System.out.println("____________________________________________________________");
//                    System.out.println(" OOPS!!! Invalid date format. Use d/M/yyyy (e.g., 2/12/2019).");
//                    System.out.println("____________________________________________________________");
//                }
//                // by = by.substring(0, 1).toUpperCase() + by.substring(1);
//            } else if (input.startsWith("event")) {
//                int fromIndex = input.indexOf("/from");
//                int toIndex = input.indexOf("/to");
//                if (fromIndex == -1 && toIndex == -1) {
//                    System.out.println("____________________________________________________________");
//                    System.out.println(" OOPS!!! Please specify start and end times with /from and /to in d/M/yyyy format (e.g., 2/12/2019).");
//                    System.out.println("____________________________________________________________");
//                    continue;
//                }
//                String description = input.substring(6, fromIndex).trim();
//                String startDate = input.substring(fromIndex + 6, toIndex).trim();
//                // startDate = startDate.substring(0, 1).toUpperCase() + startDate.substring(1);
//                // String endDate = input.substring(toIndex + 3).trim();
//                String endDate = input.substring(toIndex + 4).trim();
//                // endDate = endDate.substring(0, 1).toUpperCase() + endDate.substring(1);
//                try {
//                    // DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
//                    // System.out.println("Parsing date: " + startDate + " to " + endDate); // Debug
//                    // LocalDate dateTime = LocalDate.parse(startDate, inputFormatter);
//                    // LocalDate endDateTime = LocalDate.parse(endDate, inputFormatter);
//                    LocalDate dateTime = parseDate(startDate);
//                    LocalDate endDateTime = parseDate(startDate);
//                    tasks.add(new johan.task.Event(description, startDate, endDate));
//                    System.out.println("Got it. I've added this task:");
//                    System.out.println(tasks.get(tasks.size() - 1).toString());
//                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
//                    storage.saveTasks(tasks);
//                } catch (Exception e) {
//                    System.out.println("____________________________________________________________");
//                    System.out.println(" OOPS!!! Invalid date format. Use d/M/yyyy (e.g., 2/12/2019).");
//                    System.out.println("____________________________________________________________");
//                }
//            } else if (input.startsWith("delete")) {
//                String taskID = input.substring(7);
//                int id = Integer.parseInt(taskID);
//                if (id > 0 && id <= tasks.size()) {
//                    johan.task.Task removedTask = tasks.remove(id - 1);
//                    System.out.println("____________________________________________________________");
//                    System.out.println("Noted. I've removed this task:");
//                    System.out.println(removedTask.toString());
//                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
//                    System.out.println("____________________________________________________________");
//                    storage.saveTasks(tasks);
//                } else {
//                    System.out.println("____________________________________________________________");
//                    System.out.println(" OOPS!!! The task ID is invalid.");
//                    System.out.println("____________________________________________________________");
//                }
//            } else if (input.startsWith("on ")) { // fix this, showing everything despite not being on the right date
//                String dateStr = input.substring(3).trim();
//                try {
//                    // DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
//                    // LocalDate targetDate = LocalDate.parse(dateStr, dateFormatter);
//                    LocalDate targetDate = parseDate(dateStr);
//                    System.out.println("Tasks on " + targetDate.format(DateTimeFormatter.ofPattern("d/MM/yyyy")) + ":");
//                    boolean found = false;
//                    for (int i = 0; i < tasks.size(); i++) {
//                        johan.task.Task task = tasks.get(i);
//                        if (task instanceof johan.task.Deadline) {
//                            LocalDate deadline = ((johan.task.Deadline) task).getBy();
//                            if (deadline != null && deadline.equals(targetDate)) {
//                                System.out.println((i + 1) + "." + task.toString());
//                                found = true;
//                            }
//                        } else if (task instanceof johan.task.Event) {
//                            LocalDate startDate = ((johan.task.Event) task).getStartDate();
//                            LocalDate endDate = ((johan.task.Event) task).getEndDate();
//                            if (startDate != null && endDate != null) {
//                                if (!startDate.isAfter(targetDate) && !endDate.isBefore(targetDate)) {
//                                    System.out.println((i + 1) + "." + task.toString());
//                                    found = true;
//                                }
//                            }
//                        }
//                    }
//                    if (!found) {
//                        System.out.println("No tasks found on this date.");
//                    }
//                } catch (Exception e) {
//                    System.out.println("____________________________________________________________");
//                    System.out.println(" OOPS!!! Invalid date format. Use d/M/yyyy (e.g., 2/12/2019).");
//                    System.out.println("____________________________________________________________");
//                }
//            } else {
//                System.out.println("____________________________________________________________");
//                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
//                System.out.println("____________________________________________________________");
//            }
//        }
//        System.out.println("Bye. Hope to see you again soon!");
//    }
//
//    private static LocalDate parseDate(String dateStr) {
//        try {
//            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy").withLocale(java.util.Locale.ENGLISH);
//            return LocalDate.parse(dateStr.trim(), formatter1);
//        } catch (Exception e) {
//            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(java.util.Locale.ENGLISH);
//            return LocalDate.parse(dateStr.trim(), formatter2);
//        }
//    }
//}
