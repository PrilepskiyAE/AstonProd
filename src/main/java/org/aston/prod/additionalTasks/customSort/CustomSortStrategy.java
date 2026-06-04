package org.aston.prod.additionalTasks.customSort;

import org.aston.prod.model.Student;
import org.aston.prod.sort.StrategyActivator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Стратегия сортировки студентов с фильтрацией по условию чётности.
 * <p>
 * Интерфейс определяет общий контракт для стратегий сортировки, которые:
 * <ul>
 *   <li>фильтруют студентов по условию, заданному в {@link #isEven(Student)};</li>
 *   <li>сортируют отфильтрованных студентов с помощью компаратора из {@link #getStudentComparator()};</li>
 *   <li>возвращают отсортированных студентов на их исходные позиции в общем списке.</li>
 * </ul>
 */

public interface CustomSortStrategy {

    /**
     * Сортирует студентов, удовлетворяющих условию {@link #isEven(Student)}, и сохраняет их на исходных позициях.
     * Остальные студенты остаются без изменений.
     *
     * @param studentsList список студентов для частичной сортировки (модифицируется)
     * @param strategyActivator объект для запуска сортировки отфильтрованного подмножества
     */

    default void sort(List<Student> studentsList, StrategyActivator strategyActivator) {
        List<Student> studentsEvenLength = new ArrayList<>();
        List<Integer> numberList = new ArrayList<>();

        for (int i = 0; i < studentsList.size(); i++) {
            Student student = studentsList.get(i);
            if (isEven(student)) {
                studentsEvenLength.add(student);
                numberList.add(i);
            }
        }
        strategyActivator.startSort(studentsEvenLength, getStudentComparator());
        for (int i = 0; i < studentsEvenLength.size(); i++) {
            studentsList.set(numberList.get(i), studentsEvenLength.get(i));
        }
    }

    Comparator<Student> getStudentComparator();

    boolean isEven(Student student);
}