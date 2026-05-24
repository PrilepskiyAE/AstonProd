package org.aston.prod.model;

import java.util.Random;

public abstract class StudentRandom {
    private static final Random random = new Random();

    public static Student newRandomStudent() {
        return Student.builder()
                .name(getRandomString())
                .age(random.nextInt(18, 61))
                .group(random.nextInt(1, 10))
                .build();
    }

    private static String getRandomString() {
        return (char) random.nextInt('A', 'Z' + 1) +
                random.ints('a', 'z' + 1)
                        .limit(random.nextInt(2, 10))
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();
    }
}