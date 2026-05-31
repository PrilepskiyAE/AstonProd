package org.aston.prod.menu;

import org.aston.prod.model.Student;

import java.util.List;

public class Print {

    /**
     * Вывод коллекции студентов
     *
     * @param students коллекция студентов
     */
    public static void printStudents(List<Student> students) {
        students.forEach(System.out::println);
    }
}
