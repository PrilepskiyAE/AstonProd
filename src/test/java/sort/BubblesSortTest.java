package sort;

import org.aston.prod.model.Student;
import org.aston.prod.sort.BubblesSort;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BubblesSortTest {

    private final BubblesSort bubblesSort = new BubblesSort();

    StudentMockData mock = new StudentMockData();

    /**
     * Тест сортировки студентов по имени в алфавитном порядке (по возрастанию) с использованием.
     * <p>
     * Проверяет, что после сортировки список студентов упорядочен по полю {@link Student#getName()}
     * в лексикографическом порядке. Для сравнения применяется
     * <p>
     * Ожидаемый результат: имена в отсортированном списке должны следовать в порядке — «Анна», «Иван»,
     * «Мария», «Пётр».
     *
     */

    @Test
    void testSortByName_AscendingOrder() {
        // Arrange
        List<Student> students = mock.getStudents();

        Comparator<Student> nameComparator = Comparator.comparing(Student::getName);

        // Act
        bubblesSort.sort(students, nameComparator);

        // Assert
        assertEquals("Анна", students.get(0).getName());
        assertEquals("Иван", students.get(1).getName());
        assertEquals("Мария", students.get(2).getName());
        assertEquals("Пётр", students.get(3).getName());
    }

    /**
     * Тест сортировки студентов по возрасту в порядке убывания (от старшего к младшему)
     * с использованием {@link BubblesSort}.
     * <p>
     * Проверяет, что после сортировки список студентов упорядочен по полю {@link Student#getAge()}
     * в обратном порядке (по убыванию). Для этого используется компаратор
     *  созданный на основе {@link Student#getAge()},
     * с применением метода {@link Comparator#reversed()} для инвертирования порядка сортировки.
     * <p>
     * Ожидаемый результат: возрасты в отсортированном списке должны следовать в порядке — 25, 20, 19, 18.
     *
     */

    @Test
    void testSortByAge_DescendingOrder() {
        // Arrange
        List<Student> students = mock.getStudents();

        Comparator<Student> ageComparator = Comparator.comparing(Student::getAge).reversed();

        // Act
        bubblesSort.sort(students, ageComparator);

        // Assert
        assertEquals(25, students.get(0).getAge());
        assertEquals(20, students.get(1).getAge());
        assertEquals(19, students.get(2).getAge());
        assertEquals(18, students.get(3).getAge());
    }
    /**
     * Тест сортировки студентов по номеру группы в порядке возрастания с использованием {@link BubblesSort}.
     * <p>
     * Проверяет, что после вызова метода {@link BubblesSort#sort(List, Comparator)} список студентов
     * упорядочен по полю {@link Student#getGroup()} в возрастающем порядке (от меньшего номера группы к большему).
     * Для сравнения используется компаратор,
     * созданный на основе метода {@link Student#getGroup()}.
     * <p>
     * Ожидаемый результат: номера групп в отсортированном списке должны следовать в порядке — 101, 102, 103, 104.
     *
     */
    @Test
    void testSortByGroup_AscendingOrder() {
        // Arrange
        List<Student> students = mock.getStudents();

        Comparator<Student> groupComparator = Comparator.comparing(Student::getGroup);

        // Act
        bubblesSort.sort(students, groupComparator);

        // Assert
        assertEquals(101, students.get(0).getGroup());
        assertEquals(102, students.get(1).getGroup());
        assertEquals(103, students.get(2).getGroup());
        assertEquals(104, students.get(3).getGroup());
    }
    /**
     * Тест обработки пустой коллекции студентов в алгоритме сортировки {@link BubblesSort}.
     * <p>
     * Проверяет, что метод {@link BubblesSort#sort(List, Comparator)} корректно обрабатывает
     * пустой список без выброса исключений и не изменяет состояние коллекции.
     */

    @Test
    void testSortEmptyList() {
        // Arrange
        List<Student> emptyList = new ArrayList<>();
        Comparator<Student> nameComparator = Comparator.comparing(Student::getName);

        // Act & Assert
        assertDoesNotThrow(() -> bubblesSort.sort(emptyList, nameComparator));
        assertTrue(emptyList.isEmpty());
    }

}
