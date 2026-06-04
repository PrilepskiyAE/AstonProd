package org.aston.prod.model.exception;


/**
 * Исключение, сигнализирующее о некорректном значении номера группы студента.
 * Является непроверяемым исключением ({@link RuntimeException}),
 * поэтому не требует обязательного перехвата в блоках {@code try-catch}.
 * <p>
 * Выбрасывается в случае, если номер группы студента не соответствует допустимым значениям
 */

public class NoCorrectGroup extends RuntimeException {
    public NoCorrectGroup(String message) {
        super(message);
    }
}
