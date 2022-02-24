<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="main-section create-modify">
    <c:choose>
        <c:when test="${role == 1}">


            <p class="main-section__title">
                Для создания семестра заполните следующие данные и нажмите кнопку "Создать".
            </p>

            <div class="main-section__form">
                <input class="main-section__form input" type="number" id="duration"
                       placeholder="Длительность (в неделях)" required maxlength="64">

                <p>Дисциплины в семестре</p>

                <select id="disciplineSelector" name="disciplineSelector" size="8" multiple>
                    <c:forEach var="discipline" items="${disciplines}">
                        <option value="${discipline.id}">${discipline.name}</option>
                    </c:forEach>
                </select>

                <input class="apply_button" onclick="createTerm()" type="submit" value="Создать">
            </div>


            <form id="createTermForm" action="/term-create" method="post">
                <input type="hidden" id="createTermHiddenDuration" name="createTermHiddenDuration">
                <input type="hidden" id="createTermHiddenIds" name="createTermHiddenIds">
            </form>

        </c:when>
        <c:otherwise>
            <p style="color:#ff0000;" class="main-section__title">Вам сюда нельзя!!!</p>
        </c:otherwise>
    </c:choose>
</section>