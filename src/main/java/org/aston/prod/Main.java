package org.aston.prod;

import org.aston.prod.additionalTasks.WriteInFile;
import org.aston.prod.menu.StudentFile;
import org.aston.prod.model.Student;
import org.aston.prod.input.StudentRandom;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        Menu.menu();

        ArrayList<Student> students = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            students.add(StudentRandom.newRandomStudent());
        }

        WriteInFile wf = new WriteInFile();
        wf.writeInFile(students, "students");




    }
}