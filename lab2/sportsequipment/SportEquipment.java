package javalabs.lab2.sportsequipment;

import java.util.Objects;

public class SportEquipment {

    private static final int GOOD_CONDITION_MAX_WEAR = 20;
    private static final int SATISFACTORY_CONDITION_MAX_WEAR = 50;

    private final String name;
    private final String category;
    private final int quantity;
    private final double pricePerUnit;
    private final double wearPercent;

    public SportEquipment(String name, String category, int quantity, double pricePerUnit, double wearPercent) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.wearPercent = wearPercent;
    }

    // Метод для обчислення поточної вартості з урахуванням зносу
    public double getCurrentValue() {
        double totalValue = quantity * pricePerUnit;
        return totalValue * (1 - wearPercent / 100);
    }

    // Метод для визначення стану
    public String getCondition() {
        if (wearPercent <= GOOD_CONDITION_MAX_WEAR) {
            return "Добрий стан";
        } else if (wearPercent <= SATISFACTORY_CONDITION_MAX_WEAR) {
            return "Задовільний стан";
        } else {
            return "Потребує ремонту або заміни";
        }
    }

    // Рівень 2: поле для доступу при сортуванні
    public double getWearPercent() {
        return wearPercent;
    }

    @Override
    public String toString() {
        return String.format("Інвентар: %s [%s] | К-ть: %d шт. | Ціна: %.2f | Знос: %.2f%% | Стан: %s | Вартість: %.2f грн",
                name, category, quantity, pricePerUnit, wearPercent, getCondition(), getCurrentValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        SportEquipment that = (SportEquipment) obj;
        
        return quantity == that.quantity &&
               Double.compare(that.pricePerUnit, pricePerUnit) == 0 &&
               Double.compare(that.wearPercent, wearPercent) == 0 &&
               Objects.equals(name, that.name) &&
               Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category, quantity, pricePerUnit, wearPercent);
    }
}