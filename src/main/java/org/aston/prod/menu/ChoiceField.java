package org.aston.prod.menu;

import org.aston.prod.model.Student;
import org.aston.prod.sort.BubblesSort;

import java.util.Comparator;
import java.util.List;

public class ChoiceField {

    /**
     * сортируем по имени
     * @param students коллекция студентов
     */
    public static void byName(List<Student> students) {
        System.out.println("Сортируем по имени:");
        BubblesSort bs = new BubblesSort();
        bs.sort(students, Comparator.comparing(Student::getName));
        Print.printStudents(students);
    }

    /**
     * сортируем по возрасту
     * @param students коллекция студентов
     */
    public static void byAge(List<Student> students) {
        System.out.println("Сортируем по возрасту:");
        BubblesSort bs = new BubblesSort();
        bs.sort(students,Comparator.comparing(Student::getAge));
        Print.printStudents(students);
    }

    /**
     * сортируем по группе
     * @param students коллекция студентов
     */
    public static void byGroup(List<Student> students) {
        System.out.println("Сортируем по группе:");
        BubblesSort bs = new BubblesSort();
        bs.sort(students,Comparator.comparing(Student::getGroup));
        Print.printStudents(students);
    }
}
