package org.aston.prod.menu;

import org.aston.prod.model.Student;
import org.aston.prod.input.StudentRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Утилитарный класс для ввода данных о студентах из различных источников.
 * <p>
 * Предоставляет три способа заполнения коллекции студентов:
 * <ul>
 *   <li>ручной ввод с клавиатуры;</li>
 *   <li>случайное заполнение;</li>
 *   <li>загрузка из файла.</li>
 * </ul>
 * <p>
 * Все методы возвращают одну и ту же коллекцию {@code students}, которая хранится в статическом поле класса.
 */
public class InputStudent {

    private static List<Student> students = new ArrayList<>();
    private final static Scanner scanner = new Scanner(System.in);

    /**
     * Осуществляет интерактивный ввод данных о студентах с клавиатуры.
     * <p>
     * Алгоритм:
     * <ol>
     *   <li>Запрашивает количество студентов;</li>
     *   <li>Для каждого студента запрашивает имя, возраст и номер группы;</li>
     *   <li>Создаёт объекты {@link Student} с помощью билдера;</li>
     *   <li>Добавляет их в общую коллекцию.</li>
     * </ol>
     *
     * @return коллекция студентов, заполненная данными, введёнными с клавиатуры
     *
     * @example
     * <pre>
     * {@code
     * List<Student> students = InputStudent.inputConsole();
     * // Пользователь вводит:
     * // Сколько студентов будет: 2
     * // Имя: Анна
     * // Возраст: 20
     * // Номер группы: 101
     * // Имя: Борис
     * // Возраст: 19
     * // Номер группы: 102
     * }
     * </pre>
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
     * Заполняет коллекцию студентов случайными данными.
     * <p>
     * Использует утилитный класс {@link StudentRandom} для генерации случайных студентов.
     *
     * @return коллекция студентов со случайно сгенерированными данными
     *
     * @example
     * <pre>
     * {@code
     * List<Student> randomStudents = InputStudent.inputRandom();
     * // Создаёт 3 случайных студента с рандомными именами, возрастами и группами
     * }
     * </pre>
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
     * Загружает коллекцию студентов из файла.
     * <p>
     * Использует утилитный класс {@link StudentFile} для чтения данных из файла.
     * Предполагается, что файл имеет предопределённый формат, понятный {@link StudentFile}.
     *
     * @return коллекция студентов, загруженная из файла
     * @throws RuntimeException если произошла ошибка при чтении файла
     *         (например, файл не найден или повреждён)
     *
     * @example
     * <pre>
     * {@code
     * List<Student> fileStudents = InputStudent.inputFile();
     * // Загружает студентов из файла (путь и формат определяются внутри StudentFile)
     * }
     * </pre>
     */
    public static List<Student> inputFile() {
        students = StudentFile2.readFile(); // 2- Евг
        return students;
    }
}
