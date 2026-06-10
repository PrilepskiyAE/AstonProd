package org.aston.prod.additionalTasks.findElements.validation.name;

import java.util.List;


/**
 * Класс проверки имени студента на соответствие критерию: имя должно начинаться с заглавной буквы.
 * Реализует часть цепочки проверок в рамках паттерна «Цепочка обязанностей» (Chain of Responsibility).
 *
 * <p>Проверяет первый символ строки с помощью метода {@link Character#isUpperCase(char)},
 * чтобы убедиться, что имя начинается с заглавной буквы. Если условие не выполняется,
 * в список ошибок добавляется соответствующее сообщение.</p>
 *
 * @see CheckName
 * @see Character#isUpperCase(char)
 */

public class CheckCapitalLetter extends CheckName {

    /**
     * Проверяет, начинается ли имя с заглавной буквы.
     *
     * Алгоритм работы:
     * 1. Получает первый символ строки {@code value} с помощью {@code charAt(0)}.
     * 2. Проверяет, является ли этот символ заглавным, используя {@code Character.isUpperCase()}.
     * 3. Если первый символ не заглавный, добавляет в список {@code errors} сообщение:
     *    «Имя должно начинаться с большой буквы».
     *
     * @param value   имя студента для проверки (предполагается, что строка не пустая и не null)
     * @param errors  список для накопления сообщений об ошибках валидации.
     *              Если имя не соответствует критерию, в список добавляется описание ошибки.
     *
     */

    @Override
    void validName(String value, List<String> errors) {
        if (!Character.isUpperCase(value.charAt(0))) {
            errors.add("Имя должно начинаться с большой буквы");
        }
    }
}
