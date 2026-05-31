package org.aston.prod.sort;

import org.aston.prod.model.Student;

import java.util.Comparator;
import java.util.List;

public class ChoiceSort implements SortStrategy {

    /**
     * сортировка выбором
     * @param students коллекция студентов
     * @param comparator компаратор
     */
    public void sort(List<Student> students,Comparator<Student> comparator) {
        int size = students.size();
        for(int i = 0; i < size - 1;i++) {
            int min = i;
            for(int j = i + 1; j < size; j++) {
                if(comparator.compare(students.get(j),students.get(min))<0) {
                    min = j;
                }
            }
            Swap.swap(students,i,min);
        }
    }
}
