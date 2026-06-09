package org.aston.prod.additionalTasks.findElements.validation.name;

import java.util.List;

public class CheckLong extends CheckName{
    @Override
    void validName(String value, List<String> errors) {
        int length = value.length();
        if (length < 3 || length > 11) {
            errors.add("Имя должно содержать от 3 до 11 символов");
        }
    }
}
