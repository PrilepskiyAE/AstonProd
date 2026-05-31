package org.aston.prod.sort;

import org.aston.prod.model.Student;

import java.util.Comparator;
import java.util.List;

public class ShuttleSort implements SortStrategy{

    /**
     * челночная сортировка
     * @param students коллекция студентов
     * @param comparator компаратор
     */
    public void sort(List<Student> students, Comparator<Student> comparator) {
        int size = students.size();
        for (int i = 1; i < size - 1; i++) {
            if(comparator.compare(students.get(i - 1), students.get(i)) > 0) {
                Swap.swap(students, i - 1, i);
            }
            for (int j = i; j > 1; j--) {
                if (comparator.compare(students.get(j - 2), students.get(j - 1)) > 0) {
                    Swap.swap(students, j - 2, j - 1);
                }
                else {
                    break;
                }
            }
        }
    }
}
