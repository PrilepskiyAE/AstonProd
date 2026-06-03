package org.aston.prod.menu;

import org.aston.prod.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentFile2 {

    public static List<Student> readFile() {
        File file = new File("students.txt");
        if (!file.exists()) {
            createDefaultFile(file);
        }

        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(";");
                if (parts.length != 3) {
                    System.err.printf("Строка %d: пропущена (неверный формат) - %s%n", lineNumber, line);
                    continue;
                }

                String name = parts[0].trim();
                String ageStr = parts[1].trim();
                String groupStr = parts[2].trim();

                // Проверка имени (без цифр и спецсимволов)
                if (!isValidName(name)) {
                    System.err.printf("Строка %d: пропущена (некорректное имя: \"%s\")%n", lineNumber, name);
                    continue;
                }

                int age;
                try {
                    age = Integer.parseInt(ageStr);
                } catch (NumberFormatException e) {
                    System.err.printf("Строка %d: пропущена (возраст не число) - %s%n", lineNumber, ageStr);
                    continue;
                }
                if (age <= 0) {
                    System.err.printf("Строка %d: пропущена (возраст должен быть >0) - %d%n", lineNumber, age);
                    continue;
                }

                int group;
                try {
                    group = Integer.parseInt(groupStr);
                } catch (NumberFormatException e) {
                    System.err.printf("Строка %d: пропущена (группа не число) - %s%n", lineNumber, groupStr);
                    continue;
                }
                if (group <= 0) {
                    System.err.printf("Строка %d: пропущена (группа должна быть >0) - %d%n", lineNumber, group);
                    continue;
                }

                students.add(Student.builder().name(name).age(age).group(group).build());
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
        return students;
    }

    // Проверка: имя состоит только из букв, пробелов, дефисов, апострофов (без цифр)
    private static boolean isValidName(String name) {
        if (name == null || name.isEmpty()) return false;
        return name.matches("^[\\p{L}\\s\\-']+$");
    }

    private static void createDefaultFile(File file) {
        String[] sample = {
                "Anna;18;234",
                "Mihail;24;235",
                "Evgeny;25;237",
                "Leonid;17;124",
                "Marya;28;238",
                "Oleg;37;129",
                "Alexandr;58;28",
                "Nikolai;47;24",
                "Polina;25;235"
        };
        try (PrintWriter writer = new PrintWriter(file)) {
            for (String line : sample) writer.println(line);
            System.out.println("Создан файл students.txt с тестовыми данными");
        } catch (FileNotFoundException e) {
            System.err.println("Не удалось создать файл students.txt: " + e.getMessage());
        }
    }
}