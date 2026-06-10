import io.qameta.allure.*;
import org.aston.prod.model.Student;
import org.aston.prod.input.StudentRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
@Epic("Тесты класса StudentRandom")
class StudentRandomTest {

    /**
     * Проверяет работу приватного метода {@code newRandomStudent()} через рефлексию.
     * <p>
     * С помощью рефлексии вызывается приватный метод {@code newRandomStudent()},
     * и проверяет что метод создаёт валидного студента.
     * <p>
     * Генерируется один случайный студент. Тест верифицирует:
     * <ul>
     *   <li>имя не равно null и имеет длину от 3 до 11 символов;</li>
     *   <li>первая буква имени заглавная, остальные — строчные;</li>
     *   <li>возраст находится в диапазоне [18, 60];</li>
     *   <li>номер группы находится в диапазоне [1, 9].</li>
     * </ul>
     */
    @DisplayName("Корректность работы приватного метода newRandomStudent() через рефлексию.")
    @Description("Проверяет работу приватного метода {@code newRandomStudent()} через рефлексию.")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("AQA Engineer Prilepskiy Alexey")
    @Test
    void testNewRandomStudentCreatesValidStudent() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = StudentRandom.class.getDeclaredMethod("newRandomStudent");
        method.setAccessible(true); // Разрешаем доступ к приватному методу

        Student student =(Student) method.invoke(null);

        assertNotNull(student.getName());
        assertTrue(student.getName().length() >= 3 && student.getName().length() <= 11);

        char firstChar = student.getName().charAt(0);
        assertTrue(Character.isUpperCase(firstChar));

        for (int i = 1; i < student.getName().length(); i++) {
            assertTrue(Character.isLowerCase(student.getName().charAt(i)));
        }

        assertTrue(student.getAge() >= 18 && student.getAge() <= 60);
        assertTrue(student.getGroup() >= 1 && student.getGroup() <= 9);
    }

    /**
     * Проверяет, что многократный вызов приватного метода {@code newRandomStudent()} генерирует разные объекты.
     * <p>
     * Создаётся массив из 10 случайных студентов. Тест убеждается, что не все объекты равны между собой
     * (т. е. генератор действительно выдаёт вариативные данные).
     */
    @DisplayName("Корректность работы приватного метода newRandomStudent() через рефлексию.")
    @Description("Проверяет работу приватного метода newRandomStudent() через рефлексию.")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("AQA Engineer Prilepskiy Alexey")
    @Test
    void testMultipleRandomStudentsAreDifferent() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = StudentRandom.class.getDeclaredMethod("newRandomStudent");
        method.setAccessible(true); // Разрешаем доступ к приватному методу

        Student[] students = new Student[10];
        for (int i = 0; i < students.length; i++) {
            students[i] = (Student) method.invoke(null);
        }

        boolean allSame = true;
        for (int i = 1; i < students.length; i++) {
            if (!students[0].equals(students[i])) {
                allSame = false;
                break;
            }
        }
        assertFalse(allSame, "Случайные студенты должны отличаться");
    }

    /**
     * Проверяет, что первая буква имени случайного студента всегда заглавная.
     * <p>
     * Выполняется 100 итераций генерации студента. На каждой итерации проверяется, что первый символ
     * поля name — заглавный. Тест подтверждает стабильность соблюдения правила форматирования.
     */
    @DisplayName("Корректность работы что первая буква имени случайного студента всегда заглавная.")
    @Description("Проверяет, что первая буква имени случайного студента всегда заглавная.")
    @Severity(SeverityLevel.MINOR)
    @Owner("AQA Engineer Prilepskiy Alexey")
    @Test
    void testNameFormat_FirstLetterCapitalized() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = StudentRandom.class.getDeclaredMethod("newRandomStudent");
        method.setAccessible(true); // Разрешаем доступ к приватному методу
        for (int i = 0; i < 100; i++) {
            Student student = (Student) method.invoke(null);
            char firstChar = student.getName().charAt(0);
            assertTrue(Character.isUpperCase(firstChar));
        }
    }

    /**
     * Проверяет, что все буквы имени, кроме первой, являются строчными.
     * <p>
     * Выполняется 100 итераций генерации студента. Для каждого имени проверяется, что символы
     * со второго и далее — строчные. Тест гарантирует соблюдение формата написания имени.
     */
    @DisplayName("Корректность работы что все буквы имени, кроме первой, являются строчными.")
    @Description("Проверяет, что все буквы имени, кроме первой, являются строчными.")
    @Severity(SeverityLevel.TRIVIAL)
    @Owner("AQA Engineer Prilepskiy Alexey")
    @Test
    void testNameFormat_OtherLettersLowercase() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = StudentRandom.class.getDeclaredMethod("newRandomStudent");
        method.setAccessible(true); // Разрешаем доступ к приватному методу
        for (int i = 0; i < 100; i++) {
            Student student = (Student) method.invoke(null);
            String name = student.getName();
            for (int j = 1; j < name.length(); j++) {
                assertTrue(Character.isLowerCase(name.charAt(j)));
            }
        }
    }

    /**
     * Проверяет, что возраст случайного студента попадает в допустимый диапазон.
     * <p>
     * Выполняется 100 итераций генерации студента. На каждой итерации подтверждается, что значение
     * {@code age} находится в интервале [18, 60]. Тест верифицирует корректность генерации возраста.
     */

    @DisplayName("Корректность работы что что возраст случайного студента попадает в допустимый диапазон.")
    @Description("Проверяет, что возраст случайного студента попадает в допустимый диапазон.")
    @Severity(SeverityLevel.TRIVIAL)
    @Owner("AQA Engineer Prilepskiy Alexey")
    @Test
    void testAgeRange() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = StudentRandom.class.getDeclaredMethod("newRandomStudent");
        method.setAccessible(true); // Разрешаем доступ к приватному методу
        for (int i = 0; i < 100; i++) {
            Student student = (Student) method.invoke(null);
            int age = student.getAge();
            assertTrue(age >= 18 && age <= 60);
        }
    }

    /**
     * Проверяет, что номер группы случайного студента попадает в допустимый диапазон.
     * <p>
     * Выполняется 100 итераций генерации студента. На каждой итерации подтверждается, что значение
     * {@code group} находится в интервале [1, 9]. Тест верифицирует корректность генерации номера группы.
     */

    @DisplayName("Корректность работы что номер группы случайного студента попадает в допустимый диапазон.")
    @Description("Проверяет, что номер группы случайного студента попадает в допустимый диапазон.")
    @Severity(SeverityLevel.TRIVIAL)
    @Owner("AQA Engineer Prilepskiy Alexey")
    @Test
    void testGroupRange() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = StudentRandom.class.getDeclaredMethod("newRandomStudent");
        method.setAccessible(true); // Разрешаем доступ к приватному методу
        for (int i = 0; i < 100; i++) {
            Student student = (Student) method.invoke(null);
            int group = student.getGroup();
            assertTrue(group >= 1 && group <= 9);
        }
    }

    /**
     * Проверяет работу приватного метода {@code getRandomString()} через рефлексию.
     * <p>
     * С помощью рефлексии вызывается приватный метод {@code getRandomString()}, который используется
     * внутри {@code StudentRandom} для генерации имён. Выполняется 100 вызовов метода.
     * Тест верифицирует, что возвращаемая строка:
     * <ul>
     *   <li>не равна null;</li>
     *   <li>имеет длину от 3 до 10 символов;</li>
     *   <li>начинается с заглавной буквы;</li>
     *   <li>остальные символы — строчные.</li>
     * </ul>
     * @throws NoSuchMethodException если метод не найден
     * @throws IllegalAccessException если доступ к методу запрещён
     * @throws InvocationTargetException если при вызове метода возникла ошибка
     */

    @DisplayName("Корректность работы приватного метода getRandomString() через рефлексию.")
    @Description("Проверяет работу приватного метода getRandomString() через рефлексию.")
    @Severity(SeverityLevel.TRIVIAL)
    @Owner("AQA Engineer Prilepskiy Alexey")
    @Test
    void testGetRandomStringPrivateMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = StudentRandom.class.getDeclaredMethod("getRandomString");
        method.setAccessible(true); // Разрешаем доступ к приватному методу

        for (int i = 0; i < 100; i++) {
            String name = (String) method.invoke(null);
            assertNotNull(name);
            assertTrue(name.length() >= 3 && name.length() <= 10);

            char firstChar = name.charAt(0);
            assertTrue(Character.isUpperCase(firstChar));

            for (int j = 1; j < name.length(); j++) {
                assertTrue(Character.isLowerCase(name.charAt(j)));
            }
        }
    }
}

