package javalabs.lab2.sportsequipment.maintenance.strategies;

import javalabs.lab2.sportsequipment.SportEquipment;

public class FullMaintenance extends AbstractMaintenance {

    private static final double BASE_RATE = 0.10;
    private static final double WEAR_RATE = 0.001;

    @Override
    public double calculateCost(SportEquipment equipment) {
        double value = getEquipmentValue(equipment);
        double wearPercent = equipment.getWearPercent();

        double rate = BASE_RATE + wearPercent * WEAR_RATE;

        return value * rate;
    }

    @Override
    public String getDescription() {
        return "Повне обслуговування з урахуванням зносу";
    }
}