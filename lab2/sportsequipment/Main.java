package javalabs.lab2.sportsequipment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть кількість спортивного спорядження для додавання: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Очищення буфера після nextInt()

        SportEquipment[] equipmentArray = new SportEquipment[n];

        // РІВЕНЬ 1: Заповнення масиву об'єктів з клавіатури (цикл for)
        for (int i = 0; i < n; i++) {
            System.out.println("\nВведення даних для предмета #" + (i + 1));
            
            System.out.print("Назва (наприклад, Гантеля): ");
            String name = scanner.nextLine();
            
            System.out.print("Категорія (наприклад, Фітнес): ");
            String category = scanner.nextLine();
            
            System.out.print("Ціна (через кому, наприклад 450,50): ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Очищення буфера після nextDouble()

            equipmentArray[i] = new SportEquipment(name, category, price);
        }

        // РІВЕНЬ 1: Виведення масиву (цикл for-each)
        System.out.println("\n--- Усі товари в базі ---");
        for (SportEquipment eq : equipmentArray) {
            System.out.println(eq.toString());
        }

        // РІВЕНЬ 1: Підсумок за умовою
        System.out.print("\nВведіть максимальну ціну для підрахунку дешевших товарів: ");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine();
        
        int cheapCount = 0;
        for (SportEquipment eq : equipmentArray) {
            if (eq.getPrice() < maxPrice) {
                cheapCount++;
            }
        }
        System.out.println("Кількість товарів, дешевших за " + maxPrice + " грн: " + cheapCount);

        // РІВЕНЬ 2: Сортування масиву (Bubble sort за ціною)
        System.out.println("\n--- Сортування масиву за зростанням ціни (Bubble Sort) ---");
        for (int i = 0; i < equipmentArray.length - 1; i++) {
            for (int j = 0; j < equipmentArray.length - 1 - i; j++) {
                if (equipmentArray[j].getPrice() > equipmentArray[j + 1].getPrice()) {
                    // Обмін елементів
                    SportEquipment temp = equipmentArray[j];
                    equipmentArray[j] = equipmentArray[j + 1];
                    equipmentArray[j + 1] = temp;
                }
            }
        }

        // Виведення після сортування
        for (SportEquipment eq : equipmentArray) {
            System.out.println(eq.toString());
        }

        // РІВЕНЬ 3: Лінійний пошук об'єкта
        System.out.println("\n--- Пошук конкретного об'єкта ---");
        System.out.println("Введіть дані об'єкта, який хочете знайти:");
        
        System.out.print("Назва: ");
        String searchName = scanner.nextLine();
        
        System.out.print("Категорія: ");
        String searchCategory = scanner.nextLine();
        
        System.out.print("Ціна: ");
        double searchPrice = scanner.nextDouble();

        // Створюємо об'єкт-зразок для пошуку
        SportEquipment target = new SportEquipment(searchName, searchCategory, searchPrice);
        boolean found = false;
        
        for (int i = 0; i < equipmentArray.length; i++) {
            // Використовуємо перевизначений equals() для порівняння всього об'єкта
            if (equipmentArray[i].equals(target)) {
                System.out.println("✅ Об'єкт знайдено під індексом [" + i + "]: " + equipmentArray[i].toString());
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("❌ Такого об'єкта в масиві немає.");
        }

        scanner.close();
    }
}