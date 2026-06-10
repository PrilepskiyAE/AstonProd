package org.aston.prod.sort;

import org.aston.prod.model.Student;

import java.util.Comparator;
import java.util.List;

public class QuickSort implements SortStrategy {

    /**
     * Быстрая сортировка
     * @param students список студентов
     * @param comparator компаратор
     */
    public void sort(List<Student> students, Comparator<Student> comparator) {
        if (students.size() < 2) {
            return;
        }
        quick(students, comparator, 0, students.size() - 1);
    }

    /**
     * Рекурсия быстрой сортировки
     * @param students список студентов
     * @param comparator компаратор
     * @param left левая граница списка
     * @param right правая граница списка
     */
    private void quick(List<Student> students, Comparator<Student> comparator, int left, int right) {
        int leftMarker, rightMarker;
        leftMarker = left;
        rightMarker = right;
        Student pivot = students.get((rightMarker + leftMarker) / 2);
        do {
            while (comparator.compare(students.get(leftMarker), pivot) < 0) {
                leftMarker++;
            }
            while (comparator.compare(students.get(rightMarker), pivot) > 0) {
                rightMarker--;
            }
            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker) {
                    Swap.swap(students, leftMarker, rightMarker);
                }
                leftMarker++;
                rightMarker--;
            }
        }while (leftMarker <= rightMarker);
        if (leftMarker < right) {
            quick(students, comparator, leftMarker, right);
        }
        if (left < rightMarker) {
            quick(students, comparator, left, rightMarker);
        }
    }
}
