package zapalki;

import java.util.ArrayList;
import java.util.List;

public class Items {
    private Resources resources = new Resources(0, 0, 0);
    private List<MatchBox> boxes = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();
    private List<Worker> workers = new ArrayList<>();

    public Resources getResources() {
        return resources;
    }

    public List<MatchBox> getBoxes() {
        return boxes;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void reset() {
        this.resources = new Resources(0,0,0);
        this.boxes = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public List<Worker> getWorkers() {
        return workers;
    }
}