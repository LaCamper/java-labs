package javalabs.lab3.sportsequipment;

import javalabs.lab3.sportsequipment.exceptions.*;

public class Equipment {
    private String name;
    private int availableCount;
    private int wearPercentage; // Відсоток зносу (0-100)

    // Збудження винятку при неправильних даних (throw new...)
    public Equipment(String name, int availableCount, int wearPercentage) throws InvalidConditionException {
        if (wearPercentage < 0 || wearPercentage > 100) {
            throw new InvalidConditionException("Відсоток зносу має бути від 0 до 100!", wearPercentage);
        }
        this.name = name;
        this.availableCount = availableCount;
        this.wearPercentage = wearPercentage;
    }

    // Метод для видачі інвентарю (оренда/використання)
    public void rentOut(int count) throws EquipmentUnavailableException {
        if (count > availableCount) {
            throw new EquipmentUnavailableException("Недостатньо інвентарю на складі!", count, availableCount);
        }
        availableCount -= count;
    }

    public String getName() { return name; }
    public int getAvailableCount() { return availableCount; }
}
