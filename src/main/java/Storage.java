import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
                if (parts.length < 3) continue;

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

        String status = task.isDone ? "1" : "0";
        String description = task.description;

        if (task instanceof Deadline) {
            LocalDate deadline = ((Deadline) task).getBy();
            return type + " | " + status + " | " + description + " | " + deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        } else if (task instanceof Event) {
            LocalDate startDate = ((Event) task).getStartDate();
            LocalDate endDate = ((Event) task).getEndDate();
            return type + " | " + status + " | " + description + " | " + startDate.format(DateTimeFormatter.ofPattern("d/M/yyyy")) + " | " + endDate.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        } else {
            return type + " | " + status + " | " + description;
        }
    }
}
