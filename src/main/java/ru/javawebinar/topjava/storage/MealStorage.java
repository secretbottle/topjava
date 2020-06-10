package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealStorage implements Storage {
    private Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Meal create(Meal meal) {
        meal.setId((counter.getAndIncrement()));
        meals.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(int id) {
        meals.remove(id);
    }

    @Override
    public Meal update(Meal meal) {
        meals.replace(meal.getId(), meal);
        return meal;
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
