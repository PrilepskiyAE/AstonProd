package org.aston.prod.model;

import java.util.Comparator;

public abstract class StudentComparators {

    public static Comparator<Student> byName(){
        return new Comparator<Student>(){
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
    }

    public static Comparator<Student> byAge(){
        return new Comparator<Student>(){
            @Override
            public int compare(Student o1, Student o2){
                if (o1.getAge() > o2.getAge()) return 1;
                else if (o1.getAge() < o2.getAge()) return -1;
                return 0;
            }
        };
    }

    public static Comparator<Student> byGroup(){
        return new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getGroup() > o2.getGroup()) return 1;
                else if (o1.getGroup() < o2.getGroup()) return -1;
                return 0;
            }
        };
    }
}