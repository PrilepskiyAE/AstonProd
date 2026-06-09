package org.aston.prod.additionalTasks.findElements;

import org.aston.prod.additionalTasks.findElements.validation.Validation;
import org.aston.prod.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

public class ElementFind {

    public void find(List<Student> students, Scanner scanner) {
        int field = field(scanner);
        String value = value(scanner, field);
        int numberThreads = numberThreads(scanner);
        int result;

        if (students.size() < 20) {
            System.out.println("Для маленького списка студентов используются параллельные стримы");
            result = findByParallelStream(students, field, value);
        } else {
            if (numberThreads > 10) {
                System.out.println("Вы и правда хотите создать так много потоков? Давайте ограничимся 10 потоками");
                numberThreads = 10;
            }

            result = findByParameter(students, field, value, numberThreads);
        }
        System.out.println("Количество вхождений элемента в коллекцию = " + result);
    }

    private int findByParallelStream(List<Student> students, int field, String value) {
        Predicate<Student> studentPredicate;
        if (field == 1)
            studentPredicate = s -> s.getName().equals(value);
        else if (field == 2)
            studentPredicate = s -> s.getAge() == Integer.parseInt(value);
        else
            studentPredicate = s -> s.getGroup() == Integer.parseInt(value);

        return (int) students.parallelStream().filter(studentPredicate).count();
    }


    private int findByParameter(List<Student> students, int field, String value, int numberThreads) {
        int result = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(numberThreads);

        int size = (students.size() + numberThreads - 1) / numberThreads;
        List<CompletableFuture<Long>> futures = new ArrayList<>();
        try {
            for (int i = 0; i < numberThreads; i++) {
                int from = i * size;
                int to = Math.min(from + size, students.size());

                List<Student> studentSubList = students.subList(from, to);

                CompletableFuture<Long> future = CompletableFuture.supplyAsync(() ->
                        studentSubList.stream().filter(s -> checkValue(s, field, value)).count(), executorService);

                futures.add(future);
            }
            result = (int) futures.stream().mapToLong(CompletableFuture::join).sum();
        } catch (Exception e) {
            System.out.println("Возникла ошибка в процессе выполнения, попробуйте еще раз");
        } finally {
            executorService.shutdown();
        }
        return result;
    }


    private boolean checkValue(Student student, int field, String value) {
        switch (field) {
            case 1 -> {
                return student.getName().equals(value);
            }
            case 2 -> {
                return student.getAge() == Integer.parseInt(value);
            }
            case 3 -> {
                return student.getGroup() == Integer.parseInt(value);
            }
        }
        return false;
    }

    private int numberThreads(Scanner scanner) {
        while (true) {
            System.out.println("Сколько потоков будем использовать для поиска?");
            String stringThreads = scanner.nextLine();
            if (!stringThreads.matches("\\d+")) {
                System.out.println("Количество потоков должно быть положительным числом");
                continue;
            }
            int numberThreads = Integer.parseInt(stringThreads);
            if (numberThreads < 1) {
                System.out.println("Количество потоков должно быть положительным");
                continue;
            }
            return numberThreads;
        }
    }

    private String value(Scanner sc, int field) {
        Validation validation = new Validation();
        while (true) {
            System.out.println("Введите значение которое будем искать:");
            String value = sc.nextLine();
            if (field == 1) {
                if (!validation.checkName(value.trim()))
                    continue;
            }
            if (field == 2) {
                if (!value.matches("\\d+")) {
                    System.out.println("Возраст должен состоять только из цифр");
                    continue;
                }
                int age = Integer.parseInt(value);
                if (age < 10 || age > 100) {
                    System.out.println("Возраст должен быть от 10 до 100 лет");
                    continue;
                }
            }
            if (field == 3) {
                if (!value.matches("\\d+")) {
                    System.out.println("Группа должна состоять только из цифр");
                    continue;
                }
                int group = Integer.parseInt(value);
                if (group < 1) {
                    System.out.println("Номер группы не может быть отрицательный или равен нулю");
                    continue;
                }
            }
            return value;
        }
    }


    private int field(Scanner scanner) {
        while (true) {
            System.out.println("""
                    По какому полю будем искать?
                    Нажмите "1" Чтобы искать по имени
                    Нажмите "2" Чтобы искать по возрасту
                    Нажмите "3" Чтобы искать по группе""");
            String field = scanner.nextLine();
            if (!field.equals("1") && !field.equals("2") && !field.equals("3")) {
                System.out.println("Вы ввели не корректное поле для поиска, повторите операцию еще раз");
            } else return Integer.parseInt(field);
        }
    }
}
