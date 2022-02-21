package zapalki;

public interface Parser {
    boolean isParsingPossible(String[] cmdParts);
    Command parse(String[] cmdParts);
}