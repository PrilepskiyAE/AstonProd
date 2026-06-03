package org.aston.prod.additionalTasks.customSort;

import org.aston.prod.model.Student;
import org.aston.prod.model.StudentComparators;

import java.util.Comparator;
/**
 * Реализация стратегии сортировки студентов по группе с фильтрацией по чётности номера группы.
 * <p>
 * Класс реализует интерфейс {@link CustomSortStrategy} и определяет:
 * <ul>
 *   <li>Компаратор для сортировки студентов по номеру группы — через {@link StudentComparators#byGroup()};</li>
 *   <li>Условие фильтрации: в сортировке участвуют только студенты из групп с чётным номером ({@code group % 2 == 0}).</li>
 * </ul>
 */
public class SortByGroup implements CustomSortStrategy {

    /**
     * Возвращает компаратор для сортировки студентов по номеру группы.
     * <p>
     * Использует готовую реализацию из утилиты {@link StudentComparators}.
     *
     * @return компаратор {@code Comparator<Student>}, сортирующий студентов по полю {@code group}
     *         (обычно по возрастанию номера группы)
     */

    @Override
    public Comparator<Student> getStudentComparator() {
        return StudentComparators.byGroup();
    }

    /**
     * Проверяет, является ли номер группы студента чётным.
     * @param student объект {@link Student}, номер группы которого проверяется
     * @return {@code true}, если {@code student.getGroup() % 2 == 0}; {@code false} в противном случае
     *
     * @example
     * <pre>
     * {@code
     * Student s1 = new Student("Alice", "Group10"); // isEven(s1) → true (если group=10)
     * Student s2 = new Student("Bob", "Group11");   // isEven(s2) → false (если group=11)
     * }
     * </pre>
     */

    @Override
    public boolean isEven(Student student) {
        return student.getGroup() % 2 == 0;
    }
}
