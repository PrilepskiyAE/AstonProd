package org.aston.prod.model.exception;


/**
 * Исключение, сигнализирующее о некорректном значении возраста студента.
 * Является непроверяемым исключением ({@link RuntimeException}),
 * поэтому не требует обязательного перехвата в блоках {@code try-catch}.
 * <p>
 * Выбрасывается в случае, если возраст студента выходит за допустимые пределы
 */

public class NoCorrectAge extends RuntimeException {
    public NoCorrectAge(String message) {
        super(message);
    }
}
