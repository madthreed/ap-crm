<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Disciplines List</title>
    <link rel="stylesheet" href="../../resources/css/fonts.css">
    <link rel="stylesheet" href="../../resources/css/style.css">
</head>
<body>

<div class="container">
    <nav class="nav">
        <a class="nav_button" href="/home">На главную</a>
        <a class="nav_button" href="#" onclick="history.back();">Назад</a>
    </nav>

    <div class="log-in-out">
        <a class="nav_button" href="">Выйти</a>
    </div>

    <header class="header">
        <h1 class="header name">Система управления студентами и их успеваемостью</h1>
    </header>

    <section class="main-section disciplines">
        <div class="main-section__menu disciplines">
            <form method="get" action="/discipline-create">
                <input class="i_button" type="submit" value="Создать дисциплину...">
            </form>
            <form method="get" action="/discipline-modify">
                <input class="i_button disabled" type="submit" value="Модифицировать выбранную дисциплину...">
            </form>
            <form method="get" action="/discipline-delete">
                <input class="i_button disabled" type="submit" value="Удалить выбранные дисциплины...">
            </form>
        </div>

        <p>Список дисциплин</p>

        <table class="main-section__table disciplines">
            <thead>
            <tr>
                <th class="disciplines-tbl-header"></th>
                <th>Название дисциплины</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="discipline" items="${disciplines}">
                <tr>
                    <td class="main-section__table col_checkbox">
                        <input type="checkbox" name="selected" value="${discipline.id}">
                    </td>
                    <td class="main-section__table col_discipline">${discipline.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
</div>


<footer class="footer">
    <div>&copy; 2021 MadThreeD "Java Junior" Avenue course</div>
</footer>
</body>
</html>