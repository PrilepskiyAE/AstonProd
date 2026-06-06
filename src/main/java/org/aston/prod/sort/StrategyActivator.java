package org.aston.prod.sort;

import org.aston.prod.model.Student;

import java.util.Comparator;
import java.util.List;

public class StrategyActivator {

   private SortStrategy sortStrategy;

    /**
     * Начало сортировки
     * @param students коллекция студентов
     * @param comparator компаратор
     */
    public void startSort(List<Student> students, Comparator<Student> comparator) {
        sortStrategy.sort(students, comparator);
    }

    /**
     * Выбор сортировки
     * @param sortStrategy какая сортировка
     */
    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }
}
