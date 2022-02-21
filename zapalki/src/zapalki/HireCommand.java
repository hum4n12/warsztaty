package zapalki;

public class HireCommand implements Command {

    public static Parser getParser() {
        return new Parser() {

            @Override
            public boolean isParsingPossible(String[] cmdParts) {
                return false;
            }

            @Override
            public Command parse(String[] cmdParts) {
                return null;
            }
        };
    }

    @Override
    public void execute(Items data) {

    }
}
