package javalabs.lab3.sportsequipment;

import java.util.InputMismatchException;
import java.util.Scanner;

import javalabs.lab3.sportsequipment.exceptions.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Ініціалізація бази інвентарю (може кинути InvalidConditionException, але ми впевнені в даних)
            Equipment[] inventory = {
                new Equipment("Футбольний м'яч", 15, 10),
                new Equipment("Тенісна ракетка", 5, 25)
            };

            // Рівень 1: Можливі InputMismatchException та ArrayIndexOutOfBoundsException
            System.out.println("Введіть номер інвентарю для видачі (0 - Футбольний м'яч, 1 - Ракетка):");
            int index = scanner.nextInt(); 
            
            Equipment selectedItem = inventory[index]; // Може кинути вихід за межі масиву

            System.out.println("Скільки одиниць потрібно видати?");
            int amount = scanner.nextInt(); // Може кинути помилку некоректного вводу

            // Виклик методу, який демонструє re-throw
            processRental(selectedItem, amount);

        } catch (InputMismatchException e) {
            // Рівень 1: Перехоплення стандартної помилки вводу
            System.err.println("Помилка вводу: Будь ласка, вводьте лише цілі числа!");
        } catch (ArrayIndexOutOfBoundsException e) {
            // Рівень 1: Перехоплення звернення до неіснуючого індексу
            System.err.println("Помилка: Інвентарю з таким номером немає в базі.");
        } catch (SportsEquipmentException e) {
            // Рівень 3: Перехоплення базового винятку, що ловить обидва наші підкласи
            System.err.println("Помилка обліку інвентарю: " + e.getMessage());
            
            // Рівень 2: Використання додаткової інформації з власного винятку
            if (e instanceof EquipmentUnavailableException) {
                EquipmentUnavailableException eue = (EquipmentUnavailableException) e;
                System.err.println("Запитано: " + eue.getRequestedAmount() + " шт., В наявності: " + eue.getAvailableAmount() + " шт.");
            }
        } finally {
            // Рівень 1: Блок finally для гарантованого закриття ресурсів
            System.out.println("Завершення операції. Закриття з'єднання з вводом...");
            scanner.close();
        }
    }

    // Рівень 3: Демонстрація повторного збудження винятку (re-throw)
    public static void processRental(Equipment item, int amount) throws SportsEquipmentException {
        try {
            item.rentOut(amount);
            System.out.println("Успішно видано! Залишок на складі: " + item.getAvailableCount() + " шт.");
        } catch (EquipmentUnavailableException e) {
            // Часткова обробка: запис в умовний лог
            System.out.println("[ЛОГ СИСТЕМИ]: Спроба видати більше інвентарю (" + item.getName() + "), ніж є в наявності.");
            // Прокидання винятку далі (re-throw) для обробки в main
            throw e; 
        }
    }
}
