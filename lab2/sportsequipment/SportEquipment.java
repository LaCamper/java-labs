package javalabs.lab2.sportsequipment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javalabs.lab2.sportsequipment.exceptions.InvalidPriceException;
import javalabs.lab2.sportsequipment.exceptions.InvalidWearPercentException;
import javalabs.lab2.sportsequipment.observers.EquipmentObserver;

public abstract sealed class SportEquipment extends BaseEntity permits BallEquipment, Racket {

    private static final int GOOD_CONDITION_MAX_WEAR = 20;
    private static final int SATISFACTORY_CONDITION_MAX_WEAR = 50;

    private static final AtomicLong NEXT_ID =
    new AtomicLong(1);

    private String category;
    private int quantity;
    private double pricePerUnit;
    private final List<EquipmentObserver> observers = new ArrayList<>();
    private double wearPercent;

    protected SportEquipment(String name,String category, int quantity, double pricePerUnit, double wearPercent) throws InvalidPriceException, InvalidWearPercentException {
        super(NEXT_ID.getAndIncrement(), name);
        if (pricePerUnit < 0) {
            throw new InvalidPriceException(pricePerUnit);
        }
        if (wearPercent < 0 || wearPercent > 100) {
            throw new InvalidWearPercentException(wearPercent);
        }
        this.category = category;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.wearPercent = wearPercent;

        System.out.println("Конструктор SportEquipment");
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
        notifyObservers();
    }

    public final double getTotalPrice() {
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

    public abstract String getSportType();

    public void addObserver(EquipmentObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(EquipmentObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (EquipmentObserver observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public String toString() {
        return String.format("Інвентар: %s [%s] | К-ть: %d шт. | Ціна: %.2f | Знос: %.2f%% | Стан: %s | Вартість: %.2f грн",
                getName(), category, quantity, pricePerUnit, wearPercent, getCondition(), getCurrentValue());
    }

}
