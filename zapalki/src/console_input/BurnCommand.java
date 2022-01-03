package console_input;

import resources.Cost;
import zapalki.Match;
import zapalki.MatchBox;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class BurnCommand implements Command{
    private static final String NAME = "burn";
    private final MatchBox matchBox;
    private final Match match;
    private final int amount;


    public BurnCommand(Object burnTarget,int amount) {
        if(burnTarget instanceof MatchBox){
            this.matchBox = (MatchBox)burnTarget;
            this.match = null;
        }
        else {
            this.matchBox = null;
            this.match = (Match)burnTarget;
        }
        this.amount = amount;
    }

    //burn <itemType[match,box]> <item subtype[small,large] if type is 'box'> <amount> - to watch it burn"
    public static boolean isParsingPossible(String[] cmdParts) {
        return (cmdParts[0].equals(BurnCommand.NAME) && (cmdParts.length == 3 || cmdParts.length == 4));
    }

    public static Command parse(String[] cmdParts) {
        String burnObject = cmdParts[1].toUpperCase();
        Match match = null;
        MatchBox matchBox = null;
        int lastCommandPart = 2;

        if(!(burnObject.equals("MATCH") || burnObject.equals("BOX"))){
            System.out.println("ERROR: '" + cmdParts[1] + "' is not a match or box");
            return null;
        } else if(burnObject.equals("BOX")){
            lastCommandPart = 3;
            String size = cmdParts[2].toUpperCase();

            if(size.equals("SMALL") || size.equals("LARGE")){
                burnObject = size + "_MATCH" + burnObject;
            } else {
                System.out.println("ERROR: '" + cmdParts[2] + "' is not a valid size");
                return null;
            }
        }

        String finalBurnObject = burnObject;
        Optional<Cost> boxOpt = Arrays.stream(Cost.values())
                .filter(item -> item.name().equals(finalBurnObject))
                .findAny();


        if(!(boxOpt.isPresent() && (boxOpt.get().getEffect().get() instanceof MatchBox) || (boxOpt.get().getEffect().get() instanceof Match))){
            System.out.println("ERROR: '" + burnObject + "' is not a match or matchbox");
            return null;
        }

        int amount;
        try {
            amount = Integer.parseInt(cmdParts[lastCommandPart]);
        } catch (NumberFormatException nfe) {
            System.out.println("ERROR: '" + cmdParts[lastCommandPart] + "' is not a number");
            return null;
        }

        return new BurnCommand(boxOpt.get().getEffect().get(),amount);
    }

    @Override
    public void execute(Items data) {
        if(this.match != null){
            if(data.getMatches().size() == 0){
                System.out.println("ERROR: there are no matches");
                return;
            }

            if(this.amount > data.getMatches().size()){
                System.out.println("ERROR: there are not enough matches");
                return;
            }

            for(int i = 0; i < this.amount; i++){
                Match match = data.getMatches().get(new Random().nextInt(data.getMatches().size()));
                match.putOnFire();
                data.getMatches().remove(match);
            }
        }
        else{
            if(data.getBoxes().size() == 0){
                System.out.println("ERROR: there are no boxes");
                return;
            }
            List<MatchBox> availableBoxes;
            //getting list of the boxes with remaining space
            availableBoxes = data.getBoxes().stream()
                    .filter(box -> (box.getClass().equals(this.matchBox.getClass())))
                    .filter(box -> box.getRemainingSpace() < box.getBoxSize())
                    .collect(Collectors.toList())
            ;

            if(this.amount > availableBoxes.size()){
                System.out.println("ERROR: there are not enough matchboxes");
                return;
            }

            for(int i = 0; i < this.amount; i++){
                MatchBox box = availableBoxes.get(new Random().nextInt(availableBoxes.size()));
                box.burnAll();
                availableBoxes.remove(box);
            }
        }
    }
}
