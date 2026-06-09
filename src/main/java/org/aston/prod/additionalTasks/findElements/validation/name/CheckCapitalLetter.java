package org.aston.prod.additionalTasks.findElements.validation.name;

import java.util.List;

public class CheckCapitalLetter extends CheckName {
    @Override
    void validName(String value, List<String> errors) {
        if (!Character.isUpperCase(value.charAt(0))) {
            errors.add("Имя должно начинаться с большой буквы");
        }
    }
}
