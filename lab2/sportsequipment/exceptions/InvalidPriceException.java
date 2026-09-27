package javalabs.lab2.sportsequipment.exceptions;

public class InvalidPriceException extends EquipmentException {

    public InvalidPriceException(double invalidPrice) {
        super("Ціна інвентарю не може бути від'ємною.", invalidPrice);
    }
}