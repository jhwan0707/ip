package johan;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import johan.parser.Parser;
import johan.storage.Storage;
import johan.task.TaskList;

/**
 * The main JavaFX application class for Johan, providing a GUI interface using FXML.
 */
public class Main extends Application {
    private Johan johan;

    @Override
    public void start(Stage stage) {
        try {
            Storage storage = new Storage("./data/johan.txt");
            Parser parser = new Parser();
            ArrayList<johan.task.Task> loadedTasks;
            try {
                loadedTasks = storage.loadTasks();
            } catch (Exception e) {
                loadedTasks = new ArrayList<>();
            }
            TaskList tasks = new TaskList(loadedTasks);
            this.johan = new Johan(storage, tasks, parser);

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Johan Chatbot");
            stage.setResizable(false);
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.getController();
            controller.setJohan(johan);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
