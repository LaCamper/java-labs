package javalabs.lab2.sportsequipment.maintenance.adapter;

public class LegacyRepairService {

    public double estimateRepairPrice(
            String itemName,
            int damageLevel,
            double itemPrice) {

        // Умовна модель ціноутворення зовнішнього сервісу.
        return itemPrice * (0.10 + damageLevel / 200.0);
    }
}