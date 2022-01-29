<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Students List</title>
    <link rel="stylesheet" href="../../resources/css/fonts.css">
    <link rel="stylesheet" href="../../resources/css/style.css">
    <script src="../../resources/js/functions.js"></script>
</head>
<body>
<div class="container">
    <nav class="nav">
        <a class="nav_button" href="/home">На главную</a>
        <a class="nav_button" href="#" onclick="history.back();">Назад</a>
    </nav>

    <header class="header">
        <h1 class="header name">Система управления студентами и их успеваемостью</h1>
    </header>

    <div class="log-in-out">
        <a class="nav_button" href="">Выйти</a>
    </div>

    <section class="main-section students">
        <div class="main-section__menu students">
            <form method="get" action="/student-progress">
                <input class="i_button disabled" type="submit" value="Просмотреть успеваемость выбранных студентов">
            </form>
            <form method="get" action="/student-create">
                <input class="i_button" type="submit" value="Создать студента...">
            </form>
            <input class="i_button" onclick="modifyStudent()" type="submit"
                   value="Модифицировать выбранного студента...">
            <input class="i_button" onclick="deleteStudents()" type="submit" value="Удалить выбранных студентов">
        </div>

        <p>Список студентов</p>

        <table class="main-section__table students">
            <thead>
            <tr>
                <th class="students-tbl-header"></th>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Группа</th>
                <th>Дата поступления</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${students}" var="student">
                <tr>
                    <td class="main-section__table col_checkbox">
                        <input type="checkbox" name="idStudent" value=${student.id}>
                    </td>
                    <td class="main-section__table col_surname">${student.surname}</td>
                    <td class="main-section__table col_name">${student.name}</td>
                    <td class="main-section__table col_group">${student.group}</td>
                    <td class="main-section__table col_date">${student.date}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
</div>

<form id="deleteStudentForm" action="/students" method="post">
    <input type="hidden" id="deleteStudentHidden" name="deleteStudentHidden">
</form>

<form id="modifyStudentForm" action="/student-modify" method="get">
    <input type="hidden" id="modifyStudentHidden" name="modifyStudentHidden">
</form>

<footer class="footer">
    <div>&copy; 2021 MadThreeD -"Java Junior" Avenue course</div>
</footer>
</body>
</html>