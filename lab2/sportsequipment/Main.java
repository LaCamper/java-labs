
package javalabs.lab2.sportsequipment;

import java.util.InputMismatchException;
import java.util.Scanner;
import javalabs.lab2.sportsequipment.demo.BasicEquipmentDemo;
import javalabs.lab2.sportsequipment.demo.CrudDemo;
import javalabs.lab2.sportsequipment.demo.InheritanceDemo;
import javalabs.lab2.sportsequipment.demo.MaintenanceDemo;
import javalabs.lab2.sportsequipment.exceptions.EquipmentException;
import javalabs.lab2.sportsequipment.exceptions.InvalidPriceException;
import javalabs.lab2.sportsequipment.exceptions.InvalidWearPercentException;

public class Main {

    public static void main(String[] args) {

       try (Scanner scanner = new Scanner(System.in)) {

            SportEquipment[] equipment = null;

            boolean running = true;

            while (running) {

                printMenu();

                System.out.print("Оберіть пункт: ");
                String choice = scanner.next();
                scanner.nextLine();

                try {
                    switch (choice) {

                        case "1" -> {
                            equipment = BasicEquipmentDemo.run(scanner);
                        }

                        case "2" -> {
                            if (equipment == null || equipment.length == 0) {
                                System.out.println(
                                    "Спочатку створіть інвентар через пункт 1!"
                                );
                            } else {
                                MaintenanceDemo.run(equipment, scanner);
                            }
                        }

                        case "3" -> {
                            InheritanceDemo.run();
                        }

                        case "4" -> {
                            CrudDemo.run(scanner);
                        }

                        case "5" -> {
                            equipment = BasicEquipmentDemo.run(scanner);

                            MaintenanceDemo.run(equipment, scanner);

                            InheritanceDemo.run();

                            CrudDemo.run(scanner);
                        }

                        case "0" -> {
                            running = false;
                            System.out.println("Вихід із програми...");
                        }

                        default -> {
                            System.out.println(
                                "Невідомий пункт меню! Спробуйте ще раз."
                            );
                        }
                    }

                } catch (InputMismatchException e) {
                    System.out.println(
                        "Помилка: потрібно вводити числа у відповідні поля."
                    );
                    scanner.nextLine();

                } catch (InvalidPriceException e) {
                    System.out.println(
                        "Помилка ціни: " + e.getMessage()
                    );
                    System.out.println(
                        "Некоректне значення: " + e.getInvalidValue()
                    );

                } catch (InvalidWearPercentException e) {
                    System.out.println(
                        "Помилка зносу: " + e.getMessage()
                    );
                    System.out.println(
                        "Некоректне значення: " + e.getInvalidValue()
                    );

                } catch (EquipmentException e) {
                    System.out.println(
                        "Помилка інвентарю: " + e.getMessage()
                    );
                    System.out.println(
                        "Некоректне значення: " + e.getInvalidValue()
                    );
                }
            }

        } finally {
            System.out.println("\nРоботу програми завершено.");
        }
    }

    private static void printMenu() {

        System.out.println("\n========== ГОЛОВНЕ МЕНЮ ==========");
        System.out.println("1. ЛР1-3: Введення, пошук, сортування");
        System.out.println("2. ЛР4-5: Обслуговування та патерни");
        System.out.println("3. ЛР6: Наслідування та поліморфізм");
        System.out.println("4. ЛР7-9: CRUD та Generics");
        System.out.println("5. Запустити всі демонстрації");
        System.out.println("0. Вихід");
        System.out.println("==================================");
    }
}
