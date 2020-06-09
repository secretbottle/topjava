package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Meal {
    private final AtomicInteger id;
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(0, dateTime, description, calories);
    }

    public Meal(int id, LocalDateTime dateTime, String description, int calories) {
        this.id = new AtomicInteger(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public AtomicInteger getId() {
        return id;
    }

    public int getIdToInt() {
        return id.get();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
