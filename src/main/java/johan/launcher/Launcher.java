package johan.launcher;

import javafx.application.Application;
import johan.Main;

/**
 * A launcher class to workaround classpath issues with JavaFX.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
