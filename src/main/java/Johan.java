import java.util.Scanner;
import java.util.ArrayList;

public class Johan {
    public static void main(String[] args) {
        String input;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Johan");
        System.out.println("What can I do for you?");
        ArrayList<Task> tasks = new ArrayList<>();
        while (true) {
            input = scanner.nextLine().toLowerCase().trim();
            String[] words = input.split("");
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (Task t : tasks) {
                    System.out.println(t.getID() + ". [" + t.getStatusIcon() + "] " + t.toString());
                }
            } else if (input.startsWith("mark ")) {
                String taskID = input.substring(5);
                int id = Integer.parseInt(taskID);
                if (id > 0 && id <= tasks.size()) {
                    tasks.get(id - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + tasks.get(id - 1).getStatusIcon() + "] " + tasks.get(id - 1).toString());
                }
            } else if (input.startsWith("unmark ")) {
                String taskID = input.substring(7);
                int id = Integer.parseInt(taskID);
                if (id > 0 && id <= tasks.size()) {
                    tasks.get(id - 1).markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + tasks.get(id - 1).getStatusIcon() + "] " + tasks.get(id - 1).toString());
                }
            } else {
                System.out.println("added: " + input);
                tasks.add(new Task(input));
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
