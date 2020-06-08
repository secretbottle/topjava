package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealMapStorage implements Storage<Meal, Integer> {
    private ConcurrentMap<Integer, Meal> meals = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void save(Meal meal) {
        meals.put(counter.getAndIncrement(), meal);
    }

    @Override
    public void delete(Integer id) {
        meals.remove(id);
        counter.getAndDecrement();
    }

    @Override
    public void update(Meal meal) {
        meals.replace(meal.getId(), meal);
    }

    @Override
    public Meal get(Integer id) {
        return meals.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    public ConcurrentMap<Integer, Meal> getMeals() {
        return meals;
    }

    public int getCount() {
        return counter.get();
    }
}
