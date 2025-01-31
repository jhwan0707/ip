import java.util.Scanner;
import java.util.ArrayList;

public class Johan {
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Johan");
        System.out.println("What can I do for you?");
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            input = scanner.nextLine().toLowerCase().trim();
            String[] words = input.split("");
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(tasks[i].getID() + "." + tasks[i].toString());
                }
            } else if (input.startsWith("mark ")) {
                String taskID = input.substring(5);
                int id = Integer.parseInt(taskID);
                if (id > 0 && id <= tasks.length) {
                    tasks[id - 1].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks[id - 1].toString());
                }
            } else if (input.startsWith("unmark ")) {
                String taskID = input.substring(7);
                int id = Integer.parseInt(taskID);
                if (id > 0 && id <= tasks.length) {
                    tasks[id - 1].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks[id - 1].toString());
                }
            } else if (input.startsWith("todo")) {
                String description = input.substring(5).trim();
                tasks[taskCount++] = new Todo(description);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1].toString());
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            } else if (input.startsWith("deadline")) {
                int byIndex = input.indexOf("/by");
                String description = input.substring(9, byIndex).trim();
                String by = input.substring(byIndex + 4);
                by = by.substring(0, 1).toUpperCase() + by.substring(1);
                tasks[taskCount++] = new Deadline(description, by);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1].toString());
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            } else if (input.startsWith("event")) {
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");
                String description = input.substring(6, fromIndex).trim();
                String startDate = input.substring(fromIndex + 6, toIndex);
                startDate = startDate.substring(0, 1).toUpperCase() + startDate.substring(1);
                String endDate = input.substring(toIndex + 3).trim();
                endDate = endDate.substring(0, 1).toUpperCase() + endDate.substring(1);
                tasks[taskCount++] = new Event(description, startDate, endDate);
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[taskCount - 1].toString());
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            } else {
                System.out.println("added: " + input);
                tasks[taskCount++] = new Task(input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
