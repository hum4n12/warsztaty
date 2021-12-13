package zapalki;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MatchFactory {
    public static final Map<Colors,Supplier<Match>> mapa = new HashMap<>(){{
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
}
