package javalabs.lab3.sportsequipment.exceptions;

public class InvalidConditionException extends SportsEquipmentException {
    private final int invalidWearPercentage;

    public InvalidConditionException(String message, int wearPercentage) {
        super(message);
        this.invalidWearPercentage = wearPercentage;
    }

    public int getInvalidWearPercentage() {
        return invalidWearPercentage;
    }
}
