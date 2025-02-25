package johan.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import johan.command.Command;
import johan.command.TodoCommand;

import org.junit.jupiter.api.Test;

/**
 * Tests for the Parser class.
 */
public class ParserTest {
    @Test
    void parse_todoCommand_returnsTodoCommand() throws Exception {
        Parser parser = new Parser();
        Command command = parser.parse("todo read book");
        assertTrue(command instanceof TodoCommand, "Should return TodoCommand for 'todo' input");
    }

    @Test
    void parse_invalidInput_throwsException() {
        Parser parser = new Parser();
        assertThrows(Exception.class, () -> parser.parse("invalid garbage"), "Should throw for invalid input");
    }

    @Test
    void parse_emptyInput_throwsException() {
        Parser parser = new Parser();
        assertThrows(Exception.class, () -> parser.parse(""), "Should throw for empty input");
    }
}
