package org.aston.prod.additionalTasks.customSort;

import org.aston.prod.model.Student;
import org.aston.prod.model.StudentComparators;

import java.util.Comparator;

public class SortByAge implements CustomSortStrategy {

    @Override
    public Comparator<Student> getStudentComparator() {
        return StudentComparators.byAge();
    }

    @Override
    public boolean isEven(Student student) {
        return student.getAge() % 2 == 0;
    }
}