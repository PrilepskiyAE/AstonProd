package org.aston.prod.additionalTasks.findElements.validation.name;

import java.util.List;

public abstract class CheckName {
    private CheckName nextCheckName;

    public CheckName setNextCheckName(CheckName nextCheckName) {
        this.nextCheckName = nextCheckName;
        return nextCheckName;
    }

    public void check(String value, List<String> errors) {
        validName(value, errors);
        if (nextCheckName != null) {
            nextCheckName.check(value, errors);
        }
    }

    abstract void validName(String value, List<String> errors);
}
