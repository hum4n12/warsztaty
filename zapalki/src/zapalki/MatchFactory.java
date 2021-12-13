package zapalki;

public class MatchFactory {
    public static Match createMatch(Colors color){
        Match val = null;

        switch (color) {
            case RED -> val = new RedMatch();
            case BLUE -> val = new BlueMatch();
            case GREEN -> val = new GreenMatch();
            default -> {
                System.out.println("Cannot create a match of given color");
            }
        }

        return val;
    }
}
