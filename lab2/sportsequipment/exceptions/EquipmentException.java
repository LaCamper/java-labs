package javalabs.lab2.sportsequipment.exceptions;

public class EquipmentException extends Exception {
    private final double invalidValue;

    public EquipmentException(String message, double invalidValue) {
        super(message);
        this.invalidValue = invalidValue;
    }

    public double getInvalidValue() {
        return invalidValue;
    }
}