package ru.javawebinar.topjava.storage;

import java.util.List;

public interface Storage<E, K> {
    E create(E t);

    void delete(K k);

    E update(E t);

    E get(K k);

    List<E> getAll();
}
