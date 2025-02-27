package johan;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI window in Johan.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Johan johan;
    private Image userImage = new Image(getClass().getResourceAsStream("/images/User.png"));
    private Image johanImage = new Image(getClass().getResourceAsStream("/images/Johan.png"));

    /**
     * Initializes the controller after FXML is loaded.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren()
                .add(DialogBox.getJohanDialog("Hello! I'm Johan. What can I do for you?", johanImage));
    }

    /**
     * Sets the Johan instance for this controller.
     *
     * @param j The Johan instance to use
     */
    public void setJohan(Johan j) {
        this.johan = j;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (!input.isEmpty()) {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
            try {
                johan.executeCommand(input, message -> dialogContainer.getChildren().add(
                        DialogBox.getJohanDialog(message, johanImage)));
                if (input.equals("bye")) {
                    javafx.application.Platform.exit();
                }
            } catch (Exception e) {
                dialogContainer.getChildren().add(DialogBox.getJohanDialog("Oops! " + e.getMessage(), johanImage));
            }
            userInput.clear();
        }
    }
}
