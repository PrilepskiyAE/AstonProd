package org.aston.prod.additionalTasks.customSort;

import org.aston.prod.model.Student;
import org.aston.prod.model.StudentComparators;

import java.util.Comparator;

/**
 * Реализация стратегии сортировки студентов по длине имени с фильтрацией по чётности длины имени.
 * <p>
 * Класс реализует интерфейс {@link CustomSortStrategy} и определяет:
 * <ul>
 *   <li>Компаратор для сортировки студентов по имени — через {@link StudentComparators#byName()};</li>
 *   <li>Условие фильтрации: в сортировке участвуют только студенты, у которых длина имени чётная
 *       ({@code name.length() % 2 == 0}).</li>
 * </ul>
 * <p>
 * Сортируем по длине имени, если длина имени четная, то сортируем если нет, то оставляем на месте
 */
public class SortByLongName implements CustomSortStrategy {
    @Override
    public Comparator<Student> getStudentComparator() {
        return StudentComparators.byName();
    }

    @Override
    public boolean isEven(Student student) {
        return student.getName().length() % 2 == 0;
    }
}