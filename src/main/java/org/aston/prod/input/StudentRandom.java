package org.aston.prod.input;

import org.aston.prod.model.Student;

import java.util.List;
import java.util.Random;

/**
 * Утилитарный класс для генерации случайных объектов {@link Student}.
 * Содержит методы для создания студента со случайными значениями имени, возраста и группы.
 */

public abstract class StudentRandom {
    private static final Random random = new Random();

    public static void addRandomStudentsInList(List<Student> studentList, int quantity) {
        for (int i = 0; i < quantity; i++) {
            studentList.add(newRandomStudent());
        }
    }

    /**
     * Создаёт экземпляр {@link Student} со случайными данными.
     * <p>
     * Имя генерируется случайным образом (первая буква заглавная, остальные строчные),
     * возраст — в диапазоне от 18 до 60 лет включительно,
     * номер группы — от 1 до 9 включительно.
     *
     * @return новый объект {@link Student} с рандомными полями
     */

    public static Student newRandomStudent() {
        return Student.builder()
                .name(getRandomString())
                .age(random.nextInt(18, 61))
                .group(random.nextInt(1, 10))
                .build();
    }

    /**
     * Генерирует случайную строку для имени студента.
     * Первая буква — заглавная латинская, остальные — строчные латинские.
     * Длина строки — от 3 до 10 символов (первая буква + 2–9 дополнительных).
     *
     * @return случайное имя в виде строки
     */

    private static String getRandomString() {
        return (char) random.nextInt('A', 'Z' + 1) +
                random.ints('a', 'z' + 1)
                        .limit(random.nextInt(2, 10))
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();
    }
}