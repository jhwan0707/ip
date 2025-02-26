package johan.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import johan.task.Deadline;
import johan.task.Event;
import johan.task.Task;
import johan.task.Todo;


/**
 * Handles storage and retrieval of tasks to/from a file
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage instance with the specified file path.
     * @param filePath The path to the storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the task list to the storage file.
     * @param tasks The list of tasks to save
     */
    public void saveTasks(ArrayList<Task> tasks) {
        File file = new File(filePath);
        File directory = new File(file.getParent());

        if (!directory.exists()) {
            directory.mkdirs(); // Ensure directory exists
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(formatTaskForSaving(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage files.
     * @return The list of loaded tasks
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No saved tasks found. Starting fresh.");
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    continue;
                }

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task;
                if (type.equals("T")) {
                    task = new Todo(description);
                } else if (type.equals("D") && parts.length == 4) {
                    try {
                        // LocalDate deadline = LocalDate.parse(parts[3], inputFormatter);
                        task = new Deadline(description, parts[3]);
                    } catch (Exception e) {
                        System.out.println("Error parsing deadline for task: " + description + ". Skipping...");
                        continue;
                    }
                } else if (type.equals("E") && parts.length == 5) {
                    try {
                        // LocalDate from = LocalDate.parse(parts[3], inputFormatter);
                        // LocalDate to = LocalDate.parse(parts[4], inputFormatter);
                        task = new Event(description, parts[3], parts[4]);
                    } catch (Exception e) {
                        System.out.println("Error parsing deadline for task: " + description + ". Skipping...");
                        continue;
                    }
                } else {
                    continue; // Ignore invalid entries
                }

                if (isDone) {
                    task.markAsDone();
                }

                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Formats a task for saving to the file.
     * @param task The task to format
     * @return The formatted string representation
     */
    private String formatTaskForSaving(Task task) {
        String type;
        if (task instanceof Todo) {
            type = "T";
        } else if (task instanceof Deadline) {
            type = "D";
        } else if (task instanceof Event) {
            type = "E";
        } else {
            return "";
        }

        String status = task.isDone() ? "1" : "0";
        String description = task.getDescription();

        if (task instanceof Deadline) {
            LocalDate deadline = ((Deadline) task).getBy();
            return type + " | " + status + " | " + description + " | "
                    + deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        } else if (task instanceof Event) {
            LocalDate startDate = ((Event) task).getStartDate();
            LocalDate endDate = ((Event) task).getEndDate();
            return type + " | " + status + " | " + description + " | "
                    + startDate.format(DateTimeFormatter.ofPattern("d/M/yyyy")) + " | "
                    + endDate.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        } else {
            return type + " | " + status + " | " + description;
        }
    }
}
