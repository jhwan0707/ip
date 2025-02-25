package johan.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Ui class.
 */
public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void readCommand_validInput_returnsInput() {
        // Simulate user input
        String input = "todo read book\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Ui ui = new Ui();
        String result = ui.readCommand();

        assertEquals("todo read book", result.trim(), "Should return trimmed user input");
    }

    @Test
    void readCommand_emptyInput_returnsEmpty() {
        // Simulate empty input
        String input = "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Ui ui = new Ui();
        String result = ui.readCommand();

        assertEquals("", result.trim(), "Should return empty string for empty input");
    }
}