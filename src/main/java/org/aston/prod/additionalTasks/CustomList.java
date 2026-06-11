package org.aston.prod.additionalTasks;

import java.util.*;
import java.util.function.Consumer;
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
     *
     * @return возвращает длину
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Пустой список или нет
     *
     * @return возвращает истину, если пустой
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<S> iterator() {
        return new Iterator<S>() {
            private int cursor = 0;
            private int lastRet = -1;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public S next() {
                if (cursor >= size) {
                    throw new NoSuchElementException();
                }
                lastRet = cursor;
                return (S) elements[cursor++];
            }

            @Override
            public void remove() {
                if (lastRet < 0) {
                    throw new IllegalStateException();
                }
                CustomList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return internalList.toArray(a);
    }

    /**
     * Добавляет элемент в список
     *
     * @param element элемент
     * @return
     */
    @Override
    public boolean add(S element) {
        if (size == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
        elements[size++] = element;
        return true;
    }

    /**
     * Удаление элемента
     *
     * @param o элемент, который нужно удалить
     * @return возвращает истину, если элемент есть в коллекции
     */
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        for (Object s : c) {
            if (!contains(s))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends S> c) {
        if (c == null) throw new NullPointerException();
        if (c.isEmpty()) return false;
        boolean result = false;
        for (S s : c) {
            add(s);
            result = true;
        }
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends S> c) {
        if (c == null) throw new NullPointerException();
        if (c.isEmpty()) return false;
        boolean result = false;
        for (S s : c) {
            add(index++, s);
            result = true;
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        boolean result = false;
        for (Object s : c) {
            while (contains(s)) {
                remove(s);
                result = true;
            }
        }
        return result;
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
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public S get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (S) elements[index];
    }

    @Override
    public S set(int index, S element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        S oldElement = get(index);
        elements[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, S element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    @Override
    public S remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        S element = (S) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        elements[size] = null;
        return element;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }
        return -1;
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
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        List<S> sublist = new CustomList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            sublist.add((S) elements[i]);
        }
        return sublist;
    }

    @Override
    public void forEach(Consumer<? super S> consumer) {
        for (int i = 0; i < size; i++) {
            consumer.accept((S) elements[i]);
        }
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
        return Collector.of(CustomList::new, CustomList::add, (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        });
    }
}
