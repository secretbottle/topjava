package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserMealMapStorage implements Storage {
    private Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Meal create(Meal meal) {
        meal.getId().set((counter.getAndIncrement()));
        return meals.put(meal.getIdToInt(), meal);
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }

    @Override
    public Meal update(Meal meal) {
        return meals.replace(meal.getIdToInt(), meal);
    }

    @Override
    public Meal get(int id) {
        return meals.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }
}
