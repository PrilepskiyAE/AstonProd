package org.aston.prod.input;

import org.aston.prod.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//**************



/**
 * Класс для ручного ввода студентов с консоли.
 * Формат ввода одной строки: Имя;возраст;группа
 * Ввод заканчивается пустой строкой.
 */
public class StudentsFromConsole {

    /**
     * Считывает список студентов из консоли.
     *
     * @param scanner   объект Scanner для чтения ввода
     * @param students   существующий список (может быть null или пустым), результат будет добавлен в новый список
     * @return список валидных студентов, введённых пользователем
     */
    public static List<Student> fromConsole(Scanner scanner, List<Student> students) {
        List<Student> result = new ArrayList<>();
        if (students != null) {
            result.addAll(students);
        }

        System.out.println("Введите данные студентов в формате: имя;возраст;группа");
        System.out.println("После ввода последнего студента оставьте строку пустой и нажмите Enter.");

        int lineNumber = 0;
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                break;
            }

            lineNumber++;
            String[] parts = line.split(";");
            if (parts.length != 3) {
                System.err.printf("Строка %d: неверный формат. Ожидается: имя;возраст;группа%n", lineNumber);
                continue;
            }

            String name = parts[0].trim();
            String ageStr = parts[1].trim();
            String groupStr = parts[2].trim();

            // Валидация имени: только буквы (включая Unicode)
            if (!isValidName(name)) {
                System.err.printf("Строка %d: имя \"%s\" содержит недопустимые символы (только буквы).%n", lineNumber, name);
                continue;
            }

            int age;
            try {
                age = Integer.parseInt(ageStr);
                if (age <= 0) {
                    System.err.printf("Строка %d: возраст должен быть положительным числом (получено %d).%n", lineNumber, age);
                    continue;
                }
            } catch (NumberFormatException e) {
                System.err.printf("Строка %d: возраст \"%s\" не является целым числом.%n", lineNumber, ageStr);
                continue;
            }

            int group;
            try {
                group = Integer.parseInt(groupStr);
                if (group <= 0) {
                    System.err.printf("Строка %d: номер группы должен быть положительным числом (получено %d).%n", lineNumber, group);
                    continue;
                }
            } catch (NumberFormatException e) {
                System.err.printf("Строка %d: номер группы \"%s\" не является целым числом.%n", lineNumber, groupStr);
                continue;
            }

            // Все проверки пройдены — создаём студента и добавляем в список
            Student student = Student.builder()
                    .name(name)
                    .age(age)
                    .group(group)
                    .build();
            result.add(student);
            System.out.println("Студент добавлен.");
        }

        System.out.printf("Всего введено корректных студентов: %d%n", result.size() - (students == null ? 0 : students.size()));
        return result;
    }

    /**
     * Проверяет, что имя состоит только из букв (любого алфавита).
     *
     * @param name имя для проверки
     * @return true, если имя валидно, иначе false
     */
    private static boolean isValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        // \p{L} соответствует любой букве Unicode
        return name.matches("^[\\p{L}]+$");
    }
}