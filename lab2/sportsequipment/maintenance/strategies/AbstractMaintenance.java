package javalabs.lab2.sportsequipment.maintenance.strategies;

import javalabs.lab2.sportsequipment.SportEquipment;
import javalabs.lab2.sportsequipment.maintenance.interfaces.MaintenanceStrategy;
import javalabs.lab2.sportsequipment.maintenance.interfaces.MaintenanceReport;

public abstract class AbstractMaintenance
        implements MaintenanceStrategy, MaintenanceReport {

    protected double getEquipmentValue(SportEquipment equipment) {
        if (equipment == null) {
            throw new IllegalArgumentException(
                    "Спортивний інвентар не може бути null"
            );
        }

        return equipment.getTotalPrice();
    }

    @Override
    public void printReport(SportEquipment equipment) {
        double cost = calculateCost(equipment);

        System.out.println(getDescription());
        System.out.printf(
                "Вартість обслуговування: %.2f грн%n",
                cost
        );
    }
}