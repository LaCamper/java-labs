package javalabs.lab2.sportsequipment;

import java.util.Objects;
import javalabs.lab2.sportsequipment.exceptions.InvalidPriceException;
import javalabs.lab2.sportsequipment.exceptions.InvalidWearPercentException;

public final class Racket extends SportEquipment {

    // Натяг струн у кілограмах
    private final double stringTension;

    // Основний конструктор
    public Racket(
            String name,
            int quantity,
            double pricePerUnit,
            double wearPercent,
            double stringTension)
            throws InvalidPriceException, InvalidWearPercentException {

        super(
                name,
                "Тенісна ракетка",
                quantity,
                pricePerUnit,
                wearPercent
        );

        if (!Double.isFinite(stringTension)
                || stringTension <= 0) {
            throw new IllegalArgumentException(
                    "Натяг струн має бути додатним скінченним числом"
            );
        }

        this.stringTension = stringTension;

        System.out.println("Конструктор Racket");
    }

    // Перевантажений конструктор
    public Racket(
            String name,
            int quantity,
            double pricePerUnit,
            double wearPercent)
            throws InvalidPriceException, InvalidWearPercentException {

        this(name, quantity, pricePerUnit, wearPercent, 24.0);
    }

    public double getStringTension() {
        return stringTension;
    }

    @Override
    public String getSportType() {
        return "Теніс";
    }

    @Override
    public String getCondition() {
        String condition = super.getCondition();

        if (getWearPercent() > 50) {
            return condition + " (рекомендовано перевірити струни)";
        }

        return condition;
    }

    // Специфічний метод, якого немає в SportEquipment
    public boolean needsRestringing() {
        return getWearPercent() > 50;
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(
                        " | Натяг струн: %.2f кг",
                        stringTension
                )
                + " | Вид спорту: " + getSportType();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Racket other)) {
            return false;
        }

        if (!super.equals(obj)) {
            return false;
        }

        return Double.compare(
                stringTension,
                other.stringTension
        ) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                stringTension
        );
    }
}
