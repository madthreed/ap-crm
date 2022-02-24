<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<section class="main-section student-progress">
    <p class="main-section__title student-progress">
        Редактирование успеваемости для следующего студента:
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

    <form class="main-section__form" method="post" action="/student-progress-edit">
        <table class="main-section__table student-progress-disciplines">
            <thead>
            <tr>
                <th class="main-section__table col_discipline">Дисциплина</th>
                <th class="main-section__table col_mark">Оценка</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="disciplineWithMarks" items="${disciplinesWithMarks}">
                <input type="hidden" name="disciplineId" value="${disciplineWithMarks.discipline.id}">
                <input type="hidden" name="markId" value="${disciplineWithMarks.mark.id}">
                <tr>
                    <td class="main-section__table col_discipline">
                            ${disciplineWithMarks.discipline.name}
                    </td>

                    <td class="main-section__table col_mark">
                        <input type="number" min="0" max="5" id="${disciplineWithMarks.mark.id}" name="marks"
                               placeholder="${disciplineWithMarks.mark.mark}" value="${disciplineWithMarks.mark.mark}">
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <input class="apply_button" type="submit" value="Применить">
    </form>
</section>
