<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="main-section terms">
    <div class="main-section__menu terms">
        <c:if test="${role == 1}">
            <form method="get" action="/term-create">
                <input class="i_button" type="submit" value="Создать семестр...">
            </form>

            <input class="i_button" onclick="modifyTerm()" type="submit" value="Модифицировать текущий семестр...">

            <input class="i_button" onclick="deleteTerm()" type="submit" value="Удалить текущий семестр...">
        </c:if>
    </div>

    <div class="main-section__terms-termslist">
        <span>Выбрать семестр</span>
        <form method="get" action="/terms">
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
            <input class="apply_button" type="submit" value="Выбрать">
        </form>

        <p>Длительность семестра: ${selectedTerm.duration}
            <c:choose>
                <c:when test="${selectedTerm.duration % 10 == 1}">
                    <span>неделя</span>
                </c:when>
                <c:when test="${selectedTerm.duration % 10 == 2 || selectedTerm.duration % 10 == 3 || selectedTerm.duration % 10 == 4}">
                    <span>недели</span>
                </c:when>
                <c:otherwise>
                    <span>недель</span>
                </c:otherwise>
            </c:choose>
        </p>
    </div>

    <p>Список дисциплин семестра</p>
    <%--        <div class="main-section__table_overflow">--%>
    <table class="main-section__table terms">
        <thead>
        <tr>
            <th class="main-section__table col_discipline">Название дисциплины</th>
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

<form id="deleteTermForm" action="/terms" method="post">
    <input type="hidden" id="deleteTermHiddenId" name="deleteTermHiddenId">
</form>

<form id="modifyTermForm" action="/term-modify" method="get">
    <input type="hidden" id="modifyTermHiddenId" name="modifyTermHiddenId">
</form>