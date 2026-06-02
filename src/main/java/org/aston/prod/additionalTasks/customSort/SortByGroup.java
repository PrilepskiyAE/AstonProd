package org.aston.prod.additionalTasks.customSort;

import org.aston.prod.model.Student;
import org.aston.prod.model.StudentComparators;

import java.util.Comparator;

public class SortByGroup implements CustomSortStrategy {

    @Override
    public Comparator<Student> getStudentComparator() {
        return StudentComparators.byGroup();
    }

    @Override
    public boolean isEven(Student student) {
        return student.getGroup() % 2 == 0;
    }
}
