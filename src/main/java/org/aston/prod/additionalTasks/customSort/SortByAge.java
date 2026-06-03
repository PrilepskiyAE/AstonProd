package org.aston.prod.additionalTasks.customSort;

import org.aston.prod.model.Student;
import org.aston.prod.model.StudentComparators;

import java.util.Comparator;

/**
 * Реализация стратегии сортировки студентов по возрасту с фильтрацией по чётности возраста.
 * <p>
 * Класс реализует интерфейс {@link CustomSortStrategy} и определяет:
 * <ul>
 *   <li>Компаратор для сортировки студентов по возрастанию возраста — через {@link StudentComparators#byAge()};</li>
 *   <li>Условие фильтрации: в сортировке участвуют только студенты с чётным возрастом ({@code age % 2 == 0}).</li>
 * </ul>
 */

public class SortByAge implements CustomSortStrategy {
    /**
     * Возвращает компаратор для сортировки студентов по возрастанию возраста.
     * <p>
     * Использует готовую реализацию из утилиты {@link StudentComparators}.
     * Определяет порядок сортировки.
     *
     * @return компаратор {@code Comparator<Student>}, сортирующий студентов по полю {@code age} по возрастанию
     */
    @Override
    public Comparator<Student> getStudentComparator() {
        return StudentComparators.byAge();
    }

    /**
     * Проверяет, является ли возраст студента чётным.
     *
     * @param student объект {@link Student}, чей возраст проверяется
     * @return {@code true}, если {@code student.getAge() % 2 == 0}; {@code false} в противном случае
     *
     * @example
     * <pre>
     * {@code
     * Student s1 = new Student("Alice", 20); // isEven(s1) → true
     * Student s2 = new Student("Bob", 21);   // isEven(s2) → false
     * }
     * </pre>
     */

    @Override
    public boolean isEven(Student student) {
        return student.getAge() % 2 == 0;
    }
}