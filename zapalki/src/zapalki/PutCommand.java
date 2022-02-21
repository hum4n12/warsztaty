package zapalki;

import zapalki.Match;

import java.util.*;
import java.util.stream.Collectors;

public class PutCommand implements Command {
    private static final String NAME = "put";
    private final MatchBox type;
    private final int amount;


    public PutCommand(MatchBox type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    //put <box type[small,large]> <amount> - to put random matches to a box of given size
    public static Parser getParser() {
        return new Parser() {
            @Override
            public boolean isParsingPossible(String[] cmdParts) {
                return (cmdParts[0].equals(PutCommand.NAME) && cmdParts.length == 3);
            }

            @Override
            public Command parse(String[] cmdParts) {
                String boxType = cmdParts[1].toUpperCase();
                MatchBox type;
                Cost getMatchBoxType; //cost containes matchbox objects
                int amount;
                try {
                    amount = Integer.parseInt(cmdParts[2]);
                } catch (NumberFormatException nfe) {
                    GuiManager.print("ERROR: '" + cmdParts[2] + "' is not a number");
                    return null;
                }
                Optional<Cost> boxOpt = Arrays.stream(Cost.values())
                        .filter(item -> item.name().equals(boxType))
                        .findAny();

                if (!(boxOpt.isPresent() && boxOpt.get().getEffect().get() instanceof MatchBox)) {
                    GuiManager.print("ERROR: '" + boxType + "' is not a matchbox");
                    return null;
                }
                type = (MatchBox) boxOpt.get().getEffect().get();
                return new PutCommand(type, amount);
            }
        };
    }


    @Override
    public void execute(Items data) {
        List<MatchBox> availableBoxes;

        //getting list of the boxes with remaining space
        availableBoxes = data.getBoxes().stream()
                .filter(box -> (box.getClass().equals(this.type.getClass())))
                .filter(box -> box.getRemainingSpace() > 0)
                .collect(Collectors.toList())
        ;

        //counting space
        int space = availableBoxes.stream()
                .mapToInt(MatchBox::getRemainingSpace)
                .sum();

        if (this.amount > data.getMatches().size()) {
            GuiManager.print("ERROR: There are not enough matches");
            return;
        }


        if (this.amount > space) {
            GuiManager.print("ERROR: There are not enough space in matchboxes");
            return;
        }

        if (data.getBoxes().size() <= 0) {
            GuiManager.print("ERROR: There are no matchboxes");
            return;
        }

        if (data.getMatches().size() <= 0) {
            GuiManager.print("ERROR: There are no matches");
            return;
        }


        MatchBox box = availableBoxes.get(new Random().nextInt(availableBoxes.size()));
        for (int i = 0; i < this.amount; i++) {
            Match match = data.getMatches().get(new Random().nextInt(data.getMatches().size()));
            box.addMatch(match);
            data.getMatches().remove(match);

            if (box.getRemainingSpace() == 0) {
                availableBoxes.remove(box);
                if (availableBoxes.size() != 0)
                    box = availableBoxes.get(new Random().nextInt(availableBoxes.size()));
            }
        }
    }
}
