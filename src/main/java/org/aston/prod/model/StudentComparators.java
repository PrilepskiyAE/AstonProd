package org.aston.prod.model;

import java.util.*;

/**
 * Набор готовых компараторов для сортировки объектов {@link Student}.
 * Предоставляет статические методы для получения {@link Comparator},
 * которые сортируют студентов по имени, возрасту или номеру группы.
 */

public abstract class StudentComparators {

    /**
     * Возвращает компаратор, сортирующий по всем 3 полям класса студент по выбору пользователя.
     * Натуральный или обратный порядок сортировки так же выбирает пользователь.
     *
     * @param scanner принимаем сканер для получения введенных пользователем значений
     * @return {@link Comparator} для сортировки по выбору пользователя
     */
    public static Comparator<Student> customComparator(Scanner scanner) {
        return ((o1, o2) -> {
            List<String> queue = queueCompare(scanner);
            int result = 0;
            for (String s : queue) {
                int multiplication = 1;
                if (s.startsWith("-")) {
                    multiplication = -1;
                    s = s.substring(1);
                }
                switch (s) {
                    case "1" -> result = o1.getName().compareTo(o2.getName()) * multiplication;
                    case "2" -> result = compareInt(o1.getAge(), o2.getAge()) * multiplication;
                    case "3" -> result = compareInt(o1.getGroup(), o2.getGroup()) * multiplication;
                }
                if (result != 0)
                    break;
            }
            return result;
        });
    }

    /**
     * Спрашиваем у пользователя в каком порядке он хочет отсортировать коллекцию
     *
     * @param scanner принимаем сканер для получения введенных пользователем значений
     * @return {@link List} список в котором указан порядок сортировки
     */

    private static List<String> queueCompare(Scanner scanner) {
        List<String> queueList = new ArrayList<>();
        HashMap<String, String> textMap = new HashMap<>(Map.of(
                "1", "Введите 1 для сортировки по имени",
                "2", "Введите 2 для сортировки по возрасту",
                "3", "Введите 3 для сортировки по группам"
        ));
        HashMap<String, String> headList = new HashMap<>(Map.of(
                "1", "По какому полю мы будем сортировать список в первую очередь?",
                "2", "По какому полю мы будем сортировать список во вторую очередь?",
                "3", "Осталось всего одно поле, но в каком порядке его будем сортировать?"
        ));
        for (int i = 1; i < 4; i++) {
            System.out.println(headList.get(String.valueOf(i)));
            mapToConsole(textMap);
            String value;
            String testValue;
            do {
                value = scanner.nextLine();
                testValue = value;
                if (testValue.startsWith("-")) {
                    testValue = testValue.substring(1);
                }
                if (!textMap.containsKey(testValue)) {
                    System.out.println("Было введено не корректное значение, пожалуйста повторите попытку ввода");
                    mapToConsole(textMap);
                }
            } while (!textMap.containsKey(testValue));
            queueList.add(value);
            if (value.startsWith("-"))
                value = value.substring(1);
            textMap.remove(value);
        }
        return queueList;
    }

    /**
     * Выводим значения мапы отдельным методом, чтобы избежать дублирования кода
     *
     * @param textMap принимаем мапу со значениями которые будем выводить в консоль
     */

    private static void mapToConsole(Map<String, String> textMap) {
        for (int j = 1; j < 4; j++) {
            String str = textMap.get(String.valueOf(j));
            if (str == null) continue;
            System.out.println(str);
        }
        System.out.println("Для сортировки в обратном порядке, поставьте знак \"-\" перед тем как ввести цифру");
    }

    /**
     * Сравниваем числовые значения
     *
     * @param x первое число для сравнения
     * @param y второе число для сравнения
     * @return возвращаем результат сравнения
     */
    private static int compareInt(int x, int y) {
        if (x == y) return 0;
        return x > y ? 1 : -1;
    }

    /**
     * Возвращает компаратор, сортирующий студентов по имени в лексикографическом порядке.
     *
     * @return {@link Comparator} для сортировки по имени
     */
    public static Comparator<Student> byName() {
        return (o1, o2) -> {
            int result = o1.getName().compareTo(o2.getName());
            if (result != 0) return result;
            result = compareInt(o1.getAge(), o2.getAge());
            if (result != 0) return result;
            return compareInt(o1.getGroup(), o2.getGroup());
        };
    }

    /**
     * Возвращает компаратор, сортирующий студентов по возрасту в порядке возрастания.
     *
     * @return {@link Comparator} для сортировки по возрасту
     */
    public static Comparator<Student> byAge() {
        return (o1, o2) -> {
            int result = compareInt(o1.getAge(), o2.getAge());
            if (result != 0) return result;
            result = compareInt(o1.getGroup(), o2.getGroup());
            if (result != 0) return result;
            return o1.getName().compareTo(o2.getName());
        };
    }

    /**
     * Возвращает компаратор, сортирующий студентов по номеру группы в порядке возрастания.
     *
     * @return {@link Comparator} для сортировки по номеру группы
     */
    public static Comparator<Student> byGroup() {
        return (o1, o2) -> {
            int result = compareInt(o1.getGroup(), o2.getGroup());
            if (result != 0) return result;
            result = o1.getName().compareTo(o2.getName());
            if (result != 0) return result;
            return compareInt(o1.getAge(), o2.getAge());

        };

    }
}