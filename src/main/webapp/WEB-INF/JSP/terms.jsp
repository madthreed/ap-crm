<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Terms List</title>
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

    <section class="main-section terms">
        <div class="main-section__menu terms">
            <form method="get" action="/term-create">
                <input class="i_button disabled" type="submit" value="Создать семестр...">
            </form>
            <form method="get" action="/term-modify">
                <input class="i_button disabled" type="submit" value="Модифицировать текущий семестр...">
            </form>
            <form method="get" action="/term-delete">
                <input class="i_button disabled" type="submit" value="Удалить текущий семестр...">
            </form>
        </div>

        <div class="main-section__terms-termslist">
            <span>Выбрать семестр</span>
            <select class="term-selector" name="" required>
                <c:forEach var="term" items="${terms}">
                <option name="selected" value="${term.id}">${term.name}
                    </c:forEach>
            </select>
            <input class="i_button disabled" type="submit" name="select" value="Выбрать">
        </div>

        <p>Длительность семестра: 24 недели</p>

        <p>Список дисциплин семестра</p>

        <%--        <div class="main-section__table_overflow">--%>
        <table class="main-section__table terms">
            <thead>
            <tr>
                <th class="terms-tbl-header">Название дисциплины</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="main-section__table col_discipline">Высшая математика</td>
            </tr>
            </tbody>
        </table>
    </section>

    <footer class="footer">
        <div>&copy; 2021 MadThreeD "Java Junior" Avenue course</div>
    </footer>
</div>
</body>
</html>