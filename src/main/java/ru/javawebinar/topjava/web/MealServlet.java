package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.MealMapStorage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private final MealMapStorage mealsStorage = new MealMapStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String desc = request.getParameter("description");
        int call = Integer.parseInt(request.getParameter("calories"));
        Meal meal;

        if (id.equals("-1")) {
            meal = new Meal(mealsStorage.getCount(), dateTime, desc, call);
            mealsStorage.save(meal);
        } else {
            meal = new Meal(Integer.parseInt(id), dateTime, desc, call);
            mealsStorage.update(meal);
        }
        response.sendRedirect("meals");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("AAAAAAAAAAAAAAAAAAAAAAAAAA");
        log.debug(mealsStorage.getMeals().toString());
        log.debug(mealsStorage.getMeals().keySet().toString());
        log.debug(String.valueOf(mealsStorage.getCount()));

        String action = request.getParameter("action");
        String id = request.getParameter("id");
        if(action==null){
            request.setAttribute("meals", getMealsTo());
            request.getRequestDispatcher("/WEB-INF/jsp/meals/meals.jsp").forward(request, response);
            return;
        }


        Meal meal;
        switch (action) {
            case "add":
                meal = new Meal(-1, LocalDateTime.MIN,"", 0);
                request.setAttribute("meal", meal);
                break;
            case "delete":
                mealsStorage.delete(Integer.parseInt(id));
                response.sendRedirect("meals");
                return;

            case "view":
            case "edit":
                meal = mealsStorage.get(Integer.parseInt(id));
                request.setAttribute("meal", meal);
                break;
            default:
/*                request.setAttribute("meals", getMealsTo());
                request.getRequestDispatcher("/WEB-INF/jsp/meals/meals.jsp").forward(request, response);*/
                break;
        }

        //request.setAttribute("meal", meal);
        request.getRequestDispatcher(action.equals("view")? "/WEB-INF/jsp/meals/viewMeal.jsp" :"/WEB-INF/jsp/meals/editMeal.jsp").forward(request, response);

    }

    private List<MealTo> getMealsTo(){
       return MealsUtil.filteredByStreams(mealsStorage.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
    }

}
