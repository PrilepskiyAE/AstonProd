package org.aston.prod.additionalTasks.customSort;

import org.aston.prod.model.Student;
import org.aston.prod.sort.StrategyActivator;

import java.util.List;

/**
 * Класс для выполнения сортировки студентов с использованием настраиваемой стратегии.
 * <p>
 * Реализует шаблон «Стратегия» ({@code Strategy}): позволяет динамически выбирать алгоритм сортировки.
 * По умолчанию использует стратегию {@link SortByAge} (сортировка по возрасту для студентов с чётным возрастом).
 * <p>
 * Особенности работы:
 * <ul>
 *   <li>Сортирует только элементы, удовлетворяющие условию фильтрации стратегии ({@link CustomSortStrategy#isEven(Student)});</li>
 *   <li>Элементы, не прошедшие фильтрацию, остаются на исходных позициях;</li>
 *   <li>Измеряет время выполнения сортировки в миллисекундах и выводит результат в консоль.</li>
 * </ul>
 */
public class CustomSort {
    private final StrategyActivator strategyActivator;
    private CustomSortStrategy customSortStrategy;
    /**
     * Создаёт экземпляр {@code CustomSort} с активатором стратегии и устанавливает стратегию сортировки по возрасту по умолчанию.
     * <p>
     * По умолчанию используется {@link SortByAge}: сортируются студенты с чётным возрастом, остальные остаются на местах.
     *
     * @param strategyActivator объект, отвечающий за запуск процесса сортировки; не должен быть {@code null}
     *
     * @throws NullPointerException если {@code strategyActivator} равен {@code null}
     */
    public CustomSort(StrategyActivator strategyActivator) {
        this.strategyActivator = strategyActivator;
        customSortStrategy = new SortByAge();
    }
    /**
     * Устанавливает новую стратегию сортировки.
     * <p>
     * Позволяет динамически менять алгоритм сортировки во время выполнения программы.
     * Например, можно переключиться на {@link SortByGroup} или {@link SortByLongName}.
     *
     * @param customSortStrategy новая стратегия сортировки; не должна быть {@code null}
     *
     * @throws NullPointerException если {@code customSortStrategy} равен {@code null}
     */
    public void setCustomSortStrategy(CustomSortStrategy customSortStrategy) {
        this.customSortStrategy = customSortStrategy;
    }
    /**
     * Выполняет сортировку списка студентов с использованием текущей стратегии.
     * <p>
     * Алгоритм:
     * <ol>
     *   <li>Замеряет начальное время;</li>
     *   <li>Передаёт список и активатор в метод {@link CustomSortStrategy#sort(List, StrategyActivator)};</li>
     *   <li>Вычисляет затраченное время в миллисекундах;</li>
     *   <li>Выводит время выполнения в консоль.</li>
     * </ol>
     *
     * @param studentList список студентов для сортировки; модифицируется в процессе выполнения
     *
     * @throws NullPointerException если {@code studentList} равен {@code null}
     *
     * @example
     * <pre>
     * {@code
     * StrategyActivator activator = new StrategyActivator();
     * CustomSort sorter = new CustomSort(activator);
     * sorter.sort(students); // сортировка по возрасту (чётные)
     *
     * sorter.setCustomSortStrategy(new SortByGroup());
     * sorter.sort(students); // сортировка по группе (чётные номера)
     * }
     * </pre>
     */
    public void sort(List<Student> studentList) {
        long time = System.nanoTime();
        customSortStrategy.sort(studentList, strategyActivator);
        time = (System.nanoTime() - time) / 1_000_000;
        System.out.println("Сортировка списка из "+ studentList.size() + " элементов заняла " + time + " мс");
    }
}