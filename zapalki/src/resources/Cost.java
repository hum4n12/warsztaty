package resources;

import zapalki.Colors;
import zapalki.MatchBox;
import zapalki.MatchFactory;

import java.util.Random;
import java.util.function.Supplier;

public enum Cost {
    MATCH(new Resources(1,1,0), () -> {
        int colorIndex = new Random().nextInt(Colors.values().length);
        Colors color = Colors.values()[colorIndex];
        return MatchFactory.createMatch(color);
    }),
    LARGE_MATCHBOX(new Resources(0,0,3), () -> MatchFactory.createMatchBox(MatchBox.MatchBoxType.LARGE)),
    SMALL_MATCHBOX(new Resources(0,0,1), () -> MatchFactory.createMatchBox(MatchBox.MatchBoxType.SMALL)),
    ;

    Resources cost;
    Supplier<?> effect;
    Cost(Resources cost, Supplier<?> effect) {
        this.cost = cost;
        this.effect = effect;
    }
}
