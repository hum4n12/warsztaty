package console_input;

import resources.Resources;
import zapalki.Match;
import zapalki.MatchBox;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private final Resources resources = new Resources(0,0,0);
    private final List<MatchBox> boxes = new ArrayList<>();
    private final List<Match> matches = new ArrayList<>();

    public Resources getResources() {
        return resources;
    }

    public List<MatchBox> getBoxes() {
        return boxes;
    }

    public List<Match> getMatches() {
        return matches;
    }

}