package org.aston.prod.menu;

import org.aston.prod.model.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentFile {

    /**
     * Чтение файла
     *
     * @return возвращает коллекцию студентов
     */
    public static List<Student> readFile() {
        List<Student> students = new ArrayList<>();
        /*JsonMapper mapper = new JsonMapper();
        File file = new File("students.txt");
        try(MappingIterator<Student> iterator = mapper.readerFor(Student.class).readValues(file)) {
            while(iterator.hasNext()){
                students.add(iterator.nextValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        try (BufferedReader br = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] studentData = line.split(";");
                String name = studentData[0];
                int age = Integer.parseInt(studentData[1]);
                int group = Integer.parseInt(studentData[2]);
                students.add(Student.builder().name(name).age(age).group(group).build());
            }
        } catch (IOException e) {
            System.err.printf("Ошибка чтения файла. %s%n", e.getMessage());
        }
        return students;
    }
}
