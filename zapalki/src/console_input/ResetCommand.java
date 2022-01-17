package console_input;

import gui.GuiManager;

public class ResetCommand implements Command {
    private static final String NAME = "reset";

    private Items reset() {
        return new Items();
    }

    ResetCommand() {

    }

    public static boolean isParsingPossible(String[] cmdParts) {
        return (cmdParts[0].equals(ResetCommand.NAME) && cmdParts.length == 1);
    }

    public static Command parse(String[] cmdParts) {
        return new ResetCommand();
    }

    @Override
    public void execute(Items data) {
        GuiManager.print("RESET STATE");
        data.reset();
    }
}
