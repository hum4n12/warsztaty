package zapalki;

public class ResetCommand implements Command {
    private static final String NAME = "reset";

    private Items reset() {
        return new Items();
    }

    ResetCommand() {

    }

    public static Parser getParser() {
        return new Parser() {

            @Override
            public boolean isParsingPossible(String[] cmdParts) {
                return (cmdParts[0].equals(ResetCommand.NAME) && cmdParts.length == 1);
            }

            @Override
            public Command parse(String[] cmdParts) {
                return new ResetCommand();
            }
        };
    }

    @Override
    public void execute(Items data) {
        GuiManager.print("RESET STATE");
        data.reset();
    }
}
