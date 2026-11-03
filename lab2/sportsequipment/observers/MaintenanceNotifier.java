package javalabs.lab2.sportsequipment.observers;

import javalabs.lab2.sportsequipment.SportEquipment;

public class MaintenanceNotifier implements EquipmentObserver {

    private static final double MAINTENANCE_THRESHOLD = 50.0;

    @Override
    public void update(SportEquipment equipment) {
        if (equipment.getWearPercent() > MAINTENANCE_THRESHOLD) {
            System.out.println(
                    "[WARNING] Інвентар \"" +
                    equipment.getName() +
                    "\" потребує обслуговування!"
            );
        }
    }
}