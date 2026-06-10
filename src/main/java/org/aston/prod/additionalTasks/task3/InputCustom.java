package org.aston.prod.additionalTasks.task3;

import org.aston.prod.input.StudentRandom;
import org.aston.prod.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.aston.prod.additionalTasks.task3.CustomList.customCollector;

public class InputCustom {

    public static void consoleCustom(Scanner scanner) {
        CustomList<Student> customStudent =  new CustomList<>();
        customStudent.stream().map(i -> Student.builder().name("Dgfgf").age(45).group(43).build()).
                collect(customCollector());
        customStudent.stream().map(i -> Student.builder().name("Dgjjjjgf").age(76).group(23).build()).
                forEach(System.out::println);
        for (Student student : customStudent) {
            System.out.println(student);
        }
    }
}
