package org.aston.prod.menu;

import org.aston.prod.model.Student;
import org.aston.prod.sort.*;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ChoiceField {

    private final static Scanner scanner = new Scanner(System.in);

    /**
     * сортируем по имени
     *
     * @param students коллекция студентов
     */
    public static void byName(List<Student> students) {
        System.out.println("""
                Какую сортировку предпочитаете?
                1- пузырьками, 2- выбором, 3 - вставками,
                4 - челночную, 5 - Шелла, 6 - слиянием""");
        String inputSort = scanner.nextLine();
        System.out.print("Сортируем по имени ");
        SelectionSort.sort(inputSort).startSort(students, Comparator.comparing(Student::getName));
        Print.printStudents(students);
    }

    /**
     * сортируем по возрасту
     *
     * @param students коллекция студентов
     */
    public static void byAge(List<Student> students) {
        System.out.println("""
                Какую сортировку предпочитаете?
                1- пузырьками, 2- выбором, 3 - вставками,
                4 - челночную, 5 - Шелла, 6 - слиянием""");
        String inputSort = scanner.nextLine();
        System.out.print("Сортируем по возрасту ");
        SelectionSort.sort(inputSort).startSort(students, Comparator.comparing(Student::getAge));
        Print.printStudents(students);
    }

    /**
     * сортируем по группе
     *
     * @param students коллекция студентов
     */
    public static void byGroup(List<Student> students) {
        System.out.println("""
                Какую сортировку предпочитаете?
                1- пузырьками, 2- выбором, 3 - вставками,
                4 - челночную, 5 - Шелла, 6 - слиянием""");
        String inputSort = scanner.nextLine();
        System.out.print("Сортируем по группе ");
        SelectionSort.sort(inputSort).startSort(students, Comparator.comparing(Student::getGroup));
        Print.printStudents(students);
    }
}

class SelectionSort {

    /**
     * выбор сортировки
     *
     * @param numberSort ноиер сортировки
     * @return возвращаем выбранную сортировку
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
                activator.setSortStrategy(new ShellSort());
            }
            case "6" -> {
                System.out.println("слиянием:");
                activator.setSortStrategy(new MergeSort());
            }
        }
        return activator;
    }
}
