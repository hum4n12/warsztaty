package zapalki;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MatchFactory {
    public static final Map<Colors,Supplier<Match>> mapa = new HashMap<Colors,Supplier<Match>>(){{
        put(Colors.RED,RedMatch::new);
        put(Colors.BLUE,BlueMatch::new);
        put(Colors.GREEN,GreenMatch::new);
        put(Colors.YELLOW,YellowMatch::new);
    }};

    public static Match createMatch(Colors color){
        Match val = null;
        Supplier<Match> match;

        match = mapa.get(color);
        if(match != null){
            val = match.get();
        } else{
            System.out.println("That color does not exist");
        }

        return val;
    }

    public static Object createMatchBox(MatchBox.MatchBoxType type) {
        switch (type) {
        case LARGE: return new LargeMatchBox();
        case SMALL: return new SmallMatchBox();
        default:
            System.out.println("Invalid MatchBox type");
            return null;
        }
    }
}
