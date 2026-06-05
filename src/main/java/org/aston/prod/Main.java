package org.aston.prod;

import org.aston.prod.input.StudentsFromConsole;
import org.aston.prod.input.StudentsFromFile;
import org.aston.prod.model.Student;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Menu.menu();

        List<Student> students;
        Scanner sc = new Scanner((System.in));

        students = StudentsFromFile.readFromFile("students1");
        students.add(Student.builder().name("Gff").age(33).group(3).build());
        students.add(Student.builder().name("Gff").age(33).group(3).build());
        students.add(Student.builder().name("Gff").age(33).group(3).build());

        students.forEach(System.out::println);

        StudentsFromConsole.fromConsole(sc, students);

    }
}