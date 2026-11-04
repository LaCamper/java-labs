package javalabs.lab2.sportsequipment.demo;

import java.util.List;
import java.util.Scanner;
import javalabs.lab2.sportsequipment.Basketball;
import javalabs.lab2.sportsequipment.Football;
import javalabs.lab2.sportsequipment.Racket;
import javalabs.lab2.sportsequipment.SportEquipment;
import javalabs.lab2.sportsequipment.crud.EntityPrinter;
import javalabs.lab2.sportsequipment.crud.InMemoryCrudService;
import javalabs.lab2.sportsequipment.crud.SportEquipmentCrudService;
import javalabs.lab2.sportsequipment.exceptions.EntityNotFoundException;
import javalabs.lab2.sportsequipment.exceptions.EquipmentException;

public final class CrudDemo {

    private static final SportEquipmentCrudService service
            = new SportEquipmentCrudService();

    private CrudDemo() {
    }

    public static void run(Scanner scanner) {

        boolean running = true;

        while (running) {

            printMenu();

            String choice = readText(scanner, "Оберіть пункт: ");

            try {
                switch (choice) {

                    case "1" ->
                        create(scanner);
                    case "2" ->
                        find(scanner);
                    case "3" ->
                        update(scanner);
                    case "4" ->
                        delete(scanner);
                    case "5" ->
                        printAll();
                    case "6" ->
                        printSortedByName();
                    case "7" ->
                        printSortedByNameAndPrice();
                    case "8" ->
                        checkSortingDoesNotChangeStorage();
                    case "9" ->
                        demonstrateGenerics();

                    case "0" ->
                        running = false;

                    default ->
                        System.out.println(
                                "Такого пункту немає."
                        );
                }

            } catch (EntityNotFoundException e) {
                System.out.println(
                        "Помилка пошуку: " + e.getMessage()
                );

            } catch (EquipmentException | IllegalArgumentException e) {
                System.out.println(
                        "Помилка: " + e.getMessage()
                );
            }
        }
    }

    private static void printMenu() {

        System.out.println("\n========== ЛР7-9: CRUD ==========");
        System.out.println("Записів у сховищі: " + service.count());
        System.out.println("1. Створити інвентар (CREATE)");
        System.out.println("2. Знайти за ID (READ)");
        System.out.println("3. Оновити інвентар (UPDATE)");
        System.out.println("4. Видалити за ID (DELETE)");
        System.out.println("5. Показати всі записи");
        System.out.println("6. Сортувати за назвою");
        System.out.println("7. Сортувати за назвою, потім за ціною");
        System.out.println("8. Перевірити незмінність сховища");
        System.out.println("9. Продемонструвати Generics і wildcard");
        System.out.println("0. Повернутися у головне меню");
        System.out.println("=================================");
    }

    // CREATE
    private static void create(Scanner scanner)
            throws EquipmentException {

        System.out.println("\nТип інвентарю:");
        System.out.println("1. Футбольний м'яч");
        System.out.println("2. Баскетбольний м'яч");
        System.out.println("3. Тенісна ракетка");

        String type = readText(
                scanner, "Виберіть тип (1-3): "
        );

        if (!type.equals("1")
                && !type.equals("2")
                && !type.equals("3")) {

            System.out.println("Невідомий тип.");
            return;
        }

        String name = readNonEmptyText(
                scanner, "Назва або модель: "
        );

        int quantity = readNonNegativeInt(
                scanner, "Кількість: "
        );

        double price = readNonNegativeDouble(
                scanner, "Ціна за одиницю: "
        );

        double wear = readWear(
                scanner, "Знос (%): "
        );

        SportEquipment entity = switch (type) {

            case "1" ->
                new Football(
                name, quantity, price, wear
                );

            case "2" ->
                new Basketball(
                name, quantity, price, wear
                );

            case "3" ->
                new Racket(
                name, quantity, price, wear
                );

            default ->
                throw new IllegalStateException(
                        "Невідомий тип"
                );
        };

        service.save(entity);

        System.out.println(
                "Створено! ID = " + entity.getId()
        );

        System.out.println(entity);
    }

    // READ
    private static void find(Scanner scanner) {

        long id = readId(scanner);

        System.out.println(
                "Знайдено: " + service.findById(id)
        );
    }

    // UPDATE
    private static void update(Scanner scanner)
            throws EquipmentException {

        long id = readId(scanner);

        SportEquipment entity = service.findById(id);

        System.out.println("Зараз: " + entity);

        System.out.println("1. Назва");
        System.out.println("2. Категорія");
        System.out.println("3. Кількість");
        System.out.println("4. Ціна за одиницю");
        System.out.println("5. Відсоток зносу");

        String choice = readText(
                scanner, "Що змінити? "
        );

        int before = service.count();

        switch (choice) {

            case "1" ->
                entity.setName(
                        readNonEmptyText(scanner, "Нова назва: ")
                );

            case "2" ->
                entity.setCategory(
                        readNonEmptyText(scanner, "Нова категорія: ")
                );

            case "3" ->
                entity.setQuantity(
                        readNonNegativeInt(scanner, "Нова кількість: ")
                );

            case "4" ->
                entity.setPricePerUnit(
                        readNonNegativeDouble(scanner, "Нова ціна: ")
                );

            case "5" ->
                entity.setWearPercent(
                        readWear(scanner, "Новий знос (%): ")
                );

            default -> {
                System.out.println("Невідоме поле. Змін немає.");
                return;
            }
        }

        service.save(entity);

        System.out.println(
                "Оновлено: " + service.findById(id)
        );

        System.out.printf(
                "Кількість записів: %d -> %d%n",
                before,
                service.count()
        );
    }

    // DELETE
    private static void delete(Scanner scanner) {

        long id = readId(scanner);

        boolean deleted = service.delete(id);

        if (deleted) {
            System.out.println("Запис видалено.");
        } else {
            System.out.println(
                    "Запису з таким ID немає (false)."
            );
        }
    }

    // READ ALL
    private static void printAll() {

        System.out.println(
                "\n=== ЗАПИСИ У ПОРЯДКУ ДОДАВАННЯ ==="
        );

        List<SportEquipment> items = service.findAll();

        if (items.isEmpty()) {
            System.out.println("Сховище порожнє.");
            return;
        }

        for (SportEquipment item : items) {

            System.out.printf(
                    "ID: %d | %s | %.2f грн%n",
                    item.getId(),
                    item.getName(),
                    item.getPricePerUnit()
            );
        }
    }

    // SORT BY NAME
    private static void printSortedByName() {

        System.out.println("\n=== СОРТУВАННЯ ЗА НАЗВОЮ ===");

        EntityPrinter.printAll(
                service.findAllSortedByName()
        );
    }

    // SORT BY NAME AND PRICE
    private static void printSortedByNameAndPrice() {

        System.out.println(
                "\n=== СОРТУВАННЯ ЗА НАЗВОЮ ТА ЦІНОЮ ==="
        );

        for (SportEquipment item
                : service.findAllSortedByNameAndByPrice()) {

            System.out.printf(
                    "ID: %d | %s | %.2f грн%n",
                    item.getId(),
                    item.getName(),
                    item.getPricePerUnit()
            );
        }
    }

    // CHECK SORTING
    private static void checkSortingDoesNotChangeStorage() {

        List<SportEquipment> before
                = service.findAll();

        System.out.println("\nДо сортування:");
        EntityPrinter.printAll(before);

        System.out.println("Відсортований список:");

        EntityPrinter.printAll(
                service.findAllSortedByNameAndByPrice()
        );

        List<SportEquipment> after
                = service.findAll();

        System.out.println("Сховище після сортування:");
        EntityPrinter.printAll(after);

        System.out.println(
                "Порядок і склад не змінилися: "
                + before.equals(after)
        );
    }

    // GENERICS AND WILDCARD
    private static void demonstrateGenerics() {

        InMemoryCrudService<Football> footballService
                = new InMemoryCrudService<>();

        InMemoryCrudService<Racket> racketService
                = new InMemoryCrudService<>();

        for (SportEquipment item : service.findAll()) {

            switch (item) {

                case Football football ->
                    footballService.save(football);

                case Racket racket ->
                    racketService.save(racket);

                case Basketball basketball ->
                    System.out.println(
                            "Баскетбольний інвентар: "
                            + basketball.getName()
                    );
            }
        }

        System.out.println("Football-сервіс:");

        EntityPrinter.printAll(
                footballService.findAll()
        );

        System.out.println("Racket-сервіс:");

        EntityPrinter.printAll(
                racketService.findAll()
        );
    }

    // INPUT ID
    private static long readId(Scanner scanner) {

        while (true) {

            try {
                long id = Long.parseLong(
                        readText(scanner, "Введіть ID: ")
                );

                if (id > 0) {
                    return id;
                }

            } catch (NumberFormatException ignored) {
                // Повідомлення нижче
            }

            System.out.println(
                    "ID має бути додатним цілим числом."
            );
        }
    }

    // INPUT INTEGER
    private static int readNonNegativeInt(
            Scanner scanner,
            String prompt) {

        while (true) {

            try {
                int value = Integer.parseInt(
                        readText(scanner, prompt)
                );

                if (value >= 0) {
                    return value;
                }

            } catch (NumberFormatException ignored) {
                // Повідомлення нижче
            }

            System.out.println(
                    "Введіть невід'ємне ціле число."
            );
        }
    }

    // INPUT DOUBLE
    private static double readNonNegativeDouble(
            Scanner scanner,
            String prompt) {

        while (true) {

            try {
                double value = Double.parseDouble(
                        readText(scanner, prompt).replace(',', '.')
                );

                if (Double.isFinite(value) && value >= 0) {
                    return value;
                }

            } catch (NumberFormatException ignored) {
                // Повідомлення нижче
            }

            System.out.println(
                    "Введіть невід'ємне число."
            );
        }
    }

    // INPUT WEAR
    private static double readWear(
            Scanner scanner,
            String prompt) {

        while (true) {

            double value = readNonNegativeDouble(
                    scanner, prompt
            );

            if (value <= 100) {
                return value;
            }

            System.out.println(
                    "Знос має бути від 0 до 100%."
            );
        }
    }

    // INPUT NON-EMPTY STRING
    private static String readNonEmptyText(
            Scanner scanner,
            String prompt) {

        while (true) {

            String value = readText(scanner, prompt);

            if (!value.isBlank()) {
                return value;
            }

            System.out.println(
                    "Поле не може бути порожнім."
            );
        }
    }

    // INPUT TEXT
    private static String readText(
            Scanner scanner,
            String prompt) {

        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
