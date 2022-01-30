<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="main-section terms">
    <div class="main-section__menu terms">
        <form method="get" action="/term-create">
            <input class="i_button" type="submit" value="Создать семестр...">
        </form>

        <input class="i_button disabled" type="submit" value="Модифицировать текущий семестр...">

        <input class="i_button disabled" type="submit" value="Удалить текущий семестр...">
    </div>

    <div class="main-section__terms-termslist">
        <span>Выбрать семестр</span>
        <select name="termSelector" required>
            <c:forEach var="term" items="${terms}">
                <option value="${term.id}">${term.name}</option>
            </c:forEach>
        </select>
        <input class="apply_button disabled" type="submit" value="Выбрать">
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
        <c:forEach var="discipline" items="${disciplines}">
            <tr>
                <td class="main-section__table col_discipline">${discipline.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>
