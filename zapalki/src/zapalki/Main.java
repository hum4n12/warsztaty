package zapalki;
import java.lang.Math;

public class Main {
    private static final LargeMatchBox large = new LargeMatchBox();
    private static final SmallMatchBox small = new SmallMatchBox();
    private static int a = 0;
    private static int b = 1;


    public static void main(String[] args) {
        //small match box class is not necessarry
        addMatches(large,5);
        addMatches(small,2);

        large.turnColorToColor("red","green");
        large.burnAll();
        //testowy komentarz
    }

    private static void addMatches(MatchBox box,int amount){
        for(int i = 0; i  < amount; i++) {
            int num = (int) (Math.random() * 10) % 4;
            Match m = switch (num) {
                case 0 -> MatchFactory.createMatch("red");
                case 1 -> MatchFactory.createMatch("green");
                case 2 -> MatchFactory.createMatch("blue");
                case 3 -> MatchFactory.createMatch("yellow");
                default -> null;
            };
            // dodawanie do pudełka zapałek

            box.addMatch(m);
        }
    }
}
