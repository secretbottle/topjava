<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ru">
<head>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <title>Meal #${meal.id}</title>
</head>
<body>
<form method="POST" action="meals" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="id" value="${meal.id}">
    <dl>
        <dt>Дата и время приёма пищи</dt>
        <dd><input type="datetime-local" name="dateTime" value="${meal.dateTime}" required></dd>
    </dl>
    <dl>
        <dt>Описание</dt>
        <dd><input type="text" name="description" size="50" value="${meal.description}" required></dd>
    </dl>
    <dl>
        <dt>Каллории</dt>
        <dd><input type="number" name="calories" size="5" value="${meal.calories}" required></dd>
    </dl>
    <button type="submit">Сохранить</button>
    <button onclick="window.history.back()">Отменить</button>
</form>
</body>
</html>
