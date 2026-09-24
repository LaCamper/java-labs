package javalabs.lab2.sportsequipment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Облік спортивного інвентарю (Лабораторна 2) ===");
            System.out.print("Введіть кількість унікального інвентарю для додавання: ");
            int n = scanner.nextInt();
            scanner.nextLine(); 

            SportEquipment[] equipmentArray = new SportEquipment[n];

            // Заповнення масиву даними
            for (int i = 0; i < n; i++) {
                System.out.println("\nВведення даних для предмета #" + (i + 1));
                
                System.out.print("Назва інвентарю: ");
                String name = scanner.nextLine();
                
                System.out.print("Категорія: ");
                String category = scanner.nextLine();
                
                System.out.print("Кількість одиниць: ");
                int quantity = scanner.nextInt();
                
                System.out.print("Ціна за одну одиницю: ");
                double pricePerUnit = scanner.nextDouble();
                
                System.out.print("Відсоток зносу: ");
                double wearPercent = scanner.nextDouble();
                scanner.nextLine(); // Очищення буфера

                equipmentArray[i] = new SportEquipment(name, category, quantity, pricePerUnit, wearPercent);
            }

            // Виведення масиву (for-each)
            System.out.println("\n=== Уся база інвентарю ===");
            for (SportEquipment eq : equipmentArray) {
                System.out.println(eq.toString());
            }

            // Підсумок: підрахунок інвентарю, що потребує ремонту
            int repairCount = 0;
            for (SportEquipment eq : equipmentArray) {
                if (eq.getCondition().equals("Потребує ремонту або заміни")) {
                    repairCount++;
                }
            }
            System.out.println("\nКількість позицій, що потребують ремонту або заміни: " + repairCount);

            // Сортування (Bubble sort за відсотком зносу)
            System.out.println("\n=== Сортування за зростанням відсотка зносу ===");
            for (int i = 0; i < equipmentArray.length - 1; i++) {
                for (int j = 0; j < equipmentArray.length - 1 - i; j++) {
                    if (equipmentArray[j].getWearPercent() > equipmentArray[j + 1].getWearPercent()) {
                        SportEquipment temp = equipmentArray[j];
                        equipmentArray[j] = equipmentArray[j + 1];
                        equipmentArray[j + 1] = temp;
                    }
                }
            }

            for (SportEquipment eq : equipmentArray) {
                System.out.println(eq.toString());
            }

            // Лінійний пошук точного збігу
            System.out.println("\n=== Пошук конкретного об'єкта ===");
            System.out.println("Введіть точні дані для пошуку:");
            System.out.print("Назва: ");
            String searchName = scanner.nextLine();
            System.out.print("Категорія: ");
            String searchCategory = scanner.nextLine();
            System.out.print("Кількість: ");
            int searchQuantity = scanner.nextInt();
            System.out.print("Ціна: ");
            double searchPrice = scanner.nextDouble();
            System.out.print("Знос: ");
            double searchWear = scanner.nextDouble();

            SportEquipment target = new SportEquipment(searchName, searchCategory, searchQuantity, searchPrice, searchWear);
            boolean found = false;
            
            for (int i = 0; i < equipmentArray.length; i++) {
                if (equipmentArray[i].equals(target)) {
                    System.out.println("Знайдено під індексом [" + i + "]: " + equipmentArray[i].toString());
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                System.out.println("Такого об'єкта в базі немає.");
            }
        }
    }
}