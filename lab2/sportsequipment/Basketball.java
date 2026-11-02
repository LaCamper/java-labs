package javalabs.lab2.sportsequipment;

import java.util.Objects;
import javalabs.lab2.sportsequipment.exceptions.InvalidPriceException;
import javalabs.lab2.sportsequipment.exceptions.InvalidWearPercentException;

public final class Basketball extends BallEquipment {

    private final boolean indoor;

    // Основний конструктор
    public Basketball(
            String name,
            int quantity,
            double pricePerUnit,
            double wearPercent,
            double diameter,
            boolean indoor)
            throws InvalidPriceException, InvalidWearPercentException {

        super(
                name,
                "Баскетбольний м'яч",
                quantity,
                pricePerUnit,
                wearPercent,
                diameter
        );

        this.indoor = indoor;

        System.out.println("Конструктор Basketball");
    }

    // Перевантажений конструктор
    public Basketball(
            String name,
            int quantity,
            double pricePerUnit,
            double wearPercent)
            throws InvalidPriceException, InvalidWearPercentException {

        this(
                name,
                quantity,
                pricePerUnit,
                wearPercent,
                24.0,
                true
        );
    }

    public boolean isIndoor() {
        return indoor;
    }

    @Override
    public String getSportType() {
        return "Баскетбол";
    }

    // Специфічна оцінка стану
    @Override
    public String getCondition() {
        String condition = super.getCondition();

        if (getWearPercent() > 60) {
            return condition + " (потрібна перевірка зчеплення)";
        }

        return condition;
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Використання: "
                + (indoor ? "У приміщенні" : "На вулиці")
                + " | Вид спорту: " + getSportType();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Basketball other)) {
            return false;
        }

        if (!super.equals(obj)) {
            return false;
        }

        return Double.compare(
                getDiameter(),
                other.getDiameter()
        ) == 0
                && indoor == other.indoor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                getDiameter(),
                indoor
        );
    }
}
