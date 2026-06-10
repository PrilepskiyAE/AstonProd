package org.aston.prod.additionalTasks.task3;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;

public class CustomList<S> implements List<S> {
    List<S> internalList = new ArrayList<>();
    private Object[] elements;
    private static final int INITIAL_CAPACITY = 17;
    private int size;

    public CustomList() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Длина списка
     * @return возвращает длину
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Пустой список или нет
     * @return возвращает истину, если пустой
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return internalList.contains(o);
    }

    @Override
    public Iterator<S> iterator() {
        return internalList.iterator();
    }

    @Override
    public Object[] toArray() {
        return internalList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return internalList.toArray(a);
    }

    /**
     * Добавляет элемент в список
     * @param element элемент
     * @return
     */
    @Override
    public boolean add(S element) {
        if (size == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
        elements[size++] = element;
        return true;
    }

    /**
     * Удаление элемента
     * @param o элемент, который нужно удалить
     * @return возвращает истину, если элемент есть в коллекции
     */
    @Override
    public boolean remove(Object o) {
        Object[] newElements = new Object[elements.length - 1];
        int j = 0;
        boolean isFound = false;
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                isFound = true;
                continue;
            }
            newElements[j] = elements[i];
            j++;
        }
        size--;
        elements = newElements;
        return isFound;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return internalList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends S> c) {
        return internalList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends S> c) {
        return internalList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return internalList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return internalList.retainAll(c);
    }

    /**
     * Удаляет все элементы
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            remove(elements[i]);
        }
        size = 0;
    }

    @Override
    public S get(int index) {
        return internalList.get(index);
    }

    @Override
    public S set(int index, S element) {
        return internalList.set(index, element);
    }

    @Override
    public void add(int index, S element) {
        internalList.add(index, element);
    }

    @Override
    public S remove(int index) {
        return internalList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return internalList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return internalList.lastIndexOf(o);
    }

    @Override
    public ListIterator<S> listIterator() {
        return internalList.listIterator();
    }

    @Override
    public ListIterator<S> listIterator(int index) {
        return internalList.listIterator(index);
    }

    @Override
    public List<S> subList(int fromIndex, int toIndex) {
        return internalList.subList(fromIndex, toIndex);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("<<<Студенты. Начало>>>\n");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(",\n");
            }
        }
        sb.append("\n<<<Студенты. Конец>>>");
        return sb.toString();
    }

    public static <S> Collector<S, CustomList<S>, CustomList<S>> customCollector() {
        return Collector.of(
                CustomList::new,
                CustomList::add,
                (list1, list2) -> {
                    list1.add((S) list2);
                    return list1;
                },
                Function.identity(),
                Collector.Characteristics.UNORDERED
        );
    }
}
