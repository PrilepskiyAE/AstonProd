package org.aston.prod.sort;

import org.aston.prod.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort implements SortStrategy {

    /**
     * Сортировка слиянием
     * @param students коллекция студентов
     * @param comparator компаратор
     */
    public void sort(List<Student> students, Comparator<Student> comparator) {
        if (students.size() < 2) {
            return;
        }
        int mid = students.size() / 2;
        List<Student> left = new ArrayList<>();
        List<Student> right = new ArrayList<>();
        for (int i = 0; i < mid; i++) {
            left.add(students.get(i));
        }
        for (int i = mid; i < students.size(); i++) {
            right.add(students.get(i));
        }
        sort(left, comparator);
        sort(right, comparator);
        mergeSort(students, comparator, left, right);
    }

    /**
     * Сортируем каждую часть списка
     * @param students список студентов
     * @param comparator компаратор
     * @param left левая часть списка
     * @param right правая часть списка
     */
    private void mergeSort(List<Student> students, Comparator<Student> comparator, List<Student> left,
                           List<Student> right) {
        int iLeft = 0, iRight = 0, iStudents = 0;
        while (iLeft < left.size() && iRight < right.size()) {
            if (comparator.compare(left.get(iLeft), right.get(iRight)) < 0) {
                students.set(iStudents++, left.get(iLeft++));
            }
            else {
                students.set(iStudents++, right.get(iRight++));
            }
        }
        while (iLeft < left.size()) {
            students.set(iStudents++, left.get(iLeft++));
        }
        while (iRight < right.size()) {
            students.set(iStudents++, right.get(iRight++));
        }
    }
}
