package org.aston.prod.additionalTasks.task3;

import java.util.function.Consumer;

public interface ICollection<S> {
    int length();
    boolean isEmpty();
    boolean add(S element);
    boolean remove(S element);
    void removeAll();
    void forEach(Consumer<S> consumer);
}
