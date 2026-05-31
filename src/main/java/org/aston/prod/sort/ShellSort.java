package org.aston.prod.sort;

import org.aston.prod.model.Student;

import java.util.Comparator;
import java.util.List;

public class ShellSort implements SortStrategy {

    /**
     * сортировка Шелла
     * @param students коллекция студентов
     * @param comparator компаратор
     */
    public void sort(List<Student> students, Comparator<Student> comparator) {
        int size = students.size();
        int divide = size / 2;
        while (divide >= 1) {
            for (int i = 0; i < size; i++) {
                for (int j = i - divide; j >= 0; j -= divide) {
                    if (comparator.compare(students.get(j), students.get(j + divide)) > 0) {
                        Swap.swap(students, j, j + divide);
                    }
                }
            }
            divide /= 2;
        }
    }
}
