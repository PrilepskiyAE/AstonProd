package org.aston.prod.menu;

import org.aston.prod.input. * ;
import org.aston.prod.sort. * ;
import java.util. * ;

import org.aston.prod.model.Student;
import org.aston.prod.model.StudentComparators;
/**
* Класс, реализующий интерактивное меню для работы со студентами.
* <p>
* Предоставляет консольный интерфейс для:
* <ul>
*   <li>Выбора способа ввода данных о студентах;</li>
*   <li>Просмотра несортированного списка;</li>
*   <li>Выбора критерия сортировки;</li>
*   <li>Повторения цикла работы или завершения программы.</li>
* </ul>
* <p>
* Цикл работы повторяется до тех пор, пока пользователь не введёт 'e' для завершения.
*/
public class Menu {

  public static void menu() {
    System.out.println("""
                  Приветствуем Вас!""");
    Scanner scanner = new Scanner(System. in );
    List < Student > students = new ArrayList < >();

    StrategyActivator sortStrategy = new StrategyActivator();
    while (true) {

      System.out.println("""
                   Нажмите 1, если ввод студентов через консоль
                   2 - если рандомный ввод
                   любая другая клавиша - чтение студентов из файла""");
      String inputStudent = scanner.nextLine();
      if ("1".equals(inputStudent)) {
        StudentsFromConsole.fromConsole(scanner, students);

      } else if ("2".equals(inputStudent)) {
        System.out.println("""
                   Введите количество студентов, которые надо создать случайным образом:          
                           """);
        Integer students_count = Integer.parseInt(scanner.nextLine());
        StudentRandom.addRandomStudentsInList(students, students_count);
      } else {
        System.out.println("""
                    Введите имф файла:""");
        String filename = scanner.nextLine();
        StudentsFromFile.readFromFile(filename, students);
      }
      System.out.printf("Список студентов состоит из %d студентов", students.size());

      Comparator < Student > comparator = StudentComparators.customComparator(scanner);

      System.out.println("""
                   Выберите алгоритм сортировки:
                1 - BubbleSort
                2 - ChoiceSort
                3 - InsertSort
                4 - MergeSort
                5 - QuickSort
                6 - ShellSort
                7 - ShuttleSort

            """);
      String algoIndex = scanner.nextLine();
      if ("1".equals(algoIndex)) {
        sortStrategy.setSortStrategy(new BubblesSort());
      } else if ("2".equals(algoIndex)) {
        sortStrategy.setSortStrategy(new ChoiceSort());
      } else if ("3".equals(algoIndex)) {
        sortStrategy.setSortStrategy(new InsertSort());
      } else if ("4".equals(algoIndex)) {
        sortStrategy.setSortStrategy(new MergeSort());
      } else if ("5".equals(algoIndex)) {
        sortStrategy.setSortStrategy(new QuickSort());
      } else if ("6".equals(algoIndex)) {
        sortStrategy.setSortStrategy(new ShellSort());
      } else if ("7".equals(algoIndex)) {
        sortStrategy.setSortStrategy(new ShuttleSort());
      }

      sortStrategy.startSort(students, comparator);

      System.out.print("Список студентов отсортирован (см. ниже)!");
      for (Student student: students) {
        System.out.println(student);
      }
      System.out.print("Список студентов отсортирован!");
      System.out.print("Если хотите закончить, нажмите 'e', если продолжаем - любая другая клавиша: ");
      String inputEnd = scanner.nextLine();
      if (inputEnd.equals("e")) {
        System.out.println("ВСЁ");
        break;
      }
    }
  }
}