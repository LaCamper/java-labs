package javalabs.lab2.sportsequipment.maintenance.factory;

import javalabs.lab2.sportsequipment.maintenance.adapter.LegacyRepairAdapter;
import javalabs.lab2.sportsequipment.maintenance.adapter.LegacyRepairService;
import javalabs.lab2.sportsequipment.maintenance.interfaces.MaintenanceStrategy;
import javalabs.lab2.sportsequipment.maintenance.strategies.BasicMaintenance;
import javalabs.lab2.sportsequipment.maintenance.strategies.FullMaintenance;

public class MaintenanceStrategyFactory {

    public static MaintenanceStrategy create(int type) {
        return switch (type) {
            case 1 -> new BasicMaintenance();
            case 2 -> new FullMaintenance();
            case 3 -> new LegacyRepairAdapter(
                    new LegacyRepairService()
            );
            default -> throw new IllegalArgumentException(
                    "Невідомий тип обслуговування: " + type
            );
        };
    }
}