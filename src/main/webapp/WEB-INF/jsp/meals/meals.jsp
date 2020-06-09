<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Каллории</th>
    </tr>
    <jsp:useBean id="meals" scope="request" type="java.util.List"/>
    <c:forEach items="${meals}" var="mealTo">
        <jsp:useBean id="mealTo" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr mealExcess="${mealTo.excess}">
            <td>${mealTo.localDate} ${mealTo.localTime}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td><a href="meals?id=${mealTo.id}&action=edit">Edit</a></td>
            <td><a href="meals?id=${mealTo.id}&action=delete">Delete</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td><a href="meals?action=add">Создать</a></td>
    </tr>
</table>
</body>
</html>