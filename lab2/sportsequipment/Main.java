package javalabs.lab2.sportsequipment;

import java.util.InputMismatchException;
import java.util.Scanner;
import javalabs.lab2.sportsequipment.exceptions.EquipmentException;
import javalabs.lab2.sportsequipment.exceptions.InvalidPriceException;
import javalabs.lab2.sportsequipment.exceptions.InvalidWearPercentException;
import javalabs.lab2.sportsequipment.maintenance.MaintenanceService;
import javalabs.lab2.sportsequipment.maintenance.factory.MaintenanceStrategyFactory;
import javalabs.lab2.sportsequipment.maintenance.interfaces.MaintenanceStrategy;
import javalabs.lab2.sportsequipment.maintenance.strategies.AbstractMaintenance;
import javalabs.lab2.sportsequipment.observers.EquipmentLogger;
import javalabs.lab2.sportsequipment.observers.MaintenanceNotifier;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("=== СПОРТИВНИЙ ІНВЕНТАР ===");

            System.out.print("Введіть кількість об'єктів інвентарю: ");
            int count = scanner.nextInt();
            scanner.nextLine();

            SportEquipment[] equipment = new SportEquipment[count];

            for (int i = 0; i < equipment.length; i++) {

                System.out.println();
                System.out.println(
                        "=== Введення інвентарю №" + (i + 1) + " ==="
                );

                equipment[i] = readEquipment(scanner);
            }

            System.out.println();
            System.out.println("=== ВВЕДЕНИЙ ІНВЕНТАР ===");

            printEquipment(equipment);

            System.out.println();
            System.out.println("=== СОРТУВАННЯ ЗА ЦІНОЮ ===");

            bubbleSortByPrice(equipment);

            printEquipment(equipment);

            System.out.println();
            System.out.println("=== ЛІНІЙНИЙ ПОШУК ===");

            System.out.print(
                    "Введіть назву інвентарю для пошуку: "
            );

            String searchName = scanner.nextLine();

            int foundIndex = linearSearchByName(
                    equipment,
                    searchName
            );

            if (foundIndex != -1) {

                System.out.println(
                        "Інвентар знайдено:"
                );

                System.out.println(
                        equipment[foundIndex]
                );

            } else {

                System.out.println(
                        "Інвентар з назвою \""
                        + searchName
                        + "\" не знайдено."
                );
            }

            System.out.println();
            System.out.println(
                    "=== ДОСТУП ДО ЕЛЕМЕНТА МАСИВУ ==="
            );

            demonstrateArrayException(
                    scanner,
                    equipment
            );
            System.out.println();
            System.out.println("=== ОБСЛУГОВУВАННЯ ІНВЕНТАРЮ ===");

            for (SportEquipment item : equipment) {
                System.out.println();
                System.out.println("Інвентар: " + item.getName());

                demonstrateMaintenance(item);
            }
            System.out.println();
            System.out.println("=== ЛР5: ПАТЕРН OBSERVER ===");

            for (SportEquipment item : equipment) {
                demonstrateObserver(item, scanner);
            }

            System.out.println();
            System.out.println("=== ЛР6: НАСЛІДУВАННЯ ТА ПОЛІМОРФІЗМ ===");

            demonstrateInheritance();
        } catch (InputMismatchException e) {

            System.out.println(
                    "Помилка: введено неправильний тип даних."
            );

            System.out.println(
                    "Для кількості, ціни та зносу потрібно вводити числа."
            );

        } catch (InvalidPriceException e) {

            System.out.println(
                    "Помилка ціни: "
                    + e.getMessage()
            );

            System.out.println(
                    "Некоректне значення: "
                    + e.getInvalidValue()
            );

        } catch (InvalidWearPercentException e) {

            System.out.println(
                    "Помилка зносу: "
                    + e.getMessage()
            );

            System.out.println(
                    "Некоректне значення: "
                    + e.getInvalidValue()
            );

        } catch (EquipmentException e) {

            System.out.println(
                    "Помилка інвентарю: " + e.getMessage()
            );

            System.out.println(
                    "Некоректне значення: "
                    + e.getInvalidValue()
            );

        } finally {

            scanner.close();

            System.out.println();
            System.out.println(
                    "Роботу програми завершено."
            );
        }
    }

    private static SportEquipment readEquipment(
            Scanner scanner)
            throws EquipmentException {

        System.out.print("Назва: ");
        String name = scanner.nextLine();

        String category;

        while (true) {
            System.out.println("Доступні категорії:");
            System.out.println("1. Футбол");
            System.out.println("2. Баскетбол");
            System.out.println("3. Теніс");
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

    private static void demonstrateMaintenance(SportEquipment equipment) {

        System.out.println("\n=== ЛР4: ІНТЕРФЕЙСИ ТА ПОЛІМОРФІЗМ ===");

        MaintenanceStrategy[] strategies = {
            MaintenanceStrategyFactory.create(1),
            MaintenanceStrategyFactory.create(2)
        };

        for (MaintenanceStrategy strategy : strategies) {
            System.out.println(strategy.getDescription());

            double cost = strategy.calculateCost(equipment);

            System.out.printf("Вартість: %.2f грн%n", cost);
        }

        System.out.println("\n=== АБСТРАКТНИЙ КЛАС ===");

        AbstractMaintenance maintenance
                = (AbstractMaintenance) MaintenanceStrategyFactory.create(1);
        maintenance.printReport(equipment);

        System.out.println("\n=== ПАТЕРН STRATEGY ===");

        MaintenanceService service
                = new MaintenanceService(MaintenanceStrategyFactory.create(1));

        service.printDescription();
        System.out.printf(
                "Вартість: %.2f грн%n",
                service.calculate(equipment)
        );

        service.setStrategy(MaintenanceStrategyFactory.create(2));

        System.out.println("Стратегію змінено!");

        service.printDescription();
        System.out.printf(
                "Вартість: %.2f грн%n",
                service.calculate(equipment)
        );
        System.out.println("\n=== ЛР5: ПАТЕРН ADAPTER ===");

        service.setStrategy(MaintenanceStrategyFactory.create(3));

        service.printDescription();

        System.out.printf(
                "Вартість через зовнішній сервіс: %.2f грн%n",
                service.calculate(equipment)
        );
    }

    private static void demonstrateObserver(
            SportEquipment equipment,
            Scanner scanner) throws EquipmentException {

        System.out.println();
        System.out.println("Інвентар: " + equipment.getName());

        equipment.addObserver(new EquipmentLogger());
        equipment.addObserver(new MaintenanceNotifier());

        System.out.println(
                "Поточний знос: " + equipment.getWearPercent() + "%"
        );

        System.out.print("Введіть новий відсоток зносу: ");
        double newWear = scanner.nextDouble();

        equipment.setWearPercent(newWear);
    }

    private static void demonstrateInheritance()
            throws EquipmentException {

        System.out.println("\n=== ЛАНЦЮЖОК КОНСТРУКТОРІВ ===");

        SportEquipment football = new Football(
                "Adidas", 5, 1200, 15, 22.0, "Трава"
        );

        SportEquipment basketball = new Basketball(
                "Spalding", 3, 950, 65, 24.0, true
        );

        SportEquipment racket = new Racket(
                "Wilson", 2, 3500, 65, 24.0
        );

        System.out.println("\n=== ПОЛІМОРФІЗМ ===");

        SportEquipment[] items = {
            football,
            basketball,
            racket
        };

        for (SportEquipment item : items) {
            System.out.println("\n" + item);
            System.out.println("Вид спорту: " + item.getSportType());
            System.out.println("Стан: " + item.getCondition());
        }

        System.out.println("\n=== INSTANCEOF PATTERN MATCHING ===");

        for (SportEquipment item : items) {
            if (item instanceof Racket tennisRacket) {
                System.out.println(
                        tennisRacket.getName()
                        + " | Потрібна перетяжка струн: "
                        + tennisRacket.needsRestringing()
                );
            }
        }

        System.out.println("\n=== SEALED SWITCH PATTERN MATCHING ===");

        for (SportEquipment item : items) {
            String description = switch (item) {
                case Football f ->
                    "Футбольний м'яч для покриття: "
                    + f.getSurfaceType();

                case Basketball b ->
                    "Баскетбольний м'яч | Indoor: "
                    + b.isIndoor();

                case Racket r ->
                    "Тенісна ракетка | Натяг струн: "
                    + r.getStringTension() + " кг";
            };

            System.out.println(description);
        }

        System.out.println("\n=== EQUALS ТА HASHCODE ===");

        Football sameFootball = new Football(
                "Adidas", 5, 1200, 15, 22.0, "Трава"
        );

        Football differentFootball = new Football(
                "Adidas", 5, 1200, 15, 23.0, "Трава"
        );

        System.out.println(
                "football.equals(sameFootball): "
                + football.equals(sameFootball)
        );

        System.out.println(
                "sameFootball.equals(football): "
                + sameFootball.equals(football)
        );

        System.out.println(
                "football.equals(differentFootball): "
                + football.equals(differentFootball)
        );

        System.out.println(
                "football.equals(basketball): "
                + football.equals(basketball)
        );

        System.out.println(
                "basketball.equals(football): "
                + basketball.equals(football)
        );

        System.out.println("\n=== HASHSET ===");

        java.util.Set<SportEquipment> uniqueEquipment
                = new java.util.HashSet<>();

        uniqueEquipment.add(football);
        uniqueEquipment.add(sameFootball);
        uniqueEquipment.add(differentFootball);

        System.out.println(
                "Кількість унікальних об'єктів: "
                + uniqueEquipment.size()
        );

        System.out.println("\n=== КОМПОЗИЦІЯ ===");

        MaintenanceService service = new MaintenanceService(
                MaintenanceStrategyFactory.create(1)
        );

        System.out.printf(
                "Вартість обслуговування %s: %.2f грн%n",
                football.getName(),
                service.calculate(football)
        );
    }

}
