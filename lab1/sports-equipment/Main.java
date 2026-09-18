import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Облік спортивного інвентарю ===");

        System.out.print("Введіть назву інвентарю: ");
        String name = scanner.nextLine();

        System.out.print("Введіть категорію: ");
        String category = scanner.nextLine();

        System.out.print("Введіть кількість одиниць: ");
        int quantity = scanner.nextInt();

        System.out.print("Введіть ціну за одну одиницю: ");
        double pricePerUnit = scanner.nextDouble();

        System.out.print("Введіть відсоток зносу: ");
        double wearPercent = scanner.nextDouble();

        double totalValue = quantity * pricePerUnit;

        double currentValue =
                totalValue * (1 - wearPercent / 100);

        String condition;

        if (wearPercent <= 20) {
            condition = "Добрий стан";
        } else if (wearPercent <= 50) {
            condition = "Задовільний стан";
        } else {
            condition = "Потребує ремонту або заміни";
        }

        System.out.println();
        System.out.println("=== Інформація про інвентар ===");

        System.out.printf("Назва: %s%n", name);
        System.out.printf("Категорія: %s%n", category);
        System.out.printf("Кількість: %d шт.%n", quantity);
        System.out.printf("Ціна за одиницю: %.2f грн%n", pricePerUnit);
        System.out.printf("Знос: %.2f%%%n", wearPercent);
        System.out.printf("Загальна вартість: %.2f грн%n", totalValue);
        System.out.printf("Поточна вартість: %.2f грн%n", currentValue);
        System.out.printf("Стан: %s%n", condition);

        scanner.close();
    }
}