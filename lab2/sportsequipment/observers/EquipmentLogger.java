package javalabs.lab2.sportsequipment.observers;

import javalabs.lab2.sportsequipment.SportEquipment;

public class EquipmentLogger implements EquipmentObserver {

    @Override
    public void update(SportEquipment equipment) {
        System.out.println(
                "[LOG] Змінено стан інвентарю \"" +
                equipment.getName() +
                "\". Поточний знос: " +
                equipment.getWearPercent() + "%"
        );
    }
}