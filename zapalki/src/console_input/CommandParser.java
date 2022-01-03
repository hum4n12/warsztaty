package console_input;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {
    public List<Command> parseConsoleInput(String line) {
        List<Command> commands = new ArrayList<>();
        String[] cmdParts = line.split(" ");

        if (ResourcesCommand.isParsingPossible(cmdParts)) {
            commands.add(ResourcesCommand.parse(cmdParts));
        } else if(BuyCommand.isParsingPossible(cmdParts)){
            commands.add(BuyCommand.parse(cmdParts));
        } else if(PutCommand.isParsingPossible(cmdParts)){
            commands.add(PutCommand.parse(cmdParts));
        } else if(BurnCommand.isParsingPossible(cmdParts)){
            commands.add(BurnCommand.parse(cmdParts));
        } else if(ResetCommand.isParsingPossible(cmdParts)){
            commands.add(ResetCommand.parse(cmdParts));
        }
        else {
            System.out.println("ERROR: Command not recognized");
        }
        return commands;
    }


}
