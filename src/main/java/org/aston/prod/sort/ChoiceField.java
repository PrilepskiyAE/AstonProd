package org.aston.prod.sort;

import org.aston.prod.model.Student;

import java.util.Comparator;
import java.util.List;

public class ChoiceField {

    /**
     * сортируем по имени
     * @param students коллекция студентов
     */
    public static void byName(List<Student> students) {
        System.out.println("sorted by name:");
        BubblesSort bs = new BubblesSort(students);
        bs.sort(students, Comparator.comparing(Student::getName));
        Print.printStudents(students);
    }

    /**
     * сортируем по возрасту
     * @param students коллекция студентов
     */
    public static void byAge(List<Student> students) {
        System.out.println("sorted by age:");
        BubblesSort bs = new BubblesSort(students);
        bs.sort(students,Comparator.comparing(Student::getAge));
        Print.printStudents(students);
    }

    /**
     * сортируем по группе
     * @param students коллекция студентов
     */
    public static void byGroup(List<Student> students) {
        System.out.println("sorted by group:");
        BubblesSort bs = new BubblesSort(students);
        bs.sort(students,Comparator.comparing(Student::getGroup));
        Print.printStudents(students);
    }
}
