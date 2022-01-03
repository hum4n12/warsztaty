package zapalki;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import console_input.Command;
import console_input.CommandParser;
import console_input.Items;
import resources.Resources;

public class Main {
    private static final Items items = new Items();

    public static void main(String[] args) throws IOException {
        System.out.println("Usage:\n"
                + "add <resourceName> <resourceAmount> - to add resources"
                + "\nsub <resourceName> <resourceAmount> - to subtract resources"
                + "\nbuy <itemName> <amount> - to buy items"
                + "\nburn <itemType[match,box]> <item subtype[small,large] if type is 'box'> <amount> - to watch it burn"
                + "\nput <box type[small,large]> <amount> - to put random matches to a box of given size"
                + "\nreset - to reset state of program");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String commandString;
        CommandParser commandFactory = new CommandParser();
        while (!(commandString = reader.readLine()).equalsIgnoreCase("q")) {
            List<Command> commands = commandFactory.parseConsoleInput(commandString);
            commands.forEach(cmd -> cmd.execute(items));
            printCurrentStatus();
        }
    }

    private static void printCurrentStatus() {
        System.out.println(items.getResources());
        System.out.println(items.getBoxes());
        System.out.println(items.getMatches());
    }
}
