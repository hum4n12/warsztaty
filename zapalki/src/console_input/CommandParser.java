package console_input;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {
    public List<Command> parseConsoleInput(String line) {
        List<Command> commands = new ArrayList<>();
        String[] cmdParts = line.split(" ");
        if (ResourcesCommand.isParsingPossible(cmdParts)) {
            commands.add(ResourcesCommand.parse(cmdParts));
        } else {
            System.out.println("ERROR: Command not recognized");
        }
        return commands;
    }


}
