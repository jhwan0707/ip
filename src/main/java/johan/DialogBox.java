package johan;


import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box with text and an image in the Johan GUI.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image using FXML.
     *
     * @param text The text to display
     * @param image The image to display
     */
    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(image);
    }

    /**
     * Flips the dialog box so the image is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        var children = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(children); // Use Collections.reverse()
        this.getChildren().setAll(children);
    }

    /**
     * Creates a dialog box for a user message (image on right).
     *
     * @param text The user's message text
     * @param image The user's avatar image
     * @return A new DialogBox instance
     */
    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Creates a dialog box for a Johan message (image on left).
     *
     * @param text Johan's message text
     * @param image Johan's avatar image
     * @return A new DialogBox instance
     */
    public static DialogBox getJohanDialog(String text, Image image) {
        DialogBox db = new DialogBox(text, image);
        db.flip();
        return db;
    }
}
