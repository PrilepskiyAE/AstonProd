package org.aston.prod.additionalTasks.findElements;

import org.aston.prod.additionalTasks.findElements.validation.Validation;
import org.aston.prod.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

public class ElementFind {

    /**
     * Осуществляет поиск вхождений заданного значения в коллекции студентов.
     * В зависимости от размера коллекции и количества потоков выбирает стратегию поиска:
     * — для коллекций размером менее 20 элементов использует параллельные стримы;
     * — для больших коллекций — многопоточный поиск с ограничением до 10 потоков.
     *
     * @param students коллекция объектов Student, в которой выполняется поиск
     * @param scanner  объект Scanner для считывания входных данных от пользователя:
     *                 - номера поля для поиска ({@code field});
     *                 - искомого значения ({@code value});
     *                 - количества потоков ({@code numberThreads})
     */

    public void find(List<Student> students, Scanner scanner) {
        int field = field(scanner);
        String value = value(scanner, field);
        int numberThreads = numberThreads(scanner);
        int result;

        if (students.size() < 20) {
            System.out.println("Для маленького списка студентов используются параллельные стримы");
            result = findByParallelStream(students, field, value);
        } else {
            if (numberThreads > 10) {
                System.out.println("Вы и правда хотите создать так много потоков? Давайте ограничимся 10 потоками");
                numberThreads = 10;
            }

            result = findByParameter(students, field, value, numberThreads);
        }
        System.out.println("Количество вхождений элемента в коллекцию = " + result);
    }


    /**
     * Выполняет поиск вхождений заданного значения в коллекции студентов с использованием параллельного стрима.
     * Поиск осуществляется по указанному полю (имя, возраст или группа).
     *
     * @param students коллекция объектов Student, в которой выполняется поиск
     * @param field    код поля для поиска:
     *                 <ul>
     *                   <li>1 — поиск по имени ({@code getName()})</li>
     *                   <li>2 — поиск по возрасту ({@code getAge()})</li>
     *                   <li>любое другое значение — поиск по номеру группы ({@code getGroup()})</li>
     *                 </ul>
     * @param value    искомое значение в виде строки. Для полей возраста и группы
     *                 значение будет преобразовано в число с помощью {@code Integer.parseInt()}
     * @return количество элементов в коллекции, удовлетворяющих условию поиска
     * @throws NumberFormatException если параметр {@code value} не может быть преобразован
     */

    private int findByParallelStream(List<Student> students, int field, String value) {
        Predicate<Student> studentPredicate;
        if (field == 1)
            studentPredicate = s -> s.getName().equals(value);
        else if (field == 2)
            studentPredicate = s -> s.getAge() == Integer.parseInt(value);
        else
            studentPredicate = s -> s.getGroup() == Integer.parseInt(value);

        return (int) students.parallelStream().filter(studentPredicate).count();
    }

    /**
     * Выполняет поиск вхождений заданного значения в коллекции студентов с использованием
     * многопоточной обработки через ExecutorService и CompletableFuture.
     * Коллекция разбивается на части, каждая из которых обрабатывается отдельным потоком.
     *
     * @param students      коллекция объектов Student, в которой выполняется поиск
     * @param field         код поля для поиска:
     *                      <ul>
     *                        <li>1 — поиск по имени ({@code getName()})</li>
     *                        <li>2 — поиск по возрасту ({@code getAge()})</li>
     *                        <li>любое другое значение — поиск по номеру группы ({@code getGroup()})</li>
     *                      </ul>
     * @param value         искомое значение в виде строки. Для полей возраста и группы
     *                      значение будет преобразовано в число с помощью {@code Integer.parseInt()}
     * @param numberThreads количество потоков для параллельной обработки. Должно быть положительным числом.
     *                      При создании пула потоков используется {@code Executors.newFixedThreadPool()}
     * @return общее количество элементов в коллекции, удовлетворяющих условию поиска
     * @throws NumberFormatException если параметр {@code value} не может быть преобразован
     *                               в число при поиске по возрасту или группе (в рамках вызова {@code checkValue()})
     */

    private int findByParameter(List<Student> students, int field, String value, int numberThreads) {
        int result = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(numberThreads);

        int size = (students.size() + numberThreads - 1) / numberThreads;
        List<CompletableFuture<Long>> futures = new ArrayList<>();
        try {
            for (int i = 0; i < numberThreads; i++) {
                int from = i * size;
                int to = Math.min(from + size, students.size());
                if (from >= students.size()) {
                    break;
                }
                List<Student> studentSubList = students.subList(from, to);

                CompletableFuture<Long> future = CompletableFuture.supplyAsync(() ->
                        studentSubList.stream().filter(s -> checkValue(s, field, value)).count(), executorService);

                futures.add(future);
            }
            result = (int) futures.stream().mapToLong(CompletableFuture::join).sum();
        } catch (Exception e) {
            System.out.println("Возникла ошибка в процессе выполнения, попробуйте еще раз");
        } finally {
            executorService.shutdown();
        }
        return result;
    }

    /**
     * Проверяет, соответствует ли значение указанного поля студента заданному критерию поиска.
     * Сравнение выполняется для одного из трёх полей: имени, возраста или номера группы.
     *
     * @param student объект Student, поле которого подлежит проверке
     * @param field   код поля для проверки:
     *                <ul>
     *                  <li>1 — имя студента ({@code getName()})</li>
     *                  <li>2 — возраст студента ({@code getAge()})</li>
     *                  <li>3 — номер группы студента ({@code getGroup()})</li>
     *                </ul>
     *                При любом другом значении метода возвращает {@code false}
     * @param value   искомое значение в виде строки. Для полей возраста и группы
     *                значение будет преобразовано в число с помощью {@code Integer.parseInt()}
     * @return результат проверки:
     * <ul>
     *   <li>{@code true} — если значение поля студента совпадает с искомым;</li>
     *   <li>{@code false} — если значения не совпадают или код поля некорректен</li>
     * </ul>
     * @throws NumberFormatException если параметр {@code value} не может быть преобразован
     *                               в число при поиске по возрасту (поле 2) или группе (поле 3)
     *
     */

    private boolean checkValue(Student student, int field, String value) {
        switch (field) {
            case 1 -> {
                return student.getName().equals(value);
            }
            case 2 -> {
                return student.getAge() == Integer.parseInt(value);
            }
            case 3 -> {
                return student.getGroup() == Integer.parseInt(value);
            }
        }
        return false;
    }

    /**
     * Запрашивает у пользователя количество потоков для параллельной обработки
     * и выполняет валидацию введённого значения.
     * <p>
     * Процесс взаимодействия:
     * 1. Выводит приглашение к вводу.
     * 2. Считывает строку из сканера.
     * 3. Проверяет, что строка содержит только цифры (положительное целое число).
     * 4. Преобразует строку в число.
     * 5. Проверяет, что число больше нуля.
     * 6. При успешной валидации возвращает значение; при ошибке — повторяет запрос.
     *
     * @param scanner объект Scanner для считывания ввода пользователя с консоли
     * @return положительное целое число — количество потоков для использования
     * в параллельных вычислениях
     */

    private int numberThreads(Scanner scanner) {
        while (true) {
            System.out.println("Сколько потоков будем использовать для поиска?");
            String stringThreads = scanner.nextLine();
            if (!stringThreads.matches("\\d+")) {
                System.out.println("Количество потоков должно быть положительным числом");
                continue;
            }
            int numberThreads = Integer.parseInt(stringThreads);
            if (numberThreads < 1) {
                System.out.println("Количество потоков должно быть положительным");
                continue;
            }
            return numberThreads;
        }
    }


    /**
     * Запрашивает у пользователя значение для поиска по указанному полю студента
     * и выполняет валидацию введённого значения в зависимости от типа поля.
     * <p>
     * Процесс взаимодействия:
     * 1. Выводит приглашение к вводу.
     * 2. Считывает строку из сканера.
     * 3. В зависимости от значения {@code field} выполняет специфичную валидацию:
     * - для имени (поле 1) — использует метод {@code checkName()} класса {@code Validation};
     * - для возраста (поле 2) — проверяет, что строка состоит из цифр,
     * и значение находится в диапазоне 10–100 лет;
     * - для группы (поле 3) — проверяет, что строка состоит из цифр
     * и номер группы положительный (больше нуля).
     * 4. При успешной валидации возвращает очищенное от пробелов значение;
     * при ошибке — выводит сообщение и повторяет запрос.
     *
     * @param sc    объект Scanner для считывания ввода пользователя с консоли
     * @param field код поля для поиска:
     *              <ul>
     *                <li>1 — поиск по имени студента;</li>
     *                <li>2 — поиск по возрасту студента;</li>
     *                <li>3 — поиск по номеру группы студента</li>
     *              </ul>
     * @return введённое пользователем значение, очищенное от ведущих и замыкающих пробелов ({@code trim()}),
     * после успешной валидации
     *
     */

    private String value(Scanner sc, int field) {
        Validation validation = new Validation();
        while (true) {
            System.out.println("Введите значение которое будем искать:");
            String value = sc.nextLine();
            if (field == 1) {
                if (!validation.checkName(value))
                    continue;
            }
            if (field == 2) {
                if (!value.matches("\\d+")) {
                    System.out.println("Возраст должен состоять только из цифр");
                    continue;
                }
                int age = Integer.parseInt(value);
                if (age < 10 || age > 100) {
                    System.out.println("Возраст должен быть от 10 до 100 лет");
                    continue;
                }
            }
            if (field == 3) {
                if (!value.matches("\\d+")) {
                    System.out.println("Группа должна состоять только из цифр");
                    continue;
                }
                int group = Integer.parseInt(value);
                if (group < 1) {
                    System.out.println("Номер группы не может быть отрицательный или равен нулю");
                    continue;
                }
            }
            return value.trim();
        }
    }

    /**
     * Запрашивает у пользователя код поля, по которому будет выполняться поиск,
     * и выполняет валидацию введённого значения.
     * <p>
     * Процесс взаимодействия:
     * 1. Выводит меню с вариантами выбора поля для поиска (имя, возраст, группа).
     * 2. Считывает строку из сканера.
     * 3. Проверяет, что введённое значение — это "1", "2" или "3".
     * 4. При успешной валидации преобразует строку в число и возвращает его;
     * при ошибке — выводит сообщение и повторяет запрос.
     *
     * @param scanner объект Scanner для считывания ввода пользователя с консоли
     * @return код поля для поиска:
     * <ul>
     *   <li>1 — поиск по имени студента;</li>
     *   <li>2 — поиск по возрасту студента;</li>
     *   <li>3 — поиск по номеру группы студента</li>
     * </ul>
     */

    private int field(Scanner scanner) {
        while (true) {
            System.out.println("""
                    По какому полю будем искать?
                    Нажмите "1" Чтобы искать по имени
                    Нажмите "2" Чтобы искать по возрасту
                    Нажмите "3" Чтобы искать по группе""");
            String field = scanner.nextLine();
            if (!field.equals("1") && !field.equals("2") && !field.equals("3")) {
                System.out.println("Вы ввели не корректное поле для поиска, повторите операцию еще раз");
            } else return Integer.parseInt(field);
        }
    }
}
