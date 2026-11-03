# Лабораторна робота №5
## Рефакторинг програмного коду із застосуванням паттернів проєктування

**Предметна область:** Спортивний інвентар  
**Мета:** Застосувати паттерни проєктування GoF (Factory, Observer, Adapter) для покращення архітектури програми, створеної у попередніх лабораторних роботах.

## 1. Опис проєкту

Програма призначена для обліку спортивного інвентарю.

Основні можливості:
- Створення об'єктів спортивного інвентарю через консоль.
- Зберігання назви, категорії, кількості, ціни та відсотка зносу.
- Сортування інвентарю за ціною методом бульбашки.
- Лінійний пошук за назвою.
- Обробка виняткових ситуацій.
- Розрахунок вартості обслуговування за різними стратегіями.
- Відстеження змін стану інвентарю.
- Використання умовного зовнішнього сервісу ремонту.

Лабораторна робота №5 є продовженням ЛР №1–4. Основну функціональність програми збережено, а архітектуру розширено застосуванням паттернів проєктування.

## 2. Factory (Simple Factory)

**Проблема до рефакторингу:**

У класі `Main` конкретні стратегії обслуговування створювалися безпосередньо:

```java
MaintenanceStrategy[] strategies = {
    new BasicMaintenance(),
    new FullMaintenance()
};
```

Це створювало залежність клієнтського коду від конкретних реалізацій `MaintenanceStrategy`.

**Рішення:**

Створено клас `MaintenanceStrategyFactory`, який централізує логіку створення стратегій залежно від переданого типу.

Після рефакторингу:

```java
MaintenanceStrategy[] strategies = {
    MaintenanceStrategyFactory.create(1),
    MaintenanceStrategyFactory.create(2)
};
```

Фабрика підтримує три типи:
- `1` — `BasicMaintenance`
- `2` — `FullMaintenance`
- `3` — `LegacyRepairAdapter`

Для невідомого типу передбачено `IllegalArgumentException`.

**Результат:** створення стратегій зосереджено в одному класі. Додавання нової реалізації не потребує повторення логіки створення в різних місцях клієнтського коду, хоча фабрику потрібно буде розширити.

Використано Simple Factory — спрощений фабричний підхід, дозволений умовами лабораторної роботи.

## 3. Observer

**Проблема:**

Зміна відсотка зносу спортивного інвентарю повинна викликати реакцію кількох незалежних компонентів. Прямі виклики конкретних класів усередині `SportEquipment` створили б зайві залежності.

**Рішення:**

Створено інтерфейс `EquipmentObserver` з методом `update(SportEquipment equipment)`.

Клас `SportEquipment` виступає суб'єктом (Subject), який зберігає список спостерігачів.

Реалізовано два спостерігачі:

- `EquipmentLogger` — виводить повідомлення про зміну стану інвентарю.
- `MaintenanceNotifier` — попереджає, коли відсоток зносу перевищує 50%.

Приклад використання:

```java
equipment.addObserver(new EquipmentLogger());
equipment.addObserver(new MaintenanceNotifier());

equipment.setWearPercent(75);
```

Після зміни стану викликається `notifyObservers()`, який сповіщає зареєстрованих спостерігачів.

**Результат:** нових спостерігачів можна додавати без зміни логіки сповіщення у `SportEquipment`. Суб'єкт взаємодіє зі спостерігачами через інтерфейс, забезпечуючи слабку зв'язаність.

## 4. Adapter

**Проблема:**

Для моделювання інтеграції зі сторонньою системою створено `LegacyRepairService`, який має метод:

```java
estimateRepairPrice(String itemName, int damageLevel, double itemPrice)
```

Водночас програма використовує інтерфейс `MaintenanceStrategy` з методом:

```java
calculateCost(SportEquipment equipment)
```

Ці інтерфейси несумісні.

**Рішення:**

Створено `LegacyRepairAdapter`, який реалізує `MaintenanceStrategy` та містить посилання на `LegacyRepairService`.

Адаптер отримує `SportEquipment`, витягує необхідні дані та передає їх до зовнішнього сервісу.

Приклад використання:

```java
MaintenanceStrategy strategy =
        MaintenanceStrategyFactory.create(3);

MaintenanceService service =
        new MaintenanceService(strategy);

double cost = service.calculate(equipment);
```

**Результат:** умовний сторонній сервіс інтегровано з наявною архітектурою без зміни його інтерфейсу та без переписування `MaintenanceService`.

## 5. Взаємодія паттернів

У програмі використовуються паттерни з ЛР4 та ЛР5:

| Паттерн | Призначення |
|---|---|
| Strategy | Вибір алгоритму розрахунку вартості обслуговування |
| Template Method | Спільний алгоритм формування результатів обслуговування в ієрархії класів |
| Simple Factory | Створення конкретних стратегій |
| Observer | Сповіщення про зміну стану інвентарю |
| Adapter | Інтеграція зовнішнього сервісу ремонту |

Паттерни вирішують різні задачі та можуть використовуватися разом.

## 6. Структура проєкту

```text
sportsequipment/
├── exceptions/
│   ├── EquipmentException.java
│   ├── InvalidPriceException.java
│   └── InvalidWearPercentException.java
├── maintenance/
│   ├── adapter/
│   │   ├── LegacyRepairAdapter.java
│   │   └── LegacyRepairService.java
│   ├── factory/
│   │   └── MaintenanceStrategyFactory.java
│   ├── interfaces/
│   │   ├── MaintenanceReport.java
│   │   └── MaintenanceStrategy.java
│   ├── strategies/
│   │   ├── AbstractMaintenance.java
│   │   ├── BasicMaintenance.java
│   │   └── FullMaintenance.java
│   └── MaintenanceService.java
├── observers/
│   ├── EquipmentLogger.java
│   ├── EquipmentObserver.java
│   └── MaintenanceNotifier.java
├── Main.java
├── SportEquipment.java
└── README.md
```

## 7. Компіляція та запуск

Для роботи потрібен встановлений JDK із підтримкою синтаксису `switch` expressions.

Перейти в кореневу папку `src`:

```powershell
cd G:\Programming\java\src
```

Скомпілювати всі Java-файли:

```powershell
javac -encoding UTF-8 -d . (Get-ChildItem .\javalabs\lab2\sportsequipment -Recurse -Filter *.java).FullName
```

Запустити програму:

```powershell
java -cp . javalabs.lab2.sportsequipment.Main
```

## 8. Перевірка роботи

Для демонстрації можна створити спортивний інвентар із такими значеннями:

- Назва: Футбольний м'яч
- Категорія: М'ячі
- Кількість: 5
- Ціна за одиницю: 1200
- Відсоток зносу: 20

Програма виконує сортування, пошук, демонстрацію виняткових ситуацій і розрахунок вартості обслуговування.

**Factory:** створює різні реалізації `MaintenanceStrategy` через `MaintenanceStrategyFactory.create()`.

**Observer:** під час зміни відсотка зносу з 20% на 75% сповіщає `EquipmentLogger` і `MaintenanceNotifier`.

**Adapter:** дозволяє розрахувати вартість обслуговування через умовний зовнішній сервіс за допомогою `LegacyRepairAdapter`.

## 9. Висновок

У результаті лабораторної роботи проведено рефакторинг програмного коду з використанням Simple Factory, Observer та Adapter.

Factory зменшив залежність клієнтського коду від конкретних реалізацій стратегій. Observer забезпечив незалежне сповіщення кількох компонентів про зміни стану інвентарю. Adapter дозволив інтегрувати несумісний інтерфейс умовного стороннього сервісу без зміни наявної системи обслуговування.

Збережено основні можливості попередніх лабораторних робіт. Архітектура стала більш модульною та зручною для подальшого розширення.