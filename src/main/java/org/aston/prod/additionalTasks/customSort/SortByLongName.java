package org.aston.prod.additionalTasks.customSort;

import org.aston.prod.model.Student;
import org.aston.prod.model.StudentComparators;

import java.util.Comparator;

/* Сортируем по длине имени, если длина имени четная, то сортируем если нет, то оставляем на месте */
public class SortByLongName implements CustomSortStrategy {
    @Override
    public Comparator<Student> getStudentComparator() {
        return StudentComparators.byName();
    }

    @Override
    public boolean isEven(Student student) {
        return student.getName().length() % 2 == 0;
    }
}