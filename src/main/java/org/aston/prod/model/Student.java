package org.aston.prod.model;

import org.aston.prod.model.exception.NoCorrectAge;
import org.aston.prod.model.exception.NoCorrectGroup;
import org.aston.prod.model.exception.NoCorrectName;

import java.util.Objects;

public class Student implements Comparable<Student> {
    private final String name;
    private final int age;
    private final int group;

    private Student(String name, int age, int group) {
        this.name = name;
        this.age = age;
        this.group = group;
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    @Override
    public int compareTo(Student o) {

        int nameCompare = name.compareTo(o.name);
        if (nameCompare != 0) return nameCompare;

        if (this.age != o.age)
            return this.age > o.age ? 1 : -1;

        if (this.group != o.group)
            return this.group > o.group ? 1 : -1;

        return 0;
    }

    public static class StudentBuilder {
        private String name;
        private int age;
        private int group;

        public StudentBuilder name(String name) {
            if (name == null)
                throw new NoCorrectName("Имя не может быть пустым");
            this.name = name.trim();
            return this;
        }

        public StudentBuilder age(int age) {
            this.age = age;
            return this;
        }

        public StudentBuilder group(int group) {
            this.group = group;
            return this;
        }

        public Student build() {
            if (name == null || name.length() < 3 || name.length() > 11)
                throw new NoCorrectName("Имя должно содержать от 3 до 11 символов и не должно быть пустым");
            if (age < 10 || age > 100)
                throw new NoCorrectAge("Возраст студента должен быть от 10 до 100 лет");
            if (group < 1)
                throw new NoCorrectGroup("Номер группы не может быть отрицательный или равен нулю");
            return new Student(name, age, group);
        }
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", group=" + group +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && group == student.group && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, group);
    }
}