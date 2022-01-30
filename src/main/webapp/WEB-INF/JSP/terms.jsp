<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
