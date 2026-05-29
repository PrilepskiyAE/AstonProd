package org.aston.prod;

import org.aston.prod.model.Student;
import org.aston.prod.sort.ChoiceField;
import org.aston.prod.sort.InputStudent;
import org.aston.prod.sort.Print;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true){
            Scanner scanner=new Scanner(System.in);
            List<Student> students=new ArrayList<>();
            System.out.println("Select:\n\t1 Create the students manually\n\t2 Use random data to create students automatically\n\t" +
                    "Press any key to use the date from the file");
            String inputStudent=scanner.nextLine();
            if ("1".equals(inputStudent)) {
                students = InputStudent.inputConsole();
            }
            else if ("2".equals(inputStudent)) {
                students = InputStudent.inputRandom();
            }
            else {
                students=InputStudent.inputFile();
            }
            System.out.println("List of students without sorting:");
            Print.printStudents(students);
            System.out.println("How to sort: by name - 1, by age - 2, by group - another key");
            String inputField=scanner.nextLine();
            if (inputField.equals("1")) {
                ChoiceField.byName(students);
            }
            else if (inputField.equals("2")) {
                ChoiceField.byAge(students);
            }
            else {
                ChoiceField.byGroup(students);
            }
            System.out.print("If you want to finish, press 'e', or any other key: ");
            String inputEnd = scanner.nextLine();
            if (inputEnd.equals("e")) {
                System.out.println("END");
                break;
            }
        }
    }
}
