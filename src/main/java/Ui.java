import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("Hello! I'm Johan");
        System.out.println("What can I do for you?");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i).toString());
        }
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskMarked(Task task, boolean isDone) {
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task.toString());
    }

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" OOPS!!! " + message);
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine().toLowerCase().trim();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showTasksOnDate(TaskList tasks, LocalDate targetDate) {
        System.out.println("Tasks on " + targetDate.format(DateTimeFormatter.ofPattern("d/MM/yyyy")) + ":");
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task instanceof Deadline) {
                LocalDate deadline = ((Deadline) task).getBy();
                if (deadline != null && deadline.equals(targetDate)) {
                    System.out.println((i + 1) + "." + task.toString());
                    found = true;
                }
            } else if (task instanceof Event) {
                LocalDate startDate = ((Event) task).getStartDate();
                LocalDate endDate = ((Event) task).getEndDate();
                if (startDate != null && endDate != null && !startDate.isAfter(targetDate) && !endDate.isBefore(targetDate)) {
                    System.out.println((i + 1) + "." + task.toString());
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No tasks found on this date.");
        }
    }
}