package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealMapStorage implements Storage<Meal, AtomicInteger> {
    private ConcurrentMap<AtomicInteger, Meal> meals = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(meals.size());

    @Override
    public void save(Meal meal) {
        meals.put(counter, meal);
    }

    @Override
    public void delete(AtomicInteger id) {
        meals.remove(id);
    }

    @Override
    public void update(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public Meal get(AtomicInteger id) {
        return meals.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    public AtomicInteger getCount(){
        return counter;
    }
}
