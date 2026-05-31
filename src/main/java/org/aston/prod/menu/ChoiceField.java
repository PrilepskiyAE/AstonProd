package org.aston.prod.menu;

import org.aston.prod.model.Student;
import org.aston.prod.sort.BubblesSort;
import org.aston.prod.sort.ChoiceSort;
import org.aston.prod.sort.StrategyActivator;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ChoiceField {

    private final static Scanner scanner=new Scanner(System.in);

    /**
     * сортируем по имени
     * @param students коллекция студентов
     */
    public static void byName(List<Student> students) {
        System.out.println("Какую сортировку предпочитаете?\n 1- пузырьками, 2- выбором");
        String inputSort=scanner.nextLine();
        System.out.print("Сортируем по имени ");
        SelectionSort.sort(students,inputSort).StartSort(students,Comparator.comparing(Student::getName));
        Print.printStudents(students);
    }

    /**
     * сортируем по возрасту
     * @param students коллекция студентов
     */
    public static void byAge(List<Student> students) {
        System.out.println("Какую сортировку предпочитаете?\n 1- пузырьками, 2- выбором");
        String inputSort=scanner.nextLine();
        System.out.print("Сортируем по возрасту ");
        SelectionSort.sort(students,inputSort).StartSort(students,Comparator.comparing(Student::getAge));
        Print.printStudents(students);
    }

    /**
     * сортируем по группе
     * @param students коллекция студентов
     */
    public static void byGroup(List<Student> students) {
        System.out.println("Какую сортировку предпочитаете?\n 1- пузырьками, 2- выбором");
        String inputSort=scanner.nextLine();
        System.out.print("Сортируем по группе ");
        SelectionSort.sort(students,inputSort).StartSort(students,Comparator.comparing(Student::getGroup));
        Print.printStudents(students);
    }
}

class SelectionSort {

    /**
     * выбор сортировки
     * @param students коллекция студентов
     * @param numberSort ноиер сортировки
     * @return возвращаем выбранную сортировку
     */
    public static StrategyActivator sort(List<Student> students, String numberSort) {
        StrategyActivator activator = new StrategyActivator();
        if(numberSort.equals("1")) {
            System.out.println("пузырьками:");
            activator.setSortStrategy(new BubblesSort());
        }
        else if(numberSort.equals("2")) {
            System.out.println("выбором:");
            activator.setSortStrategy(new ChoiceSort());
        }
        return activator;
    }
}
