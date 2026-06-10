package org.aston.prod.additionalTasks.findElements.validation;

import org.aston.prod.additionalTasks.findElements.validation.name.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для валидации имени студента с использованием цепочки проверок.
 * Реализует паттерн «Цепочка обязанностей» (Chain of Responsibility) для последовательной
 * проверки различных критериев корректности имени.
 * <p>
 * При создании экземпляра класса формируется цепочка проверок:
 * 1. {@code CheckLong} — проверка длины имени;
 * 2. {@code CheckCapitalLetter} — проверка наличия заглавной буквы;
 * 3. {@code CheckSymbols} — проверка допустимых символов в имени.
 */

public class Validation {
    private final CheckName checkName;

    /**
     * Конструктор класса Validation. Инициализирует цепочку проверок для валидации имени.
     * Создаёт экземпляр {@code CheckLong}, затем последовательно добавляет к нему
     * следующие проверки: {@code CheckCapitalLetter} и {@code CheckSymbols}.
     */

    public Validation() {
        checkName = new CheckLong();
        checkName.setNextCheckName(new CheckCapitalLetter())
                .setNextCheckName(new CheckSymbols());
    }

    /**
     * Выполняет комплексную валидацию имени студента с помощью цепочки проверок.
     *
     * Алгоритм работы:
     * 1. Проверяет, что имя не является {@code null} и не состоит только из пробелов.
     * 2. Если имя пусто, выводит сообщение об ошибке и запускает цепочку проверок
     *    для пустого имени (код "1").
     * 3. Если имя непустое, выполняет все проверки для реального имени.
     * 4. Собирает все ошибки в список {@code errors}.
     * 5. Если ошибок нет, возвращает {@code true}.
     * 6. Если есть ошибки, выводит их в консоль и возвращает {@code false}.
     *
     * @param name имя студента для проверки
     *
     * @return {@code true}, если имя прошло все проверки и является корректным;
     *         {@code false}, если имя не прошло валидацию (пусто или содержит ошибки)
     */

    public boolean checkName(String name) {
        List<String> errors = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Имя не может быть пустым или состоять только из пробелов");
            checkName.check("1", errors);
            errors.forEach(System.out::println);
            return false;
        }
        checkName.check(name.trim(), errors);
        if (errors.isEmpty()) {
            return true;
        }
        errors.forEach(System.out::println);
        return false;
    }
}
