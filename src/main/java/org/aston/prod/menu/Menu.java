package org.aston.prod.menu;

import org.aston.prod.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void menu() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            List<Student> students = new ArrayList<>();
            System.out.println("""
                    Нажмите 1, если ввод студентов через консоль
                    2 - если рандомный ввод
                    любая другая клавиша - чтение студентов из файла""");
            String inputStudent = scanner.nextLine();
            if ("1".equals(inputStudent)) {
                students = InputStudent.inputConsole();
            } else if ("2".equals(inputStudent)) {
                students = InputStudent.inputRandom();
            } else {
                students = InputStudent.inputFile();
            }
            System.out.println("Вывод студентов без сортировки:");
            Print.printStudents(students);
            System.out.println("Как будем сортировать: 1 - по имени, 2 - по возрасту, " +
                    "любая другая клавиша - по группе?");
            String inputField = scanner.nextLine();
            if (inputField.equals("1")) {
                ChoiceField.byName(students);
            } else if (inputField.equals("2")) {
                ChoiceField.byAge(students);
            } else {
                ChoiceField.byGroup(students);
            }
            System.out.print("Если хотите закончить, нажмите 'e', если продолжаем - любая другая клавиша: ");
            String inputEnd = scanner.nextLine();
            if (inputEnd.equals("e")) {
                System.out.println("ВСЁ");
                break;
            }
        }
    }
}
