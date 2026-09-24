package javalabs.lab2.sportsequipment;

import java.util.Objects;

public class SportEquipment {
    private String name;
    private String category;
    private double price;

    public SportEquipment(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    // Рівень 1: Перевизначення toString() для зручного виведення
    @Override
    public String toString() {
        return String.format("Спорядження: %s | Категорія: %s | Ціна: %.2f грн", name, category, price);
    }

    // Рівень 3: Перевизначення equals() для лінійного пошуку за цілим об'єктом
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        SportEquipment that = (SportEquipment) obj;
        
        // Порівнюємо ціну (через Double.compare для уникнення проблем з плаваючою точкою) та рядки
        return Double.compare(that.price, price) == 0 &&
               Objects.equals(name, that.name) &&
               Objects.equals(category, that.category);
    }
}