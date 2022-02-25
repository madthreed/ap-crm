<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--~
  ~ Created by MadThreeD on 2022.
  --%>

<section class="main-section students">
    <div class="main-section__menu students">
        <input class="i_button" onclick="progressStudent()" type="submit"
               value="Просмотреть успеваемость выбранного студента">

        <c:if test="${role == 1}">
            <form method="get" action="${pageContext.request.contextPath}/student-create">
                <input class="i_button" type="submit" value="Создать студента...">
            </form>
            <input class="i_button" onclick="modifyStudent()" type="submit"
                   value="Модифицировать выбранного студента...">
            <input class="i_button" onclick="deleteStudents()" type="submit" value="Удалить выбранных студентов">

            <form id="deleteStudentForm" action="${pageContext.request.contextPath}/students" method="post">
                <input type="hidden" id="deleteStudentHiddenId" name="deleteStudentHiddenId">
            </form>
            <form id="modifyStudentForm" action="${pageContext.request.contextPath}/student-modify" method="get">
                <input type="hidden" id="modifyStudentHiddenId" name="modifyStudentHiddenId">
            </form>
        </c:if>

        <form id="progressStudentForm" action="${pageContext.request.contextPath}/student-progress" method="get">
            <input type="hidden" id="progressStudentId" name="progressStudentId">
        </form>
    </div>

    <br>

    <span>Список студентов</span>

    <label style="font-size: x-small">Сортировать по:
        <select>
            <option  disabled>фамилии</option>
            <option  disabled>группе</option>
            <option  disabled>дате поступления</option>
        </select>
    </label>

    <table class="main-section__table students">
        <thead>
        <tr>
            <th class="main-section__table col_checkbox"></th>
            <th class="main-section__table col_surname">Фамилия</th>
            <th class="main-section__table col_name">Имя</th>
            <th class="main-section__table col_group">Группа</th>
            <th class="main-section__table col_date">Дата поступления</th>
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