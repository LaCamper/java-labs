package javalabs.lab2.sportsequipment;

import java.util.Objects;

import javalabs.lab2.sportsequipment.exceptions.InvalidPriceException;
import javalabs.lab2.sportsequipment.exceptions.InvalidWearPercentException;

public class SportEquipment {

    private static final int GOOD_CONDITION_MAX_WEAR = 20;
    private static final int SATISFACTORY_CONDITION_MAX_WEAR = 50;

    private String name;
    private String category;
    private int quantity;
    private double pricePerUnit;
    private double wearPercent;

    public SportEquipment(String name, String category, int quantity, double pricePerUnit, double wearPercent) throws InvalidPriceException, InvalidWearPercentException {
        if (pricePerUnit < 0) {
            throw new InvalidPriceException(pricePerUnit);
        }
        if (wearPercent < 0 || wearPercent > 100) {
            throw new InvalidWearPercentException(wearPercent);
        }
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.wearPercent = wearPercent;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPricePerUnit(double pricePerUnit)
            throws InvalidPriceException {

        if (pricePerUnit < 0) {
            throw new InvalidPriceException(pricePerUnit);
        }

        this.pricePerUnit = pricePerUnit;
    }

    public void setWearPercent(double wearPercent)
            throws InvalidWearPercentException {

        if (wearPercent < 0 || wearPercent > 100) {
            throw new InvalidWearPercentException(wearPercent);
        }

        this.wearPercent = wearPercent;
    }

    public double getTotalPrice() {
        return quantity * pricePerUnit;
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

    public double getCurrentValue() {
    return getTotalPrice() * (1 - wearPercent / 100.0);
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