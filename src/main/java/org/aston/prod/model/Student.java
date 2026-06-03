package org.aston.prod.model;

import org.aston.prod.model.exception.NoCorrectAge;
import org.aston.prod.model.exception.NoCorrectGroup;
import org.aston.prod.model.exception.NoCorrectName;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

/**
 * Представляет студента с именем, возрастом и номером группы.
 * Класс неизменяемый (immutable), экземпляр создаётся через {@link StudentBuilder}.
 * Реализует {@link Comparable} для сортировки: сначала по имени, затем по возрасту, затем по группе.
 */

@JsonDeserialize(builder = Student.StudentBuilder.class)
public class Student implements Comparable<Student> {
    private final String name;
    private final int age;
    private final int group;

    /**
     * Приватный конструктор. Для создания экземпляра используйте {@link StudentBuilder}.
     *
     * @param name  имя студента
     * @param age   возраст студента
     * @param group номер группы студента
     */

    private Student(String name, int age, int group) {
        this.name = name;
        this.age = age;
        this.group = group;
    }

    /**
     * Возвращает билдер для создания экземпляра {@link Student}.
     *
     * @return новый экземпляр {@link StudentBuilder}
     */

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    /**
     * Сравнивает студентов для упорядочивания.
     * Порядок сравнения: имя (лексикографически), возраст (по возрастанию), группа (по возрастанию).
     *
     * @param o объект {@link Student} для сравнения
     * @return отрицательное число, если этот объект меньше {@code o};
     *         ноль, если объекты равны;
     *         положительное число, если этот объект больше {@code o}
     */

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

    /**
     * Вложенный класс-билдер для построения экземпляра {@link Student}.
     * Позволяет задать имя, возраст и группу, выполняет валидацию при вызове {@link #build()}.
     */

    @JsonPOJOBuilder(withPrefix ="")
    public static class StudentBuilder {
        private String name;
        private int age;
        private int group;

        /**
         * Устанавливает имя студента.
         *
         * @param name имя студента, не должно быть {@code null}
         * @return текущий экземпляр билдера
         * @throws NoCorrectName если {@code name} равен {@code null}
         */

        public StudentBuilder name(String name) {
            if (name == null)
                throw new NoCorrectName("Имя не может быть пустым");
            this.name = name.trim();
            return this;
        }

        /**
         * Устанавливает возраст студента.
         *
         * @param age возраст студента
         * @return текущий экземпляр билдера
         */

        public StudentBuilder age(int age) {
            this.age = age;
            return this;
        }

        /**
         * Устанавливает номер группы студента.
         *
         * @param group номер группы
         * @return текущий экземпляр билдера
         */

        public StudentBuilder group(int group) {
            this.group = group;
            return this;
        }

        /**
         * Создаёт экземпляр {@link Student}, предварительно проверив корректность данных.
         *
         * @return новый объект {@link Student}
         * @throws NoCorrectName  если имя {@code null}, короче 3 или длиннее 11 символов
         * @throws NoCorrectAge   если возраст меньше 10 или больше 100
         * @throws NoCorrectGroup если номер группы меньше или равен 0
         */

        public Student build() {
            if (name == null || name.length() < 3 || name.length() > 11)
                throw new NoCorrectName("Имя должно содержать от 3 до 11 символов и не должно быть пустым");
            if (age < 10 || age > 100)
                throw new NoCorrectAge("Возраст студента должен быть от 10 до 100 лет");
            if (group < 1)
                throw new NoCorrectGroup("Номер группы не может быть отрицательный или равен нулю");
        //
            if (!(name.matches("^[\\p{L}]+$"))) {
                throw new NoCorrectName("Имя должно содержать только символы алфавита");
            }
            if (!Character.isUpperCase(name.charAt(0))) {
                throw new NoCorrectName("Имя должно начинаться с большой буквы");
            }
        //


            return new Student(name, age, group);
        }
    }

    /**
     * Возвращает имя студента.
     *
     * @return имя студента
     */

    public String getName() {
        return name;
    }

    /**
     * Возвращает возраст студента.
     *
     * @return возраст студента
     */

    public int getAge() {
        return age;
    }

    /**
     * Возвращает номер группы студента.
     *
     * @return номер группы
     */

    public int getGroup() {
        return group;
    }

    /**
     * Формирует строковое представление объекта {@link Student}.
     *
     * @return строка вида {@code "Student{name='...', age=..., group=...}"}
     */

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", group=" + group +
                '}';
    }
    /**
     * Сравнивает данный объект с другим на равенство.
     * Два объекта {@link Student} равны, если у них одинаковые имя, возраст и группа.
     *
     * @param o объект для сравнения
     * @return {@code true} если объекты равны, иначе {@code false}
     */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && group == student.group && Objects.equals(name, student.name);
    }

    /**
     * Вычисляет хэш-код объекта на основе имени, возраста и группы.
     *
     * @return хэш-код
     */

    @Override
    public int hashCode() {
        return Objects.hash(name, age, group);
    }
}