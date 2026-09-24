package javalabs.lab3.sportsequipment.exceptions;

// Рівень 2: Клас винятку, коли намагаються взяти більше інвентарю, ніж є
public class EquipmentUnavailableException extends SportsEquipmentException {
    private final int requestedAmount;
    private final int availableAmount;

    public EquipmentUnavailableException(String message, int requested, int available) {
        super(message);
        this.requestedAmount = requested;
        this.availableAmount = available;
    }

    public int getRequestedAmount() { return requestedAmount; }
    public int getAvailableAmount() { return availableAmount; }
}