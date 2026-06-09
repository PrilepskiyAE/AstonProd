package org.aston.prod.additionalTasks.findElements.validation;

import org.aston.prod.additionalTasks.findElements.validation.name.*;

import java.util.ArrayList;
import java.util.List;

public class Validation {
    private final CheckName checkName;

    public Validation() {
        checkName = new CheckLong();
        checkName.setNextCheckName(new CheckCapitalLetter())
                .setNextCheckName(new CheckSymbols());
    }

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
