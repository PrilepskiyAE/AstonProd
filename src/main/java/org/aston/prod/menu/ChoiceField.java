package org.aston.prod.menu;

import org.aston.prod.model.Student;
import org.aston.prod.sort.*;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
/**
 * Утилитарный класс для интерактивного выбора параметров сортировки студентов.
 * <p>
 * Предоставляет методы для сортировки студентов по различным критериям (имя, возраст, группа)
 * с возможностью выбора алгоритма сортировки через консольный интерфейс.
 */

public class ChoiceField {

    private final static Scanner scanner = new Scanner(System.in);

    /**
     * Сортирует студентов по имени с выбором алгоритма сортировки через консоль.
     * <p>
     * Алгоритм:
     * <ol>
     *   <li>Выводит меню выбора алгоритма сортировки.</li>
     *   <li>Считывает выбор пользователя.</li>
     *   <li>Выполняет сортировку с использованием выбранного алгоритма.</li>
     *   <li>Выводит отсортированный список студентов.</li>
     * </ol>
     *
     * @param students список студентов для сортировки (модифицируется в процессе)
     * @throws IllegalArgumentException если введён неверный номер сортировки
     * @see SelectionSort#sort(String) для доступных алгоритмов сортировки
     */
    public static void byName(List<Student> students) {
        System.out.println("""
                Какую сортировку предпочитаете?
                1- пузырьками, 2- выбором, 3 - вставками,
                4 - челночную, 5 - Шелла""");
        String inputSort = scanner.nextLine();
        System.out.print("Сортируем по имени ");
        SelectionSort.sort(inputSort).startSort(students, Comparator.comparing(Student::getName));
        Print.printStudents(students);
    }

    /**
     * Сортирует студентов по возрасту с выбором алгоритма сортировки через консоль.
     *
     * @param students список студентов для сортировки (модифицируется в процессе)
     * @throws IllegalArgumentException если введён неверный номер сортировки
     * @see SelectionSort#sort(String)
     */
    public static void byAge(List<Student> students) {
        System.out.println("""
                Какую сортировку предпочитаете?
                1- пузырьками, 2- выбором, 3 - вставками,
                4 - челночную, 5 - Шелла""");
        String inputSort = scanner.nextLine();
        System.out.print("Сортируем по возрасту ");
        SelectionSort.sort(inputSort).startSort(students, Comparator.comparing(Student::getAge));
        Print.printStudents(students);
    }

    /**
     * Сортирует студентов по группе с выбором алгоритма сортировки через консоль.
     *
     * @param students список студентов для сортировки (модифицируется в процессе)
     * @throws IllegalArgumentException если введён неверный номер сортировки
     * @see SelectionSort#sort(String)
     */
    public static void byGroup(List<Student> students) {
        System.out.println("""
                Какую сортировку предпочитаете?
                1- пузырьками, 2- выбором, 3 - вставками,
                4 - челночную, 5 - Шелла""");
        String inputSort = scanner.nextLine();
        System.out.print("Сортируем по группе ");
        SelectionSort.sort(inputSort).startSort(students, Comparator.comparing(Student::getGroup));
        Print.printStudents(students);
    }
}
/**
 * Утилитарный класс для выбора алгоритма сортировки по номеру.
 * <p>
 * Реализует фабричный метод, возвращающий {@link StrategyActivator} с настроенной
 * стратегией сортировки в зависимости от ввода пользователя.
 */
class SelectionSort {

    /**
     * Создаёт и настраивает {@link StrategyActivator} с выбранной стратегией сортировки.
     * <p>
     * Поддерживаемые алгоритмы:
     * <ul>
     *   <li><b>1</b> — сортировка пузырьком ({@link BubblesSort})</li>
     *   <li><b>2</b> — сортировка выбором ({@link ChoiceSort})</li>
     *   <li><b>3</b> — сортировка вставками ({@link InsertSort})</li>
     *   <li><b>4</b> — челночная сортировка ({@link ShuttleSort})</li>
     *   <li><b>5</b> — сортировка Шелла ({@link ShellSort})</li>
     * </ul>
     *
     * @param numberSort строка с номером сортировки (1–5)
     * @return {@link StrategyActivator} с установленной стратегией сортировки
     * @throws IllegalArgumentException если номер не соответствует допустимым значениям
     * @see StrategyActivator#setSortStrategy(SortStrategy)
     */

    public static StrategyActivator sort(String numberSort) {
        StrategyActivator activator = new StrategyActivator();
        switch (numberSort) {
            case "1" -> {
                System.out.println("пузырьками:");
                activator.setSortStrategy(new BubblesSort());
            }
            case "2" -> {
                System.out.println("выбором:");
                activator.setSortStrategy(new ChoiceSort());
            }
            case "3" -> {
                System.out.println("вставками:");
                activator.setSortStrategy(new InsertSort());
            }
            case "4" -> {
                System.out.println("челночной сортировкой:");
                activator.setSortStrategy(new ShuttleSort());
            }
            case "5" -> {
                System.out.println("сортировкой Шелла:");
                activator.setSortStrategy(new ShuttleSort());
            }
        }
        return activator;
    }
}
