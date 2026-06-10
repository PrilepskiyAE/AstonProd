package org.aston.prod.menu;

import org.aston.prod.additionalTasks.WriteInFile;
import org.aston.prod.additionalTasks.customSort.CustomSort;
import org.aston.prod.additionalTasks.customSort.SortByAge;
import org.aston.prod.additionalTasks.customSort.SortByGroup;
import org.aston.prod.additionalTasks.customSort.SortByLongName;
import org.aston.prod.additionalTasks.findElements.ElementFind;
import org.aston.prod.input.StudentRandom;
import org.aston.prod.input.StudentsFromConsole;
import org.aston.prod.input.StudentsFromFile;
import org.aston.prod.model.Student;
import org.aston.prod.model.StudentComparators;
import org.aston.prod.sort.*;

import java.util.*;

public class TestProgramMenu {

    private static List<Student> studentList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static Comparator<Student> comparator = StudentComparators.byName();
    private static final StrategyActivator strategyActivator = new StrategyActivator();
    private static final CustomSort customSort = new CustomSort(strategyActivator);
    private static final WriteInFile writeInFile = new WriteInFile();
    private static final ElementFind elementFind = new ElementFind();

    public static void startMenu() {
        System.out.println("Добро пожаловать в нашу программу");

        mainWhile:
        while (true) {
            try {
                System.out.println("""
                        Нажмите:
                         1 - создание нового списка
                         2 - добавления студентов в существующий список
                         3 - настройка порядка сортировки
                         4 - выбор вида сортировки
                         5 - сортировка списка
                         6 - дополнительный функционал
                         7 - вывод текущего списка в консоль
                         9 - выход из программы
                        """);
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> studentList = new ArrayList<>();
                    case "2" -> addStudents();
                    case "3" -> comparator = StudentComparators.customComparator(scanner);
                    case "4" -> strategy();
                    case "5" -> {
                        if (studentList.size() < 2)
                            System.out.println("Для сортировки в списке должно быть хоть 2 студента");
                        else {
                            strategyActivator.startSort(studentList, comparator);
                            System.out.println("Теперь список отсортирован");
                        }
                    }
                    case "6" -> additional();
                    case "7" -> {
                        if (studentList.isEmpty()) {
                            System.out.println("Список пустой");
                        } else {
                            System.out.printf("%-11s | %s| № группы \n",
                                    "Имя", "Возраст");
                            studentList.forEach(System.out::println);
                        }
                    }
                    case "9" -> {
                        System.out.println("Спасибо за использование, выполнение программы прекращается");
                        break mainWhile;
                    }
                    default -> noCorrectChoice();
                }
            } catch (NoSuchElementException e) {
                System.out.println("""
                        Можно было просто нажать выход из программы!
                        Спасибо за использование, выполнение программы прекращается
                        """);
                break;
            } catch (Exception e) {
                System.out.println("Что-то пошло не так, попробуйте повторить по новой");
            }
        }
    }

    private static void additional() {

        while (true) {
            if (studentList.isEmpty()) {
                System.out.println("Сперва добавьте студентов в список");
                break;
            }
            System.out.println("""
                    Что будем делать
                    1 - сортировать коллекцию только по четным значениям
                    2 - записывать текущий список в файл
                    3 - Многопоточно посчитаем сколько раз элемент встречается в списке
                    """);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> customSort();
                case "2" -> {
                    System.out.println("""
                            Введите название файла для записи
                            Если файл не существует будет создан новый файл
                            Если файл уже существует, то запись данных будет произведена в конец файла
                            """);
                    String fileName = scanner.nextLine();
                    writeInFile.writeInFile(studentList, fileName);
                }
                case "3" -> elementFind.find(studentList, scanner);
                default -> {
                    noCorrectChoice();
                    continue;
                }
            }
            break;
        }
    }

    private static void customSort() {
        while (true) {
            if (studentList.size() == 1) {
                System.out.println("Список из 1го элемента всегда отсортированный");
                break;
            }
            System.out.println("""
                    Все нечетные значения будут оставаться на месте, а четные меняться местами
                    По какому критерию будем сортировать список?
                    1 - по длине имени
                    2 - по возрасту
                    3 - по группе
                    """);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> customSort.setCustomSortStrategy(new SortByLongName());
                case "2" -> customSort.setCustomSortStrategy(new SortByAge());
                case "3" -> customSort.setCustomSortStrategy(new SortByGroup());
                default -> {
                    noCorrectChoice();
                    continue;
                }
            }
            System.out.println("Начинаю сортировку списка");
            customSort.sort(studentList);
            break;
        }
    }

    private static void strategy() {
        while (true) {
            System.out.println("""
                    Какую сортировку будем использовать?
                    1 - сортировка пузырьком
                    2 - сортировка выбором
                    3 - сортировка вставками
                    4 - Челночная сортировка
                    5 - сортировка Шелла
                    6 - сортировка слиянием
                    7 - сортировка слиянием
                    """);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> strategyActivator.setSortStrategy(new BubblesSort());
                case "2" -> strategyActivator.setSortStrategy(new ChoiceSort());
                case "3" -> strategyActivator.setSortStrategy(new InsertSort());
                case "4" -> strategyActivator.setSortStrategy(new ShuttleSort());
                case "5" -> strategyActivator.setSortStrategy(new ShellSort());
                case "6" -> strategyActivator.setSortStrategy(new MergeSort());
                case "7" -> strategyActivator.setSortStrategy(new QuickSort());
                default -> {
                    noCorrectChoice();
                    continue;
                }
            }
            break;
        }
    }

    private static void addStudents() {
        while (true) {
            System.out.println("""
                    Нажмите
                     1 - для ввода студентов из консоли
                     2 - для чтения из файла
                     3 - для создания случайных студентов
                    """);
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> StudentsFromConsole.fromConsole(scanner, studentList);
                case "2" -> {
                    System.out.println("Введите название файла");
                    String fileName = scanner.nextLine();
                    StudentsFromFile.readFromFile(fileName, studentList);
                }
                case "3" -> {
                    while (true) {
                        System.out.println("Сколько случайных студентов создать?");
                        String value = scanner.nextLine();
                        if (!value.matches("\\d+")) {
                            System.out.println("Количество студентов должно быть положительным числом");
                            continue;
                        }
                        int quantity = Integer.parseInt(value);
                        if (quantity < 1) {
                            System.out.println("Количество студентов должно быть положительным числом больше нуля");
                            continue;
                        }
                        StudentRandom.addRandomStudentsInList(studentList, quantity);
                        break;
                    }
                }
                default -> {
                    noCorrectChoice();
                    continue;
                }
            }
            break;
        }
    }

    private static void noCorrectChoice() {
        System.out.println("Введенное значение не корректно попробуйте еще раз");
    }
}
