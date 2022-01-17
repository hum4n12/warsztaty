package zapalki;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GuiManager {
    private static JTextArea output;

    public static void setUp(Items items) {
        JFrame frame = new JFrame("Match Factory");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        JTextField input = new JTextField();
        output = new JTextArea();

        CommandParser commandParser = new CommandParser();
        input.addActionListener(event -> {
            String commandString = input.getText();
            input.setText("");

            print(commandString);

            List<Command> commands = commandParser.parseConsoleInput(commandString);
            commands.forEach(cmd -> cmd.execute(items));

            printGameStatus(items);
        });

        frame.getContentPane().add(BorderLayout.NORTH, input);
        frame.getContentPane().add(BorderLayout.CENTER, output);
        frame.setVisible(true);
    }

    public static void print(String message) {
        output.setText(message + "\n" + output.getText());
    }

    public static void printGameStatus(Items items) {
        print(items.getBoxes().toString());
        print(items.getMatches().toString());
        print(items.getResources().toString());
    }
}
