package org.aston.prod.additionalTasks.task3;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;

public class CustomCollection<S> implements ICollection<S> {

    private Object[] elements;
    private static final int INITIAL_CAPACITY = 17;
    private int length;

    public CustomCollection() {
        elements = new Object[INITIAL_CAPACITY];
        length = 0;
    }

    /**
     * Длина коллекции
     * @return возвращает длину
     */
    @Override
    public int length() {
        return length;
    }

    /**
     * Пустая коллекция или нет
     * @return возвращает истину, если пустая
     */
    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Добавляет элемент в коллекцию
     * @param element элемент
     * @return
     */
    @Override
    public boolean add(S element) {
        if (length == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            for (int i = 0; i < length; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
        elements[length++] = element;
        return true;
    }

    /**
     * Удаление элемента
     * @param element элемент, который нужно удалить
     * @return возвращает истину, если элемент есть в коллекции
     */
    @Override
    public boolean remove(S element) {
        Object[] newElements = new Object[elements.length - 1];
        int j = 0;
        boolean isFound = false;
        for (int i = 0; i < length; i++) {
            if (element.equals(elements[i])) {
                isFound = true;
                continue;
            }
            newElements[j] = elements[i];
            j++;
        }
        length--;
        elements = newElements;
        return isFound;
    }

    /**
     * Удаляет все элементы
     */
    @Override
    public void removeAll() {
        for (int i = 0; i < length; i++) {
            remove((S) elements[i]);
        }
        length = 0;
    }

    @Override
    public void forEach(Consumer<S> consumer) {
        for (int i = 0; i < length; i++) {
            consumer.accept((S) elements[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("<<<Студенты. Начало>>>\n");
        for (int i = 0; i < length; i++) {
            sb.append(elements[i]);
            if (i < length - 1) {
                sb.append(",\n");
            }
        }
        sb.append("\n<<<Студенты. Конец>>>");
        return sb.toString();
    }

    public static <S> Collector<S, CustomCollection<S>, CustomCollection<S>> customCollector() {
        return Collector.of(
                CustomCollection::new,
                CustomCollection::add,
                (list1, list2) -> {
                    list1.add((S) list2);
                    return list1;
                },
                Function.identity(),
                Collector.Characteristics.UNORDERED
                );
    }
}
