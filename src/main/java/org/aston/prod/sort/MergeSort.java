package org.aston.prod.sort;

import org.aston.prod.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort implements SortStrategy{

    /**
     * сортировка слиянием
     * @param students коллекция студентов
     * @param comparator компаратор
     */
    public void sort(List<Student> students, Comparator<Student> comparator){
        mergeSort(students, comparator, 0, students.size() - 1);
    }

    /**
     * отдельный метод для рекурсии
     * @param students список студентов
     * @param comparator компаратор
     * @param first начало списка
     * @param last конец списка
     */
    private void mergeSort(List<Student> students, Comparator<Student> comparator, int first, int last){
        int delimiter =first + ((last - first) / 2) + 1;
        if (delimiter > 0 && last > first + 1) {
            mergeSort(students, comparator, first, delimiter - 1);
            mergeSort(students, comparator, delimiter, last);
        }
        List<Student> buffer = new ArrayList<Student>();
        int size = last - first + 1;
        int cursor = first;
        for (int i = 0; i < size; i++) {
            if (delimiter > last || comparator.compare(students.get(cursor), students.get(delimiter)) < 0) {
                buffer.add(students.get(cursor));
                cursor++;
            }
            else {
                buffer.add(students.get(delimiter));
                delimiter++;
            }
        }
        for (int i = first; i < size; i++) {
            students.set(i, buffer.get(i));
        }
    }
}
