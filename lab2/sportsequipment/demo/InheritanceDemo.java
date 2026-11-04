package javalabs.lab2.sportsequipment.demo;

import javalabs.lab2.sportsequipment.*;
import javalabs.lab2.sportsequipment.exceptions.EquipmentException;
import javalabs.lab2.sportsequipment.maintenance.MaintenanceService;
import javalabs.lab2.sportsequipment.maintenance.factory.MaintenanceStrategyFactory;

public final class InheritanceDemo {

    private InheritanceDemo() {
    }

    public static void run()
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
    }}
