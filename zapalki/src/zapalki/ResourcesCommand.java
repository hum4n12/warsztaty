package zapalki;

import java.util.Arrays;

public class ResourcesCommand implements Command {
    public enum OperationType {
        ADD,
        SUB
    }

    private Resources delta;

    public static Parser getParser() {
        return new Parser() {
            @Override
            public boolean isParsingPossible(String[] cmdParts) {
                boolean isOperationTypeValid = Arrays.stream(OperationType.values())
                        .anyMatch(type -> cmdParts[0].toUpperCase().equals(type.name()));
                return isOperationTypeValid && cmdParts.length == 3;
            }

            @Override
            public Command parse(String[] cmdParts) {
                String operationString = cmdParts[0];
                String resourceType = cmdParts[1];
                int resourceAmount;
                try {
                    resourceAmount = Integer.parseInt(cmdParts[2]);
                } catch (NumberFormatException nfe) {
                    GuiManager.print("ERROR: '" + cmdParts[2] + "' is not a number");
                    return null;
                }
                OperationType operation = OperationType.valueOf(operationString.toUpperCase());
                if (operation == OperationType.SUB) {
                    resourceAmount = -resourceAmount;
                }

                ResourcesCommand command = new ResourcesCommand();
                command.delta = Resources.fromString(resourceType, resourceAmount);
                return command;
            }
        };
    }

    @Override
    public void execute(Items currentResources) {
        currentResources.getResources().add(delta);
    }
}