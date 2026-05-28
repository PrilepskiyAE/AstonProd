package org.aston.prod.model;

import java.util.Comparator;

/**
 * Набор готовых компараторов для сортировки объектов {@link Student}.
 * Предоставляет статические методы для получения {@link Comparator},
 * которые сортируют студентов по имени, возрасту или номеру группы.
 */

public abstract class StudentComparators {

    /**
     * Возвращает компаратор, сортирующий студентов по имени в лексикографическом порядке.
     *
     * @return {@link Comparator} для сортировки по имени
     */

    public static Comparator<Student> byName(){
        return new Comparator<Student>(){
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
    }

    /**
     * Возвращает компаратор, сортирующий студентов по возрасту в порядке возрастания.
     *
     * @return {@link Comparator} для сортировки по возрасту
     */

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

    /**
     * Возвращает компаратор, сортирующий студентов по номеру группы в порядке возрастания.
     *
     * @return {@link Comparator} для сортировки по номеру группы
     */

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