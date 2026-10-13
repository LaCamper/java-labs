package javalabs.lab2.sportsequipment.maintenance.strategies;

import javalabs.lab2.sportsequipment.SportEquipment;

public class BasicMaintenance extends AbstractMaintenance {

    private static final double BASIC_RATE = 0.05;

    @Override
    public double calculateCost(SportEquipment equipment) {
        return getEquipmentValue(equipment) * BASIC_RATE;
    }

    @Override
    public String getDescription() {
        return "Базове обслуговування (5%)";
    }
}