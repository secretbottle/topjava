<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <title>Приём пищи #${meal.id}</title>
</head>
<body>
<h3>Дата и время приёма: ${meal.date} ${meal.time}</h3>
<h3>Описание: ${meal.description}</h3>
<h3>Количество каллорий: ${meal.calories}</h3>
<button onclick="window.history.back()">Назад</button>
</body>
</html>