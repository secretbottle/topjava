package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping("/meals")
public class JspMealController extends AbstractMealController {

    @GetMapping
    public String getMeals(HttpServletRequest request) {
        request.setAttribute("meals", super.getAll());
        return "/meals";
    }

    @PostMapping("mealForm")
    protected String doPost(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String paramId = request.getParameter("id");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (StringUtils.isEmpty(paramId)) {
            super.create(meal);
        } else {
            Objects.requireNonNull(paramId);
            super.update(meal, Integer.parseInt(paramId));
        }
        return "redirect:/meals";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable int id, HttpServletRequest request) {
        final Meal meal = super.get(id);
        request.setAttribute("meal", meal);
        return "/mealForm";
    }

    @GetMapping("delete/{id}")
    public String delete0(@PathVariable int id) {
        super.delete(id);
        return "redirect:/meals";
    }

    @GetMapping("create")
    public String create(HttpServletRequest request) {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        request.setAttribute("meal", meal);
        return "/mealForm";
    }

    @GetMapping("filter")
    public String filter(HttpServletRequest request,
                         @RequestParam("startDate") String startDate, @RequestParam("startTime") String startTime,
                         @RequestParam("endDate") String endDate, @RequestParam("endTime") String endTime) {
        request.setAttribute("meals", super.getBetween(parseLocalDate(startDate), parseLocalTime(startTime),
                parseLocalDate(endDate), parseLocalTime(endTime)));
        return "/meals";
    }

}
