package org.aston.prod.input;

import org.aston.prod.model.Student;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.MappingIterator;
import tools.jackson.databind.json.JsonMapper;

import java.io.File;
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
     */
    public static void readFromFile(String fileName, List<Student> studentList) {
        File file = new File("src/main/resources/" + fileName + ".jsonl");

        // Проверка наличия файла
        if (!file.exists()) {
            System.out.println("Файл не найден: " + file.getPath());
            return;
        }

        // Проверка не пустоты файла
        if (file.length() == 0) {
            System.out.println("Файл пуст: " + file.getPath());
            return;
        }

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
                        System.out.printf("Строка %d: студент пропущен (невалидные данные): %s%n", lineNumber, student);
                    }
                } catch (JacksonException e) {
                    String message = e.getCause().getMessage() != null ? e.getCause().getMessage() : e.getOriginalMessage();
                    System.out.printf("Строка %d: ошибка десериализации JSON - %s%n", lineNumber, message);
                }
            }
        }
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
        return group > 0;
    }
}