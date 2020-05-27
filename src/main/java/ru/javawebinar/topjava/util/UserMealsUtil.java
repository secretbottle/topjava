package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        System.out.println("By cycles");
        List<UserMealWithExcess> mealsToC = filteredByCycles(meals, LocalTime.of(1, 0), LocalTime.of(23, 0), 1900);
        mealsToC.forEach(System.out::println);
        System.out.println("By stream");
        List<UserMealWithExcess> mealsToS = filteredByStreams(meals, LocalTime.of(1, 0), LocalTime.of(23, 0), 1900);
        mealsToS.forEach(System.out::println);
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> excesses = new ArrayList<>();
        Map<LocalDate, Integer> caloriesSumForDay = new HashMap<>();
        meals.forEach(userMeal -> caloriesSumForDay.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum));
        meals.forEach(userMeal -> {
            LocalDateTime mealDate = userMeal.getDateTime();
            if (TimeUtil.isBetweenHalfOpen(mealDate.toLocalTime(), startTime, endTime)) {
                excesses.add(new UserMealWithExcess(
                        mealDate,
                        userMeal.getDescription(),
                        userMeal.getCalories(),
                        caloriesSumForDay.get(mealDate.toLocalDate()) > caloriesPerDay
                ));
            }
        });
        return excesses;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumForDay = meals.stream().collect(Collectors.groupingBy(x-> x.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));
        return meals.stream().filter(x -> TimeUtil.isBetweenHalfOpen(x.getDateTime().toLocalTime(), startTime, endTime))
                .map(x-> new UserMealWithExcess(x.getDateTime(),
                        x.getDescription(),x.getCalories(),
                        caloriesSumForDay.get(x.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}
