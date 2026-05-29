package org.aston.prod.sort;

import org.aston.prod.model.Student;

import java.util.List;

public class Swap {

    /**
     * обмен значениями в коллекции
     * @param students коллекция
     * @param first индекс первого значения
     * @param second индекс второго значения
     */
    public static void swap(List<Student> students, int first, int second){
        Student temporary=students.get(first);
        students.set(first,students.get(second));
        students.set(second,temporary);
    }
}
