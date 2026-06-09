
import org.aston.prod.model.Student;
import org.aston.prod.model.exception.NoCorrectAge;
import org.aston.prod.model.exception.NoCorrectGroup;
import org.aston.prod.model.exception.NoCorrectName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    /**
     * Проверяет создание студента с корректными данными.
     * <p>
     * Создаёт экземпляр {@link Student} через билдер с валидными значениями:
     * имя "Anna", возраст 20 лет, группа 5. Затем проверяет, что все поля объекта
     * соответствуют заданным значениям.
     */

    @Test
    void testStudentCreationWithValidData() {
        Student student = Student.builder()
                .name("Anna")
                .age(20)
                .group(5)
                .build();

        assertEquals("Anna", student.getName());
        assertEquals(20, student.getAge());
        assertEquals(5, student.getGroup());
    }


    /**
     * Проверяет валидацию имени при передаче null.
     * <p>
     * Тестирует поведение билдера {@link Student.StudentBuilder} при попытке установить
     * {@code null} в качестве имени студента. Ожидается, что будет выброшено исключение
     * {@link NoCorrectName}, поскольку имя не может быть пустым.
     */

    @Test
    void testNameValidation_NullName() {
        assertThrows(NoCorrectName.class, () ->
                Student.builder().name(null).age(20).group(5).build());
    }


    /**
     * Проверяет валидацию имени при передаче слишком короткой строки.
     * <p>
     * Тестирует поведение билдера {@link Student.StudentBuilder} при попытке установить
     * имя длиной 2 символа ("An"). Ожидается, что будет выброшено исключение
     * {@link NoCorrectName}, поскольку по бизнес‑правилам имя должно содержать
     * от 3 до 11 символов.
     */

    @Test
    void testNameValidation_ShortName() {
        assertThrows(NoCorrectName.class, () ->
                Student.builder().name("An").age(20).group(5).build());
    }


    /**
     * Проверяет валидацию имени при передаче слишком длинной строки.
     * <p>
     * Тестирует поведение билдера {@link Student.StudentBuilder} при попытке установить
     * имя, превышающее максимально допустимую длину. Используется строка "ThisIsTooLongNameForStudent",
     * которая заведомо длиннее 11 символов. Ожидается, что будет выброшено исключение
     * {@link NoCorrectName}, поскольку по бизнес‑правилам имя должно содержать
     * от 3 до 11 символов.
     */

    @Test
    void testNameValidation_LongName() {
        String longName = "ThisIsTooLongNameForStudent";
        assertThrows(NoCorrectName.class, () ->
                Student.builder().name(longName).age(20).group(5).build());
    }


    /**
     * Проверяет, что имя студента корректно очищается от лишних пробелов.
     * <p>
     * Тестирует поведение билдера {@link Student.StudentBuilder} при передаче имени
     * с ведущими и trailing-пробелами ("  Bob  "). Ожидается, что в итоговом объекте
     * {@link Student} поле name будет содержать строку "Bob" — без лишних пробелов,
     * так как билдер применяет метод {@code trim()} при установке имени.
     */

    @Test
    void testNameTrimming() {
        Student student = Student.builder()
                .name("  Bob  ")
                .age(25)
                .group(3)
                .build();

        assertEquals("Bob", student.getName()); // Пробелы должны быть убраны
    }

    /**
     * Проверяет валидацию возраста при передаче значения ниже допустимого минимума.
     * <p>
     * Тестирует поведение билдера {@link Student.StudentBuilder} при попытке установить
     * возраст 9 лет для студента. Ожидается, что будет выброшено исключение
     * {@link NoCorrectAge}, поскольку по бизнес‑правилам возраст студента должен быть
     * в диапазоне от 10 до 100 лет включительно.
     */

    @Test
    void testAgeValidation_TooYoung() {
        assertThrows(NoCorrectAge.class, () ->
                Student.builder().name("Charlie").age(9).group(2).build());
    }

    /**
     * Проверяет, что при возрасте ниже допустимого минимума выбрасывается исключение.
     * <p>
     * Передаёт значение 9 в метод {@code age()} билдера {@link Student.StudentBuilder} —
     * это меньше минимально разрешённого возраста (10 лет). Ожидается исключение
     * {@link NoCorrectAge}, что подтверждает корректность валидации возраста.
     */

    @Test
    void testAgeValidation_TooOld() {
        assertThrows(NoCorrectAge.class, () ->
                Student.builder().name("David").age(101).group(1).build());
    }


    /**
     * Проверяет валидацию номера группы при передаче нулевого значения.
     * <p>
     * Тестирует поведение билдера {@link Student.StudentBuilder} при попытке установить
     * номер группы равный 0. Ожидается, что будет выброшено исключение
     * {@link NoCorrectGroup}, поскольку по правилам валидации номер группы
     * должен быть положительным числом (минимум 1).
     */

    @Test
    void testGroupValidation_ZeroGroup() {
        assertThrows(NoCorrectGroup.class, () ->
                Student.builder().name("Eve").age(30).group(0).build());
    }

    /**
     * Проверяет валидацию номера группы при передаче отрицательного значения.
     * <p>
     * Тестирует поведение билдера {@link Student.StudentBuilder} при попытке установить
     * номер группы равный -5. Ожидается, что будет выброшено исключение
     * {@link NoCorrectGroup}, поскольку по правилам валидации номер группы
     * должен быть положительным числом (минимум 1).
     */

    @Test
    void testGroupValidation_NegativeGroup() {
        assertThrows(NoCorrectGroup.class, () ->
                Student.builder().name("Frank").age(22).group(-5).build());
    }

    /**
     * Проверяет корректность реализации методов {@link Student#equals(Object)} и {@link Student#hashCode()}.
     * <p>
     * Создаются три экземпляра {@link Student}: два из них (student1 и student2) имеют идентичные поля
     * (имя "Grace", возраст 19, группа 4) и должны быть равны по {@code equals}, а также иметь одинаковый хэш‑код.
     * Третий объект (student3) отличается именем ("Heidi") и должен быть не равен student1.
     * Тест подтверждает согласованность контрактов equals и hashCode.
     */

    @Test
    void testEqualsAndHashCode() {
        Student student1 = Student.builder().name("Grace").age(19).group(4).build();
        Student student2 = Student.builder().name("Grace").age(19).group(4).build();
        Student student3 = Student.builder().name("Heidi").age(19).group(4).build();

        assertEquals(student1, student2);
        assertEquals(student1.hashCode(), student2.hashCode());
        assertNotEquals(student1, student3);
    }


    /**
     * Проверяет корректность реализации метода {@link Student#toString()}.
     * <p>
     * Создаётся экземпляр {@link Student} с именем "Ivan", возрастом 21 и номером группы 7.
     * Ожидается, что метод {@code toString()} вернёт строку строго в формате
     * {@code "Student{name='Ivan', age=21, group=7}"} — с точным совпадением полей и их значений,
     * включая регистр букв и пунктуацию. Тест подтверждает, что строковое представление объекта
     * соответствует ожидаемому шаблону.
     */

    @Test
    void testToString() {
        Student student = Student.builder().name("Ivan").age(21).group(7).build();
        String expected = "Ivan        | 21 лет | группа 7";
        assertEquals(expected, student.toString());
    }

    /**
     * Проверяет сравнение студентов по имени через метод {@link Student#compareTo(Student)}.
     * <p>
     * Создаются два экземпляра {@link Student} с одинаковыми возрастом и номером группы,
     * но разными именами: "Anna" и "Boris". Поскольку "Anna" лексикографически меньше "Boris",
     * ожидается, что {@code studentA.compareTo(studentB)} вернёт отрицательное значение,
     * а {@code studentB.compareTo(studentA)} — положительное. Тест подтверждает корректность
     * сортировки по имени в рамках контракта интерфейса {@link Comparable}.
     */

    @Test
    void testCompareTo_ByName() {
        Student studentA = Student.builder().name("Anna").age(20).group(1).build();
        Student studentB = Student.builder().name("Boris").age(20).group(1).build();

        assertTrue(studentA.compareTo(studentB) < 0);
        assertTrue(studentB.compareTo(studentA) > 0);
    }

    /**
     * Проверяет, что метод {@link Student#compareTo(Student)} корректно сортирует студентов по возрасту.
     * <p>
     * Создаются два объекта {@link Student} с одинаковыми именем ("Same") и номером группы (1),
     * но разным возрастом: 18 и 25 лет. Поскольку метод {@code compareTo()} при совпадении имён и групп
     * должен сравнивать объекты по возрасту, ожидается, что studentA будет считаться меньше studentB.
     * Тест подтверждает: {@code studentA.compareTo(studentB)} возвращает отрицательное значение,
     * а {@code studentB.compareTo(studentA)} — положительное, что соответствует правилам сортировки.
     */

    @Test
    void testCompareTo_ByAge() {
        Student studentA = Student.builder().name("Same").age(18).group(1).build();
        Student studentB = Student.builder().name("Same").age(25).group(1).build();

        assertTrue(studentA.compareTo(studentB) < 0);
        assertTrue(studentB.compareTo(studentA) > 0);
    }

    /**
     * Проверяет сравнение студентов по номеру группы при совпадении имени и возраста.
     * <p>
     * Используются два объекта {@link Student} с идентичными именем ("Same") и возрастом (20),
     * но разными номерами групп (2 и 5). Согласно логике метода {@link Student#compareTo(Student)},
     * когда имя и возраст равны, сравнение должно производиться по полю group. Поскольку 2 < 5,
     * ожидается, что {@code studentA.compareTo(studentB)} вернёт отрицательное значение,
     * а {@code studentB.compareTo(studentA)} — положительное. Тест подтверждает корректность
     * этого поведения.
     */

    @Test
    void testCompareTo_ByGroup() {
        Student studentA = Student.builder().name("Same").age(20).group(2).build();
        Student studentB = Student.builder().name("Same").age(20).group(5).build();

        assertTrue(studentA.compareTo(studentB) < 0);
        assertTrue(studentB.compareTo(studentA) > 0);
    }

    /**
     * Проверяет, что метод {@link Student#compareTo(Student)} возвращает 0 для полностью идентичных объектов.
     * <p>
     * Создаются два объекта {@link Student} с одинаковыми значениями всех полей: имя "Identical",
     * возраст 30, группа 3. Согласно контракту {@link Comparable}, при сравнении равных объектов
     * метод должен возвращать 0. Дополнительно это обеспечивает согласованность с методом {@code equals()}:
     * если {@code equals()} возвращает {@code true}, то {@code compareTo()} обязан возвращать 0.
     * Тест подтверждает корректность реализации контракта Comparable для случая идентичных экземпляров.
     */

    @Test
    void testCompareTo_EqualObjects() {
        Student studentA = Student.builder().name("Identical").age(30).group(3).build();
        Student studentB = Student.builder().name("Identical").age(30).group(3).build();

        assertEquals(0, studentA.compareTo(studentB));
    }


    /**
     * Проверяет создание студента с минимально допустимыми валидными значениями полей.
     * <p>
     * Использует билдер {@link Student.StudentBuilder} для конструирования объекта {@link Student}
     * с граничными значениями: имя "Ann", возраст 10 лет (минимальный разрешённый) и группа 1
     * (минимальный допустимый номер). Тест подтверждает, что билдер корректно обрабатывает
     * граничные значения и все поля инициализируются ожидаемыми данными.
     */

    @Test
    void testBuildWithMinimumValidValues() {
        Student student = Student.builder()
                .name("Ann")
                .age(10)
                .group(1)
                .build();

        assertEquals("Ann", student.getName());
        assertEquals(10, student.getAge());
        assertEquals(1, student.getGroup());
    }


    /**
     * Проверяет создание студента с максимально допустимыми валидными значениями полей.
     * <p>
     * Использует билдер {@link Student.StudentBuilder} для конструирования объекта {@link Student}
     * с предельными значениями: имя "MaximallyL", возраст 100 лет (максимальный разрешённый)
     * и группа 999 (максимально допустимый номер). Тест подтверждает, что билдер корректно
     * обрабатывает граничные значения и все поля инициализируются ожидаемыми данными.
     */

    @Test
    void testBuildWithMaximumValidValues() {
        Student student = Student.builder()
                .name("MaximallyL")
                .age(100)
                .group(999)
                .build();

        assertEquals("MaximallyL", student.getName());
        assertEquals(100, student.getAge());
        assertEquals(999, student.getGroup());
    }
}