package javalabs.lab2.sportsequipment.exceptions;

public class InvalidWearPercentException extends EquipmentException {

    public InvalidWearPercentException(double invalidWearPercent) {
        super(
            "Відсоток зносу повинен бути в межах від 0 до 100.",
            invalidWearPercent
        );
    }
}