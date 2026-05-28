package org.aston.prod.sort;

import org.aston.prod.model.Student;

import java.util.Comparator;
import java.util.List;

public class BubblesSort implements SortStrategy {
    private List<Student> _students;
    private int _length;

    public BubblesSort(List<Student> students) {
        _students=students;
        _length=_students.size();
    }

    /**
     * сортировка пузырьками
     * @return возвращаем отсортированную коллекцию
     */
    public List<Student> sort(List<Student> students, Comparator<Student> comparator) {
        for(int i=0;i<_length-1;i++){
            for (int j=0;j<_length-i-1;j++){
                if(comparator.compare(_students.get(j),_students.get(j+1)) > 0){
                    Swap.swap(_students,j,j+1);
                }
            }
        }
        return _students;
    }
}
