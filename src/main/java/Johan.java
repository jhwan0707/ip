import java.util.Scanner;
import java.util.ArrayList;

public class Johan {
    private static final String FILE_PATH = "./data/johan.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Storage storage;

    public static void main(String[] args) {
        storage = new Storage(FILE_PATH);
        tasks = storage.loadTasks();
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Johan");
        System.out.println("What can I do for you?");
        // Task[] tasks = new Task[100];
        // ArrayList<Task> tasks = new ArrayList<>();
        // int taskCount = 0;

        while (true) {
            input = scanner.nextLine().toLowerCase().trim();
            // String[] words = input.split("");
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i).toString());
                }
            } else if (input.startsWith("mark ")) {
                String taskID = input.substring(5);
                int id = Integer.parseInt(taskID);
                if (id > 0 && id <= tasks.size()) {
                    tasks.get(id - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(id - 1).toString());
                    storage.saveTasks(tasks);
                }
            } else if (input.startsWith("unmark ")) {
                String taskID = input.substring(7);
                int id = Integer.parseInt(taskID);
                if (id > 0 && id <= tasks.size()) {
                    tasks.get(id - 1).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(id - 1).toString());
                    storage.saveTasks(tasks);
                }
            } else if (input.startsWith("todo")) {
                String description = input.substring(4).trim();
                if (description.isEmpty()) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("____________________________________________________________");
                } else {
                    tasks.add(new Todo(description));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    storage.saveTasks(tasks);
                }
                // tasks[taskCount++] = new Todo(description);
                // System.out.println("Got it. I've added this task:");
                // System.out.println(tasks[taskCount - 1].toString());
                // System.out.println("Now you have " + taskCount + " tasks in the list.");
            } else if (input.startsWith("deadline")) {
                int byIndex = input.indexOf("/by");
                String description = input.substring(9, byIndex).trim();
                String by = input.substring(byIndex + 4);
                by = by.substring(0, 1).toUpperCase() + by.substring(1);
                tasks.add(new Deadline(description, by));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(tasks.size() - 1).toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                storage.saveTasks(tasks);
            } else if (input.startsWith("event")) {
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");
                String description = input.substring(6, fromIndex).trim();
                String startDate = input.substring(fromIndex + 6, toIndex);
                startDate = startDate.substring(0, 1).toUpperCase() + startDate.substring(1);
                String endDate = input.substring(toIndex + 3).trim();
                endDate = endDate.substring(0, 1).toUpperCase() + endDate.substring(1);
                tasks.add(new Event(description, startDate, endDate));
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(tasks.size() - 1).toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                storage.saveTasks(tasks);
            } else if (input.startsWith("delete")) {
                String taskID = input.substring(7);
                int id = Integer.parseInt(taskID);
                if (id > 0 && id <= tasks.size()) {
                    Task removedTask = tasks.remove(id - 1);
                    System.out.println("____________________________________________________________");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(removedTask.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    storage.saveTasks(tasks);
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! The task ID is invalid.");
                    System.out.println("____________________________________________________________");
                }
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
