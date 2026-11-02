package javalabs.lab2.sportsequipment;

import java.util.Objects;
import javalabs.lab2.sportsequipment.exceptions.InvalidPriceException;
import javalabs.lab2.sportsequipment.exceptions.InvalidWearPercentException;

public final class Football extends BallEquipment {

    private final String surfaceType;

    // Основний конструктор
    public Football(
            String name,
            int quantity,
            double pricePerUnit,
            double wearPercent,
            double diameter,
            String surfaceType)
            throws InvalidPriceException, InvalidWearPercentException {

        super(
                name,
                "Футбольний м'яч",
                quantity,
                pricePerUnit,
                wearPercent,
                diameter
        );

        this.surfaceType = Objects.requireNonNull(
                surfaceType,
                "Тип покриття не може бути null"
        );

        System.out.println("Конструктор Football");
    }

    // Перевантажений конструктор
    public Football(
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
                22.0,
                "Трава"
        );
    }

    public String getSurfaceType() {
        return surfaceType;
    }

    // Реалізація абстрактного методу
    @Override
    public String getSportType() {
        return "Футбол";
    }

    // Специфічна поведінка футбольного м'яча
    @Override
    public String getCondition() {
        String condition = super.getCondition();

        if (getWearPercent() > 70) {
            return condition + " (не рекомендований для матчу)";
        }

        return condition;
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Покриття: " + surfaceType
                + " | Вид спорту: " + getSportType();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Football other)) {
            return false;
        }

        if (!super.equals(obj)) {
            return false;
        }

        return Double.compare(
                getDiameter(),
                other.getDiameter()
        ) == 0
                && Objects.equals(
                        surfaceType,
                        other.surfaceType
                );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                getDiameter(),
                surfaceType
        );
    }
}
