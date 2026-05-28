package org.aston.prod.sort;

import org.aston.prod.model.Student;

import java.util.Comparator;
import java.util.List;

public interface SortStrategy {
    List<Student> sort(List<Student> students, Comparator<Student> comparator);
}
