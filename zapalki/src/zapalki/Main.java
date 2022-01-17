package zapalki;


public class Main {
    private static final Items items = new Items();

    public static void main(String[] args) {
        GuiManager.setUp(items);
        GuiManager.print("Usage:\n"
                + "add <resourceName> <resourceAmount> - to add resources"
                + "\nsub <resourceName> <resourceAmount> - to subtract resources"
                + "\nbuy <itemName> <amount> - to buy items"
                + "\nburn <itemType[match,box]> <item subtype[small,large] if type is 'box'> <amount> - to watch it burn"
                + "\nput <box type[small,large]> <amount> - to put random matches to a box of given size"
                + "\nreset - to reset state of program");
    }
}
