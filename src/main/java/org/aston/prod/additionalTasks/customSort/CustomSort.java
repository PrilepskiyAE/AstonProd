package org.aston.prod.additionalTasks.customSort;

import org.aston.prod.model.Student;
import org.aston.prod.sort.StrategyActivator;

import java.util.List;

/**
 * Чётные значения сортируются в естественном порядке;
 * нечётные значения остаются на исходных позициях.
 */
public class CustomSort {
    private StrategyActivator strategyActivator;
    private CustomSortStrategy customSortStrategy;

    public CustomSort(StrategyActivator strategyActivator) {
        this.strategyActivator = strategyActivator;
        customSortStrategy = new SortByAge();
    }

    public void setCustomSortStrategy(CustomSortStrategy customSortStrategy) {
        this.customSortStrategy = customSortStrategy;
    }

    public void sort(List<Student> studentList) {
        long time = System.nanoTime();
        customSortStrategy.sort(studentList, strategyActivator);
        time = (System.nanoTime() - time) / 1_000_000;
        System.out.println("Сортировка заняла " + time + " миллисекунд");
    }
}