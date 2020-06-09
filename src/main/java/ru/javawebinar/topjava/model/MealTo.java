package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class MealTo {
    private final AtomicInteger id;
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final boolean excess;

    public MealTo(LocalDateTime dateTime, String description, int calories, boolean excess) {
        this(0, dateTime, description, calories, excess);
    }

    public MealTo(int id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.id = new AtomicInteger(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public AtomicInteger getId() {
        return id;
    }

    public int getIdToInt(){
        return id.get();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getLocalDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getLocalTime() {
        return dateTime.toLocalTime();
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean getExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }
}
