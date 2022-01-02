package console_input;

import resources.Cost;
import resources.Resources;
import zapalki.Match;
import zapalki.MatchBox;

import java.util.Arrays;
import java.util.Optional;

public class BuyCommand implements Command{
    private static final String NAME = "buy";
    private final Cost buyItem;
    private final int amount;

    public BuyCommand(Cost buyItem, int amount) {
        this.buyItem = buyItem;
        this.amount = amount;
    }
    //"buy <itemName> <amount> - to buy items"

    public static boolean isParsingPossible(String[] cmdParts) {
        return (cmdParts[0].equals(BuyCommand.NAME) && cmdParts.length == 3);
    }

    public static Command parse(String[] cmdParts) {
        String itemName = cmdParts[1].toUpperCase();
        Cost itemCost;
        int amount;
        try {
            amount = Integer.parseInt(cmdParts[2]);
        } catch (NumberFormatException nfe) {
            System.out.println("ERROR: '" + cmdParts[2] + "' is not a number");
            return null;
        }
        Optional<Cost> itemOpt = Arrays.stream(Cost.values())
                .filter(item -> item.name().equals(itemName))
                .findAny();

        if(itemOpt.isPresent()) {
            itemCost = itemOpt.get();
        } else {
            System.out.println("ERROR: '" + cmdParts[1] + "' item does not exist");
            return null;
        }

        return new BuyCommand(itemCost,amount);
    }

    @Override
    public void execute(Items data) {
        Resources multipliedResources = Resources.multiply(this.buyItem.getCost(),this.amount);
        if(!data.getResources().isEnough(multipliedResources)){
            System.out.println("ERROR: not enough resources");
            return;
        }

        data.getResources().subtract(multipliedResources);

        for(int i = 0; i < this.amount; i++){
            switch (buyItem){
                case MATCH -> data.getMatches().add((Match)buyItem.getEffect().get());
                case LARGE_MATCHBOX,SMALL_MATCHBOX -> data.getBoxes().add((MatchBox)buyItem.getEffect().get());
            }
        }

    }
}