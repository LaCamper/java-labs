package javalabs.lab2.sportsequipment.maintenance.interfaces;

import javalabs.lab2.sportsequipment.SportEquipment;

public interface MaintenanceStrategy {

    double calculateCost(SportEquipment equipment);

    default String getDescription() {
        return "Обслуговування спортивного інвентарю";
    }
}