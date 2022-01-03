package console_input;

import resources.Resources;
import zapalki.Match;
import zapalki.MatchBox;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private final int wood = 0;
    private final int sulfur = 0;
    private final int paper = 0;

    private  Resources resources = new Resources(this.wood,this.sulfur,this.paper);
    private  List<MatchBox> boxes = new ArrayList<>();
    private  List<Match> matches = new ArrayList<>();

    public Resources getResources() {
        return resources;
    }

    public List<MatchBox> getBoxes() {
        return boxes;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void reset(){
        this.resources = new Resources(wood,sulfur,paper);
        this.boxes = new ArrayList<>();
        this.matches = new ArrayList<>();
    }
}