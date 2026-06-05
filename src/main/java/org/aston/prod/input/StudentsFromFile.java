// пятнецо!     05-06-2026


package org.aston.prod.input;

import org.aston.prod.model.Student;
import tools.jackson.databind.MappingIterator;
import tools.jackson.databind.json.JsonMapper;

/* import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.json.JsonMapper; /**/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для чтения списка студентов из JSONL-файла.
 * Формат файла: каждая строка — JSON-объект Student.
 * Выполняет валидацию полей: имя (только буквы), возраст > 0, группа > 0.
 */
public class StudentsFromFile {

    /**
     * Читает студентов из JSONL-файла, расположенного в папке src/main/resources.
     * Некорректные записи (невалидные поля или ошибка десериализации) пропускаются,
     * о них выводится предупреждение в консоль.
     * Если файл не найден или пуст, выводится сообщение об ошибке и возвращается пустой список.
     *
     * @param fileName имя файла без расширения (расширение .jsonl добавится автоматически)
     * @return список валидных студентов, прочитанных из файла (может быть пустым)
     */
    public static List<Student> readFromFile(String fileName) {
        File file = new File("src/main/resources/" + fileName + ".jsonl");

        // Проверка наличия файла
        if (!file.exists()) {
            System.err.println("Файл не найден: " + file.getPath());
            return new ArrayList<>();
        }

        // Проверка непустоты файла
        if (file.length() == 0) {
            System.err.println("Файл пуст: " + file.getPath());
            return new ArrayList<>();
        }

        List<Student> studentList = new ArrayList<>();
        JsonMapper mapper = JsonMapper.builder().build();

        try (MappingIterator<Student> iterator = mapper.readerFor(Student.class).readValues(file)) {
            int lineNumber = 0;
            while (iterator.hasNext()) {
                lineNumber++;
                try {
                    Student student = iterator.nextValue();
                    if (isValidStudent(student)) {
                        studentList.add(student);
                    } else {
                        System.err.printf("Строка %d: студент пропущен (невалидные данные): %s%n", lineNumber, student);
                    }
                } catch (Exception e) {
                    System.err.printf("Строка %d: ошибка десериализации JSON - %s%n", lineNumber, e.getMessage());
                }
            }
        } /* catch (IOException e) {
            // Ошибка ввода-вывода при чтении файла (например, проблемы с доступом)
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        } */

        return studentList;
    }

    /**
     * Проверяет корректность полей студента:
     * - имя не null, не пустое и состоит только из букв (без цифр и спецсимволов);
     * - возраст > 0;
     * - номер группы > 0.
     *
     * @param student студент для проверки
     * @return true, если все поля валидны, иначе false
     */
    private static boolean isValidStudent(Student student) {
        if (student == null) {
            return false;
        }
        String name = student.getName();
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        // Только буквы (любого алфавита)
        if (!name.matches("^[\\p{L}]+$")) {
            return false;
        }
        int age = student.getAge();
        if (age <= 0) {
            return false;
        }
        int group = student.getGroup();
        if (group <= 0) {
            return false;
        }
        return true;
    }
}