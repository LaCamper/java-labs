package javalabs.lab2.sportsequipment;

import java.util.InputMismatchException;
import java.util.Scanner;
import javalabs.lab2.sportsequipment.exceptions.EquipmentException;
import javalabs.lab2.sportsequipment.exceptions.InvalidPriceException;
import javalabs.lab2.sportsequipment.exceptions.InvalidWearPercentException;
import javalabs.lab2.sportsequipment.maintenance.MaintenanceService;
import javalabs.lab2.sportsequipment.maintenance.interfaces.MaintenanceStrategy;
import javalabs.lab2.sportsequipment.maintenance.strategies.AbstractMaintenance;
import javalabs.lab2.sportsequipment.maintenance.strategies.BasicMaintenance;
import javalabs.lab2.sportsequipment.maintenance.strategies.FullMaintenance;

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

        System.out.print("Категорія: ");
        String category = scanner.nextLine();

        System.out.print("Кількість: ");
        int quantity = scanner.nextInt();

        System.out.print("Ціна за одиницю: ");
        double price = scanner.nextDouble();

        System.out.print("Відсоток зносу: ");
        double wearPercent = scanner.nextDouble();

        scanner.nextLine();

        try {

            return new SportEquipment(
                    name,
                    category,
                    quantity,
                    price,
                    wearPercent
            );

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
            new BasicMaintenance(),
            new FullMaintenance()
        };

        for (MaintenanceStrategy strategy : strategies) {
            System.out.println(strategy.getDescription());

            double cost = strategy.calculateCost(equipment);

            System.out.printf("Вартість: %.2f грн%n", cost);
        }

        System.out.println("\n=== АБСТРАКТНИЙ КЛАС ===");

        AbstractMaintenance maintenance = new BasicMaintenance();
        maintenance.printReport(equipment);

        System.out.println("\n=== ПАТЕРН STRATEGY ===");

        MaintenanceService service
                = new MaintenanceService(new BasicMaintenance());

        service.printDescription();
        System.out.printf(
                "Вартість: %.2f грн%n",
                service.calculate(equipment)
        );

        service.setStrategy(new FullMaintenance());

        System.out.println("Стратегію змінено!");

        service.printDescription();
        System.out.printf(
                "Вартість: %.2f грн%n",
                service.calculate(equipment)
        );
    }
}
