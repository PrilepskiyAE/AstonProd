package sort;

import org.aston.prod.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentMockData {
    List<Student> students = new ArrayList<>();
    Student student1 = Student.builder()
            .name("Иван")
            .age(20)
            .group(101)
            .build();
    Student student2 = Student.builder()
            .name("Анна")
            .age(19)
            .group(102)
            .build();
    Student student3 = Student.builder()
            .name("Пётр")
            .age(25)
            .group(103)
            .build();
    Student student4 = Student.builder()
            .name("Мария")
            .age(18)
            .group(104)
            .build();

    public StudentMockData() {
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
    }

     public  List<Student> getStudents(){
        return students;
     }
}
