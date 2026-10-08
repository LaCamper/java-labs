package javalabs.lab2.sportsequipment.maintenance;

import java.util.Objects;
import javalabs.lab2.sportsequipment.SportEquipment;
import javalabs.lab2.sportsequipment.maintenance.interfaces.MaintenanceStrategy;

public class MaintenanceService {

    private MaintenanceStrategy strategy;

    public MaintenanceService(MaintenanceStrategy strategy) {
    this.strategy = Objects.requireNonNull(
            strategy,
            "Стратегія не може бути null"
    );
}

    public void setStrategy(MaintenanceStrategy strategy) {
        this.strategy = Objects.requireNonNull(
                strategy,
                "Стратегія не може бути null"
        );
    }

    public double calculate(SportEquipment equipment) {
        return strategy.calculateCost(equipment);
    }

    public void printDescription() {
        System.out.println(strategy.getDescription());
    }
}