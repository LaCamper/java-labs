package javalabs.lab2.sportsequipment.demo;

import java.util.Scanner;
import javalabs.lab2.sportsequipment.SportEquipment;
import javalabs.lab2.sportsequipment.exceptions.EquipmentException;
import javalabs.lab2.sportsequipment.maintenance.MaintenanceService;
import javalabs.lab2.sportsequipment.maintenance.factory.MaintenanceStrategyFactory;
import javalabs.lab2.sportsequipment.maintenance.interfaces.MaintenanceStrategy;
import javalabs.lab2.sportsequipment.maintenance.strategies.AbstractMaintenance;
import javalabs.lab2.sportsequipment.observers.EquipmentLogger;
import javalabs.lab2.sportsequipment.observers.MaintenanceNotifier;

public final class MaintenanceDemo {

    private MaintenanceDemo() {
    }

    public static void run(SportEquipment[] equipment, Scanner scanner)
            throws EquipmentException {
        System.out.println("\n=== ОБСЛУГОВУВАННЯ ІНВЕНТАРЮ ===");
        for (SportEquipment item : equipment) {
            System.out.println("\nІнвентар: " + item.getName());
            demonstrateMaintenance(item);
        }
        System.out.println("\n=== ЛР5: ПАТЕРН OBSERVER ===");
        for (SportEquipment item : equipment) {
            demonstrateObserver(item, scanner);
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
}
