package zapalki;

public class MatchFactory {
    public static Match createMatch(String color){
        Match val = null;

        switch (color) {
            case "red" -> val = new RedMatch();
            case "blue" -> val = new BlueMatch();
            case "green" -> val = new GreenMatch();
            case "yellow" -> val = new YellowMatch();
            default -> {
                System.out.println("Cannot create a match of given color");
            }
        }

        return val;
    }
}
