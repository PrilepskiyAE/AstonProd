package org.aston.prod.additionalTasks.task3;


import org.aston.prod.model.Student;


import java.util.Scanner;
import java.util.stream.Stream;


import static org.aston.prod.additionalTasks.task3.CustomList.customCollector;

public class InputCustom {

    public static void consoleCustom(Scanner scanner) {
        System.out.println("Сколько студентов введем?");
        String inputSize = scanner.nextLine();
        int size = Integer.parseInt(inputSize);
        Student[] students = new Student[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Введите имя");
            String name = scanner.nextLine();
            System.out.println("Введите возраст");
            String inputAge = scanner.nextLine();
            int age = Integer.parseInt(inputAge);
            System.out.println("Введите группу");
            String inputGroup = scanner.nextLine();
            int group = Integer.parseInt(inputGroup);
            students[i] = Student.builder().name(name).age(age).group(group).build();
        }
        CustomList<Student> customStudent = Stream.of(students).collect(customCollector());
        System.out.println(customStudent);
    }
}
