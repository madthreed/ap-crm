<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="main-section students">
    <div class="main-section__menu students">
        <input class="i_button" onclick="progressStudent()" type="submit"
               value="Просмотреть успеваемость выбранных студентов">

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
        <c:forEach var="student" items="${students}">
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

<form id="deleteStudentForm" action="/students" method="post">
    <input type="hidden" id="deleteStudentHiddenId" name="deleteStudentHiddenId">
</form>

<form id="modifyStudentForm" action="/student-modify" method="get">
    <input type="hidden" id="modifyStudentHiddenId" name="modifyStudentHiddenId">
</form>

<form id="progressStudentForm" action="/student-progress" method="get">
    <input type="hidden" id="progressStudentHiddenId" name="progressStudentHiddenId">
</form>