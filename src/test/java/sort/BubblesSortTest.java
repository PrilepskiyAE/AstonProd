package sort;

import io.qameta.allure.*;
import org.aston.prod.model.Student;
import org.aston.prod.sort.BubblesSort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@Epic("Тесты класса BubblesSort")
class BubblesSortTest extends BaseTest<BubblesSort> {
    public BubblesSortTest() {
        super(BubblesSort.class);
    }

    /**
     * Проверяет сортировку студентов по имени в алфавитном порядке (A→Z).
     */
    @DisplayName("Сортировка студентов по имени в алфавитном порядке (A→Z)")
    @Description("Проверяет сортировку студентов по имени в алфавитном порядке (A→Z).")
    @Severity(SeverityLevel.MINOR)
    @Owner("AQA Engineer Prilepskiy Alexey")
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

    @DisplayName("Сортировка студентов по возрасту в порядке возрастания (молодые → старшие).")
    @Description("Проверяет сортировку студентов по возрасту в порядке возрастания (молодые → старшие).")
    @Severity(SeverityLevel.MINOR)
    @Owner("AQA Engineer Prilepskiy Alexey")
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
     * Тест сортировки студентов по номеру группы в порядке возрастания.
     */
    @DisplayName("Сортировка студентов по возрасту в порядке возрастания (молодые → старшие).")
    @Description("Проверяет сортировку студентов по возрасту в порядке возрастания (молодые → старшие).")
    @Severity(SeverityLevel.MINOR)
    @Owner("AQA Engineer Prilepskiy Alexey")
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
     * Тест обработки пустой коллекции студентов в алгоритме сортировки.
     */
    @DisplayName("Обработка пустой коллекции студентов в алгоритме сортировки")
    @Description("Тест обработки пустой коллекции студентов в алгоритме сортировки.")
    @Severity(SeverityLevel.MINOR)
    @Owner("AQA Engineer Prilepskiy Alexey")
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
