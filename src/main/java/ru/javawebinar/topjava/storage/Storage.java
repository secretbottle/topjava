package ru.javawebinar.topjava.storage;

import java.util.List;

public interface Storage<E,K> {
    void save(E t);

    void delete(K k);

    void update(E t);

    E get(K k);

    List<E> getAll();
}
