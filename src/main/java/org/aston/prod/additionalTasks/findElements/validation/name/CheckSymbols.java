package org.aston.prod.additionalTasks.findElements.validation.name;

import java.util.List;

public class CheckSymbols extends CheckName {
    @Override
    void validName(String value, List<String> errors) {
        if (!(value.matches("^\\p{L}+$"))) {
            errors.add("Имя должно содержать только буквы");
        }
    }
}
