<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<section class="main-section create-modify">
    <p class="main-section__title">
        Для модификации семестра введите новое значение длительности, выберите дисциплины и нажмите кнопку "Применить".
    </p>

    <form class="main-section__form" method="post" action="/term-modify">
        <input type="hidden" name="id" value="${term.id}">
        <input class="main-section__form input" type="number" name="duration" id="duration" value="${term.duration}"
               required maxlength="64">

        <p>Дисциплины в семестре</p>

        <select id="disciplineSelector" name="disciplineSelector" size="8" multiple>
            <c:forEach items="${allDisciplines}" var="discipline">
                <c:choose>
                    <c:when test="${fn:contains(selectedDisciplines, discipline)}">
                        <option selected value="${discipline.id}">${discipline.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${discipline.id}">${discipline.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>

        <input class="apply_button" type="submit" value="Применить">
    </form>
</section>

<%--<form id="modifyTermForm" action="/term-modify" method="post">--%>
<%--    <input type="hidden" id="modifyTermHiddenDuration" name="modifyTermHiddenDuration">--%>
<%--    <input type="hidden" id="modifyTermHiddenIds" name="modifyTermHiddenIds">--%>
<%--</form>--%>