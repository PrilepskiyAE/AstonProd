package org.aston.prod.sort;

import org.aston.prod.model.Student;
import org.aston.prod.model.StudentRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputStudent {

    private static List<Student> students=new ArrayList<>();
    private static Scanner scanner=new Scanner(System.in);

    /**
     * ввод с клавиатуры
     * @return возвращает коллекцию студентов
     */
    public static List<Student> inputConsole() {
        System.out.println("How many students will there be?");
        int size=scanner.nextInt();
        for (int i = 0; i < size; i++) {
            scanner.nextLine();
            System.out.print("\nName: ");
            String name = scanner.nextLine();
            System.out.print("\nAge: ");
            int age = scanner.nextInt();
            System.out.print("\nNumber of group: ");
            int group = scanner.nextInt();
            students.add(Student.builder().name(name).age(age).group(group).build());
        }
        return students;
    }

    /**
     * рандомное заполнение студентов
     * @return возвращает коллекцию студентов
     */
    public static List<Student> inputRandom() {
        System.out.println("How many students will there be?");
        int size=scanner.nextInt();
        for (int i = 0; i < size; i++) {
            students.add(StudentRandom.newRandomStudent());
        }
        return students;
    }

    /**
     * заполнение студентов из файла
     * @return возвращает коллекцию студентов
     */
    public static List<Student> inputFile() {
        students=StudentFile.readFile();
        return students;
    }
}
