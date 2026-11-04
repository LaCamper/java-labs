package javalabs.lab2.sportsequipment.demo;

import java.util.InputMismatchException;
import java.util.Scanner;
import javalabs.lab2.sportsequipment.*;
import javalabs.lab2.sportsequipment.exceptions.EquipmentException;

public final class BasicEquipmentDemo {

    private BasicEquipmentDemo() {
    }

    public static SportEquipment[] run(Scanner scanner) throws EquipmentException {
        System.out.println("=== СПОРТИВНИЙ ІНВЕНТАР ===");
        System.out.print("Введіть кількість об'єктів інвентарю: ");
        int count = scanner.nextInt();
        scanner.nextLine();
        if (count < 0) {
            throw new IllegalArgumentException("Кількість не може бути від’ємною");
        }
        SportEquipment[] equipment = new SportEquipment[count];
        for (int i = 0; i < equipment.length; i++) {
            System.out.println("\n=== Введення інвентарю №" + (i + 1) + " ===");
            equipment[i] = readEquipment(scanner);
        }

        System.out.println("\n=== ВВЕДЕНИЙ ІНВЕНТАР ===");
        printEquipment(equipment);
        System.out.println("\n=== СОРТУВАННЯ ЗА ЦІНОЮ ===");
        bubbleSortByPrice(equipment);
        printEquipment(equipment);

        System.out.println("\n=== ЛІНІЙНИЙ ПОШУК ===");
        System.out.print("Введіть назву інвентарю для пошуку: ");
        String searchName = scanner.nextLine();
        int foundIndex = linearSearchByName(equipment, searchName);
        if (foundIndex != -1) {
            System.out.println("Інвентар знайдено:");
            System.out.println(equipment[foundIndex]);
        } else {
            System.out.println("Інвентар з назвою \"" + searchName + "\" не знайдено.");
        }

        System.out.println("\n=== ДОСТУП ДО ЕЛЕМЕНТА МАСИВУ ===");
        demonstrateArrayException(scanner, equipment);
        return equipment;
    }

    private static SportEquipment readEquipment(
            Scanner scanner)
            throws EquipmentException {

        System.out.print("Назва або модель інвентарю: ");
        String name = scanner.nextLine();

        String category;

        while (true) {
            System.out.println("Доступні категорії:");
            System.out.println("1. Футбольний м'яч");
            System.out.println("2. Баскетбольний м'яч");
            System.out.println("3. Тенісна ракетка");
            System.out.print("Оберіть категорію (1-3): ");

            category = scanner.nextLine().trim();

            switch (category) {
                case "1" ->
                    category = "футбол";
                case "2" ->
                    category = "баскетбол";
                case "3" ->
                    category = "теніс";
                default -> {
                    System.out.println(
                            "Невірна категорія! Введіть число від 1 до 3.\n"
                    );
                    continue;
                }
            }

            break;
        }

        System.out.print("Кількість: ");
        int quantity = scanner.nextInt();

        System.out.print("Ціна за одиницю: ");
        double price = scanner.nextDouble();

        System.out.print("Відсоток зносу: ");
        double wearPercent = scanner.nextDouble();

        scanner.nextLine();

        try {

            return switch (category) {
                case "футбол" ->
                    new Football(name, quantity, price, wearPercent);

                case "баскетбол" ->
                    new Basketball(name, quantity, price, wearPercent);

                case "теніс" ->
                    new Racket(name, quantity, price, wearPercent);

                default ->
                    throw new IllegalArgumentException(
                            "Невідома категорія: " + category
                    );
            };

        } catch (EquipmentException e) {

            System.out.println(
                    "Лог: не вдалося створити об'єкт SportEquipment."
            );

            System.out.println(
                    "Причина: " + e.getMessage()
            );

            throw e;
        }
    }

    private static void printEquipment(
            SportEquipment[] equipment) {

        for (int i = 0; i < equipment.length; i++) {

            System.out.println(
                    (i + 1) + ". " + equipment[i]
            );
        }
    }

    private static void bubbleSortByPrice(
            SportEquipment[] equipment) {

        for (int i = 0; i < equipment.length - 1; i++) {

            for (int j = 0;
                    j < equipment.length - 1 - i;
                    j++) {

                if (equipment[j].getPricePerUnit()
                        > equipment[j + 1].getPricePerUnit()) {

                    SportEquipment temp
                            = equipment[j];

                    equipment[j]
                            = equipment[j + 1];

                    equipment[j + 1]
                            = temp;
                }
            }
        }
    }

    private static int linearSearchByName(
            SportEquipment[] equipment,
            String searchName) {

        for (int i = 0; i < equipment.length; i++) {

            if (equipment[i]
                    .getName()
                    .equalsIgnoreCase(searchName)) {

                return i;
            }
        }

        return -1;
    }

    private static void demonstrateArrayException(
            Scanner scanner,
            SportEquipment[] equipment) {

        try {

            System.out.print(
                    "Введіть номер інвентарю для перегляду: "
            );

            int number = scanner.nextInt();

            System.out.println(
                    "Вибраний інвентар:"
            );

            System.out.println(
                    equipment[number - 1]
            );

        } catch (InputMismatchException e) {

            System.out.println(
                    "Помилка: потрібно ввести ціле число."
            );

            scanner.nextLine();

        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println(
                    "Помилка: інвентарю з таким номером не існує."
            );
        }
    }
}
