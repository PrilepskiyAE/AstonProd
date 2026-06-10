package sort;

import org.aston.prod.model.Student;
import org.aston.prod.sort.BubblesSort;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BubblesSortTest extends BaseTest<BubblesSort> {
    public BubblesSortTest() {
        super(BubblesSort.class);
    }

    /**
     * Проверяет сортировку студентов по имени в алфавитном порядке (A→Z).
     */

    @Test
    void testSortByNameAscendingOrder() {
        // Arrange
        List<Student> students = mock.getStudents();

        Comparator<Student> nameComparator = Comparator.comparing(Student::getName);

        // Act
        sort.sort(students, nameComparator);

        // Assert
        assertEquals("Анна", students.get(0).getName());
        assertEquals("Иван", students.get(1).getName());
        assertEquals("Мария", students.get(2).getName());
        assertEquals("Пётр", students.get(3).getName());
    }

    /**
     * Проверяет сортировку студентов по возрасту в порядке возрастания (молодые → старшие).
     */

    @Test
    void testSortByAgeDescendingOrder() {
        // Arrange
        List<Student> students = mock.getStudents();

        Comparator<Student> ageComparator = Comparator.comparing(Student::getAge).reversed();

        // Act
        sort.sort(students, ageComparator);

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
    void testSortByGroupAscendingOrder() {
        // Arrange
        List<Student> students = mock.getStudents();

        Comparator<Student> groupComparator = Comparator.comparing(Student::getGroup);

        // Act
        sort.sort(students, groupComparator);

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
        assertDoesNotThrow(() -> sort.sort(emptyList, nameComparator));
        assertTrue(emptyList.isEmpty());
    }

}
