package org.aston.prod.menu;

import org.aston.prod.model.Student;
import org.aston.prod.input.StudentRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputStudent {

    private static List<Student> students = new ArrayList<>();
    private final static Scanner scanner = new Scanner(System.in);

    /**
     * Ввод с клавиатуры
     *
     * @return возвращает коллекцию студентов
     */
    public static List<Student> inputConsole() {
        System.out.print("Сколько студентов будет: ");
        int size = scanner.nextInt();
        for (int i = 0; i < size; i++) {
            scanner.nextLine();
            System.out.print("\nИмя: ");
            String name = scanner.nextLine();
            System.out.print("\nВозраст: ");
            int age = scanner.nextInt();
            System.out.print("\nНомер группы: ");
            int group = scanner.nextInt();
            students.add(Student.builder().name(name).age(age).group(group).build());
        }
        return students;
    }

    /**
     * Рандомное заполнение студентов
     *
     * @return возвращает коллекцию студентов
     */
    public static List<Student> inputRandom() {
        System.out.println("Сколько студентов будет?");
        int size = scanner.nextInt();
        for (int i = 0; i < size; i++) {
            students.add(StudentRandom.newRandomStudent());
        }
        return students;
    }

    /**
     * Заполнение студентов из файла
     *
     * @return возвращает коллекцию студентов
     */
    public static List<Student> inputFile() {
        students = StudentFile.readFile();
        return students;
    }
}
