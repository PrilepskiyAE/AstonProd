package org.aston.prod.sort;

import org.aston.prod.model.Student;
import java.util.Comparator;
import java.util.List;

public class InsertSort implements SortStrategy{

    /**
     * сортировка вставками
     * @param students коллекция студентов
     * @param comparator компаратор
     */
    public void sort(List<Student> students, Comparator<Student> comparator) {
        int size = students.size();
        for (int i = 0; i < size; i++) {
            Student value = students.get(i);
            int j = i;
            for ( ; j > 0; j--) {
                if (comparator.compare(value, students.get(j-1)) < 0 ) {
                    students.set(j,students.get(j-1));
                }
                else {
                    break;
                }
            }
            students.set(j,value);
        }
    }
}
