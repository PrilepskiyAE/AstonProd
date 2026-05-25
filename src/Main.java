import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }

       /* Scanner scanner=new Scanner(System.in);
        List<Student> students=new ArrayList<Student>();
        while (true){
            System.out.print("\nИмя студента: ");
            String name=scanner.nextLine();
            System.out.print("\nВозраст: ");
            int age =scanner.nextInt();
            System.out.print("\nНомер группы: ");
            int group=scanner.nextInt();
            System.out.print("\nСредний балл: ");
            int averageScore=scanner.nextInt();
            System.out.print("\nНомер зачетной книжки: ");
            int numberReportCard=scanner.nextInt();
            scanner.nextLine();
            students.add(new Student(name,age,group,averageScore,numberReportCard));
            System.out.print("\nХотите закончить пишем e, если нет любую другую клавишу: ");
            String input=scanner.nextLine();
            if(input.equals("e")){
                break;
            }
        }
        System.out.println("список студентов");
        Print.print(students);
        System.out.println("отсортируем по группе");*/

    }
}