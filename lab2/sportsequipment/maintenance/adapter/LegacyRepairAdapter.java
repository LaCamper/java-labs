package javalabs.lab2.sportsequipment.maintenance.adapter;

import javalabs.lab2.sportsequipment.SportEquipment;
import javalabs.lab2.sportsequipment.maintenance.interfaces.MaintenanceStrategy;

public class LegacyRepairAdapter implements MaintenanceStrategy {

    private final LegacyRepairService legacyService;

    public LegacyRepairAdapter(LegacyRepairService legacyService) {
        this.legacyService = legacyService;
    }

    @Override
    public double calculateCost(SportEquipment equipment) {
        return legacyService.estimateRepairPrice(
                equipment.getName(),
                (int) equipment.getWearPercent(),
                equipment.getPricePerUnit()
        );
    }

    @Override
    public String getDescription() {
        return "Обслуговування через зовнішній сервіс ремонту";
    }
}