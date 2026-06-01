package org.aston.prod.additionalTasks.customSort;

import org.aston.prod.model.Student;
import org.aston.prod.sort.StrategyActivator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface CustomSortStrategy {
    default void sort(List<Student> studentsList, StrategyActivator strategyActivator) {
        List<Student> studentsEvenLength = new ArrayList<>();
        List<Integer> numberList = new ArrayList<>();

        for (int i = 0; i < studentsList.size(); i++) {
            Student student = studentsList.get(i);
            if (isEven(student)) {
                studentsEvenLength.add(student);
                numberList.add(i);
            }
        }
        strategyActivator.startSort(studentsEvenLength, getStudentComparator());
        for (int i = 0; i < studentsEvenLength.size(); i++) {
            studentsList.set(numberList.get(i), studentsEvenLength.get(i));
        }
    }

    Comparator<Student> getStudentComparator();

    boolean isEven(Student student);
}