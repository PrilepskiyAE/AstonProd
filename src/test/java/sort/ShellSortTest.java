package sort;

import org.aston.prod.model.Student;
import org.aston.prod.sort.ShellSort;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShellSortTest extends BaseTest<ShellSort> {

    public ShellSortTest() {
        super(ShellSort.class);
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
        assertEquals("Анна", students.get(0).getName(),
                "Первый студент должен быть Анна — первая по алфавиту");
        assertEquals("Иван", students.get(1).getName(),
                "Второй студент должен быть Иван");
        assertEquals("Мария", students.get(2).getName(),
                "Третий студент должен быть Мария");
        assertEquals("Пётр", students.get(3).getName(),
                "Четвёртый студент должен быть Пётр — последний по алфавиту");
    }

    /**
     * Проверяет сортировку студентов по возрасту в порядке возрастания (молодые → старшие).
     */
    @Test
    void testSortByAgeAscendingOrder() {
        // Arrange
        List<Student> students = mock.getStudents();
        Comparator<Student> ageComparator = Comparator.comparingInt(Student::getAge);

        // Act
        sort.sort(students, ageComparator);

        // Assert
        assertEquals(18, students.get(0).getAge(), "Самый молодой студент — 18 лет");
        assertEquals(19, students.get(1).getAge(), "Следующий по возрасту — 20 лет");
        assertEquals(20, students.get(2).getAge(), "Средний возраст — 22 года");
        assertEquals(25, students.get(3).getAge(), "Самый старший студент — 25 лет");
    }

    /**
     * Проверяет сортировку студентов по номеру группы в порядке возрастания.
     */
    @Test
    void testSortByGroupAscendingOrder() {
        // Arrange
        List<Student> students = mock.getStudents();
        Comparator<Student> groupComparator = Comparator.comparingInt(Student::getGroup);

        // Act
        sort.sort(students, groupComparator);

        // Assert
        assertEquals(101, students.get(0).getGroup(), "Первая группа — 101");
        assertEquals(102, students.get(1).getGroup(), "Вторая группа — 102");
        assertEquals(103, students.get(2).getGroup(), "Третья группа — 103");
        assertEquals(104, students.get(3).getGroup(), "Четвёртая группа — 104");
    }

    /**
     * Проверяет сортировку по имени в обратном алфавитном порядке (Z→A).
     */
    @Test
    void testSortByNameDescendingOrder() {
        // Arrange
        List<Student> students = mock.getStudents();
        Comparator<Student> nameComparator = Comparator.comparing(Student::getName).reversed();

        // Act
        sort.sort(students, nameComparator);

        // Assert
        assertEquals("Пётр", students.get(0).getName(), "Первый — Пётр (последний по алфавиту)");
        assertEquals("Мария", students.get(1).getName(), "Второй — Мария");
        assertEquals("Иван", students.get(2).getName(), "Третий — Иван");
        assertEquals("Анна", students.get(3).getName(), "Последний — Анна (первая по алфавиту)");
    }

    /**
     * Проверяет, что сортировка корректно работает с пустым списком.
     */
    @Test
    void testSortEmptyList() {
        // Arrange
        List<Student> emptyList = List.of();
        Comparator<Student> nameComparator = Comparator.comparing(Student::getName);

        // Act & Assert
        assertDoesNotThrow(() -> sort.sort(emptyList, nameComparator),
                "Сортировка пустого списка не должна вызывать исключений");
        assertTrue(emptyList.isEmpty(), "Пустой список должен остаться пустым после сортировки");
    }

}
