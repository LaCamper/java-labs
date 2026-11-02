
package javalabs.lab2.sportsequipment;

import javalabs.lab2.sportsequipment.exceptions.InvalidPriceException;
import javalabs.lab2.sportsequipment.exceptions.InvalidWearPercentException;

public abstract sealed class BallEquipment
        extends SportEquipment
        permits Football, Basketball {

    private final double diameter;

    // Основний конструктор
    protected BallEquipment(
            String name,
            String category,
            int quantity,
            double pricePerUnit,
            double wearPercent,
            double diameter)
            throws InvalidPriceException, InvalidWearPercentException {

        super(name, category, quantity, pricePerUnit, wearPercent);

        if (diameter <= 0 || !Double.isFinite(diameter)) {
            throw new IllegalArgumentException(
                    "Діаметр м'яча повинен бути додатним скінченним числом"
            );
        }

        this.diameter = diameter;

        System.out.println("Конструктор BallEquipment");
    }

    // Перевантажений конструктор
    protected BallEquipment(
            String name,
            String category,
            int quantity,
            double pricePerUnit,
            double wearPercent)
            throws InvalidPriceException, InvalidWearPercentException {

        this(name, category, quantity, pricePerUnit,
                wearPercent, 22.0);
    }

    public final double getDiameter() {
        return diameter;
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(
                        " | Діаметр: %.2f см",
                        diameter
                );
    }
}
