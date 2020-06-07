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
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr mealExcess="${meal.excess}">
            <td>${meal.localDate} ${meal.localTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            </tr>
        </c:forEach>
</table>

</body>
</html>