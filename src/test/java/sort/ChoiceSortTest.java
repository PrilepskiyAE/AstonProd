package sort;

import org.aston.prod.model.Student;
import org.aston.prod.sort.ChoiceSort;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChoiceSortTest {
    private final ChoiceSort choiceSort = new ChoiceSort();
    StudentMockData mock = new StudentMockData();
    @Test
    void testSortByName_AscendingOrder() {
        // Arrange
        List<Student> students = mock.getStudents();

        Comparator<Student> nameComparator = Comparator.comparing(Student::getName);

        // Act
        choiceSort.sort(students, nameComparator);

        // Assert
        assertEquals("Анна", students.get(0).getName());
        assertEquals("Иван", students.get(1).getName());
        assertEquals("Мария", students.get(2).getName());
        assertEquals("Пётр", students.get(3).getName());
    }
}
