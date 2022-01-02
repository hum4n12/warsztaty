package console_input;

import resources.Cost;
import zapalki.Match;
import zapalki.MatchBox;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class PutCommand implements Command{
    private static final String NAME = "put";
    private final MatchBox type;
    private final int amount;


    public PutCommand(MatchBox type,int amount) {
        this.type = type;
        this.amount = amount;
    }

    //put <box type[small,large]> <amount> - to put random matches to a box of given size
    public static boolean isParsingPossible(String[] cmdParts) {
        return (cmdParts[0].equals(PutCommand.NAME) && cmdParts.length == 3);
    }

    public static Command parse(String[] cmdParts) {
        String boxType = cmdParts[1].toUpperCase();
        MatchBox type;
        Cost getMatchBoxType; //cost containes matchbox objects
        int amount;
        try {
            amount = Integer.parseInt(cmdParts[2]);
        } catch (NumberFormatException nfe) {
            System.out.println("ERROR: '" + cmdParts[2] + "' is not a number");
            return null;
        }
        Optional<Cost> boxOpt = Arrays.stream(Cost.values())
                .filter(item -> item.name().equals(boxType))
                .findAny();

        if(!(boxOpt.isPresent() && boxOpt.get().getEffect().get() instanceof MatchBox)){
            System.out.println("ERROR: '" + boxType + "' is not a matchbox");
            return null;
        }
        type  = (MatchBox)boxOpt.get().getEffect().get();
        return new PutCommand(type,amount);
    }

    @Override
    public void execute(Items data) {
        if(data.getBoxes().size() <= 0){
            System.out.println("ERROR: There are no matchboxes");
            return;
        }

        if(data.getMatches().size() <= 0){
            System.out.println("ERROR: There are no matches");
            return;
        }
        MatchBox box = data.getBoxes().get(new Random().nextInt(data.getBoxes().size()));

        for(int i = 0; i < this.amount; i++){
            Match match = data.getMatches().get(new Random().nextInt(data.getMatches().size()));
            box.addMatch(match);
        }

        box.printContent();
    }
}
