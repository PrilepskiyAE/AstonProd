package org.aston.prod.input;

import org.aston.prod.model.Student;
import org.aston.prod.model.exception.NoCorrectAge;
import org.aston.prod.model.exception.NoCorrectGroup;
import org.aston.prod.model.exception.NoCorrectName;

import java.util.List;
import java.util.Scanner;

/**
 * Класс для ручного ввода студентов с консоли.
 * Формат ввода одной строки: Имя;возраст;группа
 * Ввод заканчивается пустой строкой.
 *
 */
public class StudentsFromConsole {

    /**
     * Считывает список студентов из консоли и добавляет их в переданный список.
     *
     * @param scanner  объект Scanner для чтения ввода
     * @param students список, в который будут добавлены новые студенты (не должен быть null)
     * @throws IllegalArgumentException если передан null вместо списка
     */
    public static void fromConsole(Scanner scanner, List<Student> students) {

        // Запоминаем исходный размер, чтобы потом подсчитать количество новых студентов
        int initialSize = students.size();

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
                System.out.printf("Строка %d: неверный формат. Ожидается: имя;возраст;группа%n", lineNumber);
                continue;
            }

            String name = parts[0].trim();
            String ageStr = parts[1].trim();
            String groupStr = parts[2].trim();

            int age;
            try {
                age = Integer.parseInt(ageStr);
            } catch (NumberFormatException e) {
                System.out.printf("Строка %d: возраст \"%s\" не является целым числом.%n", lineNumber, ageStr);
                continue;
            }

            int group;
            try {
                group = Integer.parseInt(groupStr);
            } catch (NumberFormatException e) {
                System.out.printf("Строка %d: номер группы \"%s\" не является целым числом.%n", lineNumber, groupStr);
                continue;
            }

            // Все проверки пройдены — создаём студента и добавляем в список
            try {
                Student student = Student.builder()
                        .name(name)
                        .age(age)
                        .group(group)
                        .build();
                students.add(student);
            } catch (NoCorrectName | NoCorrectAge | NoCorrectGroup e) {
                System.out.printf("Строка %d: ошибка создания нового студента. %s%n", lineNumber, e.getMessage());
                continue;
            }
            System.out.println("Студент добавлен.");
        }

        // Подсчитываем количество новых добавленных студентов
        int addedCount = students.size() - initialSize;
        System.out.printf("Всего введено корректных студентов: %d%n", addedCount);
    }
}