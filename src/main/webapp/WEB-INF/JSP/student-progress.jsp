<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="main-section student-progress">
    <p class="main-section__title student-progress">
        Отображена успеваемость для следующего студента:
    </p>

    <table class="main-section__table student-progress-sngd">
        <thead>
        <tr>
            <th class="students-tbl-header">Фамилия</th>
            <th>Имя</th>
            <th>Группа</th>
            <th>Дата поступления</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="main-section__table col_surname">${student.surname}</td>
            <td class="main-section__table col_name">${student.name}</td>
            <td class="main-section__table col_group">${student.group}</td>
            <td class="main-section__table col_date">${student.date}</td>
        </tr>
        </tbody>
    </table>

    <table class="main-section__table student-progress-disciplines">
        <thead>
        <tr>
            <th class="main-section__table col_discipline">Дисциплина</th>
            <th class="main-section__table col_mark">Оценка</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="mark" items="${marks}">
            <tr>
                <td class="main-section__table col_discipline">${mark.discipline.name}</td>
<%--                <td>--%>
<%--                    <input class="main-section__table col_mark" type="number" value="${mark.mark}">--%>
<%--                </td>--%>
                <td class="main-section__table col_mark">${mark.mark}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="main-section__student-progress-termlist">
        <span>Выбрать семестр</span>
        <form method="get" action="/student-progress">
            <select name="termSelector" id="termSelector" required>
                <c:forEach var="term" items="${terms}">
                    <c:choose>
                        <c:when test="${term.id == selectedTerm.id}">
                            <option selected value="${term.id}">${term.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${term.id}">${term.name}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <input type="hidden" name="progressStudentId" value="${student.id}">
            <input class="apply_button" type="submit" value="Выбрать">
        </form>

        <form method="get" action="/student-progress-edit">
            <input type="hidden" name="studentId" value="${student.id}">
            <input type="hidden" name="termId" value="${selectedTerm.id}">
            <input class="apply_button" type="submit" value="Редактировать оценки">
        </form>

        <p>Средняя оценка за семестр: ${averageMark}</p>
    </div>
</section>
