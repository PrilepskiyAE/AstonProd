package org.aston.prod;

import org.aston.prod.additionalTasks.task3.CustomCollection;
import org.aston.prod.input.StudentsFromConsole;
import org.aston.prod.input.StudentsFromFile;
import org.aston.prod.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.aston.prod.additionalTasks.task3.CustomCollection.customCollector;

public class Main {
    public static void main(String[] args) {
//        Menu.menu();

        /*List<Student> students = new ArrayList<>();
        Scanner sc = new Scanner((System.in));

        StudentsFromFile.readFromFile("students3", students);

        students.add(Student.builder().name("Gff").age(33).group(3).build());
        students.add(Student.builder().name("Gff").age(33).group(3).build());
        students.add(Student.builder().name("Gff").age(33).group(3).build());

        students.forEach(System.out::println);

        StudentsFromConsole.fromConsole(sc, students);*/

        CustomCollection<Student> students = Stream.of(Student.builder().name("Dghhgh").age(54).group(34).build(),
                        Student.builder().name("Gdssds").age(56).group(34).build()).
                collect(customCollector());
        //students.forEach(System.out::println);
        //students.remove(Student.builder().name("Gdssds").age(56).group(34).build());
        System.out.println(students);
    }
}