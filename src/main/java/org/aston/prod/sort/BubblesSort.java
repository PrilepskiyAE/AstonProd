package org.aston.prod.sort;

import org.aston.prod.model.Student;

import java.util.Comparator;
import java.util.List;

public class BubblesSort implements SortStrategy {

    /**
     * сортировка пузырьками
     */
    public void sort(List<Student> students, Comparator<Student> comparator) {
        int size = students.size();
        for(int i=0;i<size-1;i++){
            for (int j=0;j<size-i-1;j++){
                if(comparator.compare(students.get(j),students.get(j+1)) > 0){
                    Swap.swap(students,j,j+1);
                }
            }
        }
    }
}
