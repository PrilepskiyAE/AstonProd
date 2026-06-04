import org.aston.prod.model.Student;
import org.aston.prod.model.StudentComparators;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentComparatorsTest {

    /**
     * Проверяет, что компаратор {@code StudentComparators.byName()} сортирует студентов в алфавитном порядке.
     * <p>
     * Создаётся список из трёх студентов с именами "Zoe", "Anna" и "Bob". После сортировки через
     *  с компаратором {@code StudentComparators.byName()}
     * ожидается порядок: "Anna", "Bob", "Zoe". Тест подтверждает корректность алфавитной сортировки
     * независимо от других полей объекта (возраст, группа).
     */

    @Test
    void testByName_SortsAlphabetically() {
        Student student1 = Student.builder().name("Zoe").age(20).group(1).build();
        Student student2 = Student.builder().name("Anna").age(25).group(2).build();
        Student student3 = Student.builder().name("Bob").age(19).group(3).build();

        List<Student> students = Arrays.asList(student1, student2, student3);
        Collections.sort(students, StudentComparators.byName());

        assertEquals("Anna", students.get(0).getName());
        assertEquals("Bob", students.get(1).getName());
        assertEquals("Zoe", students.get(2).getName());
    }

    /**
     * Проверяет поведение компаратора {@code StudentComparators.byName()} при одинаковых именах.
     * <p>
     * Создаются два студента с одинаковым именем "Anna", но разными значениями других полей.
     * После сортировки порядок объектов не должен измениться (компаратор не вносит дополнительных
     * критериев сравнения). Тест подтверждает, что компаратор корректно обрабатывает случаи
     * с идентичными именами.
     */

    @Test
    void testByName_HandlesSameNames() {
        Student student1 = Student.builder().name("Anna").age(20).group(1).build();
        Student student2 = Student.builder().name("Anna").age(25).group(2).build();

        List<Student> students = Arrays.asList(student1, student2);
        Collections.sort(students, StudentComparators.byName());

        assertEquals("Anna", students.get(0).getName());
        assertEquals("Anna", students.get(1).getName());
    }

    /**
     * Проверяет, что компаратор {@code StudentComparators.byName()} возвращает отрицательное значение,
     * когда первый студент должен идти раньше второго в алфавитном порядке.
     * <p>
     * Сравниваются студенты с именами "Anna" и "Zara". Поскольку "Anna" лексикографически меньше,
     * результат сравнения должен быть отрицательным. Тест использует {@link Integer#signum(int)}
     * для проверки знака результата.
     */

    @Test
    void testByName_ReturnsNegativeWhenFirstComesBefore() {
        Student student1 = Student.builder().name("Anna").age(20).group(1).build();
        Student student2 = Student.builder().name("Zara").age(25).group(2).build();

        int result = StudentComparators.byName().compare(student1, student2);
        assertEquals(-1, Integer.signum(result));
    }

    /**
     * Проверяет, что компаратор {@code StudentComparators.byName()} возвращает положительное значение,
     * когда первый студент должен идти позже второго в алфавитном порядке.
     * <p>
     * Сравниваются студенты с именами "Zara" и "Anna". Поскольку "Zara" лексикографически больше,
     * результат сравнения должен быть положительным. Тест использует {@link Integer#signum(int)}
     * для проверки знака результата.
     */

    @Test
    void testByName_ReturnsPositiveWhenFirstComesAfter() {
        Student student1 = Student.builder().name("Zara").age(20).group(1).build();
        Student student2 = Student.builder().name("Anna").age(25).group(2).build();

        int result = StudentComparators.byName().compare(student1, student2);
        assertEquals(1, Integer.signum(result));
    }
}
