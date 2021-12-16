package zapalki;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import console_input.Command;
import console_input.CommandParser;
import resources.Resources;

public class Main {
    private static final Resources resources = new Resources(0,0,0);
    private static final List<MatchBox> boxes = new ArrayList<>();
    private static final List<Match> matches = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("Usage:\n"
                + "add <resourceName> <resourceAmount> - to add resources"
                + "sub <resourceName> <resourceAmount> - to subtract resources"
                + "buy <itemName> <amount> - to buy items"
                + "burn <itemType[match,box]> <item subtype[small,large] if type is 'box'> <amount> - to watch it burn"
                + "put <box type[small,large]> <amount> - to put random matches to a box of given size");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String commandString;
        CommandParser commandFactory = new CommandParser();
        while (!(commandString = reader.readLine()).equalsIgnoreCase("q")) {
            List<Command> commands = commandFactory.parseConsoleInput(commandString);
            commands.forEach(cmd -> cmd.execute(resources));
            printCurrentStatus();
        }
    }

    private static void printCurrentStatus() {
        System.out.println(resources);
        System.out.println(boxes);
        System.out.println(matches);
    }
}
