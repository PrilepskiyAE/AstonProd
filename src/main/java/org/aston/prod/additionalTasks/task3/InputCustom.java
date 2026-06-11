package org.aston.prod.additionalTasks.task3;


import org.aston.prod.model.Student;
import java.util.Scanner;
import java.util.stream.Stream;


import static org.aston.prod.additionalTasks.task3.CustomList.customCollector;

public class InputCustom {

    public static void consoleCustom(Scanner scanner) {
        while (true) {
            System.out.println("Сколько студентов введем?");
            String inputSize = scanner.nextLine();
            int size;
            try {
                size = Integer.parseInt(inputSize);
                if (size < 1) {
                    System.out.println("Число не может быть отрицательным или равно 0");
                    continue;
                }
            }catch(Exception e) {
                System.out.println("Введено некорректное значение!");
                continue;
            }
            Student[] students = new Student[size];
            for (int i = 0; i < size; i++) {
                System.out.println("Введите имя (Первая буква большая!)");
                String name = scanner.nextLine();
                System.out.println("Введите возраст");
                String inputAge = scanner.nextLine();
                int age;
                try {
                    age = Integer.parseInt(inputAge);
                    if (age < 10 || age > 100) {
                        System.out.println("Возраст должен быть от 10 до 100 лет");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Введено некорректное значение!");
                    continue;
                }
                System.out.println("Введите группу");
                String inputGroup = scanner.nextLine();
                int group;
                try {
                    group = Integer.parseInt(inputGroup);
                    if (group < 1) {
                        System.out.println("Группа не может быть отрицательным или равна 0");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("Введено некорректное значение!");
                    continue;
                }
                students[i] = Student.builder().name(name).age(age).group(group).build();
            }
            CustomList<Student> customStudent = Stream.of(students).collect(customCollector());
            //System.out.println(customStudent);
            customStudent.forEach(System.out::println);
            break;
        }
    }
}
