package johan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.function.Consumer;

import johan.command.Command;
import johan.parser.Parser;
import johan.storage.Storage;
import johan.task.Task;
import johan.task.TaskList;
import johan.ui.Ui;


// to run
// from repos dir >> javac -d bin src/main/java/johan/*.java src/main/java/johan/*/*.java
// java -cp bin johan.Johan

/**
 * Main class for the Johan task management application.
 */
public class Johan {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs a Johan instance with the specified storage file path.
     *
     * @param filePath The path to the storage file
     */
    public Johan(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        this.tasks = loadTasks();
    }
    /**
     * Constructs a Johan instance with explicit dependencies (GUI mode).
     * @param storage The storage instance
     * @param tasks The task list instance
     * @param parser The parser instance
     */
    public Johan(Storage storage, TaskList tasks, Parser parser) {
        this.storage = storage;
        this.tasks = tasks;
        this.parser = parser;
        this.ui = null;
    }
    private TaskList loadTasks() {
        ArrayList<Task> loadedTasks;
        try {
            loadedTasks = storage.loadTasks();
        } catch (Exception e) {
            ui.showError("Failed to load tasks: " + e.getMessage());
            loadedTasks = new ArrayList<>();
        }
        return new TaskList(loadedTasks);
    }
    /**
     * Runs the main application loop, processing user commands until exit.
     */
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

    /**
     * Executes a user command and sends output to the provided consumer (GUI mode).
     * @param input The user command string
     * @param outputConsumer A consumer to handle output messages
     * @throws Exception If the command execution fails
     */
    public void executeCommand(String input, Consumer<String> outputConsumer) throws Exception {
        Ui guiUi = new Ui(outputConsumer);
        Command command = parser.parse(input);
        command.execute(tasks, guiUi, storage);
    }
    /**
     * Main entry point for the application.
     *
     * @param args Command-line arguments (unused)
     */
    public static void main(String[] args) {
        new Johan("./data/johan.txt").run();
    }
    /**
     * Parses a date string into a LocalDate object.
     *
     * @param dateStr The date string to parse
     * @return The parsed LocalDate
     */
    public static LocalDate parseDate(String dateStr) {
        try {
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("d/M/yyyy")
                    .withLocale(java.util.Locale.ENGLISH);
            return LocalDate.parse(dateStr.trim(), formatter1);
        } catch (Exception e) {
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    .withLocale(java.util.Locale.ENGLISH);
            return LocalDate.parse(dateStr.trim(), formatter2);
        }
    }
}
