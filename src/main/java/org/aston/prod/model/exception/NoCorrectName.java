package org.aston.prod.model.exception;

/**
 * Исключение, сигнализирующее о некорректном значении имени студента.
 * Является непроверяемым исключением ({@link RuntimeException}),
 * поэтому не требует обязательного перехвата в блоках {@code try-catch}.
 * <p>
 * Выбрасывается в случаях, когда имя не соответствует бизнес-правилам
 */

public class NoCorrectName extends RuntimeException{
    public NoCorrectName(String message){
            super(message);
    }
}
