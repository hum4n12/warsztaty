package zapalki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CommandParser {
    public List<Command> parseConsoleInput(String line) {
        List<Parser> parsers = Arrays.asList(
                BurnCommand.getParser(),
                BuyCommand.getParser(),
                PutCommand.getParser(),
                ResourcesCommand.getParser(),
                ResetCommand.getParser(),
                HireCommand.getParser()
                );
        String[] cmdParts = line.split(" ");


        Optional<Parser> parser = parsers.stream()
                .filter(p -> p.isParsingPossible(cmdParts))
                .findAny();

        List<Command> commands = new ArrayList<>();
        parser.ifPresentOrElse(p -> {
            commands.add(p.parse(cmdParts));
        },() -> {
            GuiManager.print("ERROR: Command \"" + line + "\" not recognized");
        });

        return commands;
    }


}
