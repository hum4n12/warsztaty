package resources;

public class Resources {
    private int wood;
    private int sulfur;
    private int paper;

    public Resources(int wood, int sulfur, int paper) {
        this.wood = wood;
        this.sulfur = sulfur;
        this.paper = paper;
    }

    public static Resources fromString(String type, int amount) {
        switch (type) {
        case "wood": return new Resources(amount, 0, 0);
        case "sulfur": return new Resources(0, amount, 0);
        case "paper": return new Resources(0, 0, amount);
        default:
            System.out.println("Invalid resource type");
            return null;
        }
    }

    public Object buy(Cost cost) {
        this.subtract(cost.cost);
        return cost.effect.get();
    }

    public void add(Resources amount) {
        wood += amount.wood;
        sulfur += amount.sulfur;
        paper += amount.paper;
    }

    public void subtract(Resources amount) {
        wood -= amount.wood;
        sulfur -= amount.sulfur;
        paper -= amount.paper;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "wood=" + wood +
                ", sulfur=" + sulfur +
                ", paper=" + paper +
                '}';
    }
}