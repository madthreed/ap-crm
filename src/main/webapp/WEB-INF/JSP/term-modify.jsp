<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<section class="main-section create-modify">
    <c:choose>
        <c:when test="${role == 1}">


            <p class="main-section__title">Отредактируйте длительность и спикок дисциплин и нажмите кнопку
                "Применить".</p>
            <p style="font-size: x-small">Для изменения доступны только те дисциплины, по которым в семестре не
                выставлены
                оценки</p>
            <p style="font-size: x-small">Выбор нескольких дисциплин Ctrl+click</p>
            <br>
            <br>

            <form class="main-section__form" method="post" action="/term-modify">
                <input type="hidden" name="id" value="${term.id}">
                <p style="font-size: small">Длительность (в неделях)</p>
                <input class="main-section__form input" type="number" name="duration" id="duration"
                       value="${term.duration}"
                       required maxlength="64">

                <select id="disciplineSelector" name="disciplineSelector" size="8" multiple>
                    <c:forEach items="${allDisciplines}" var="discipline">
                        <c:choose>
                            <c:when test="${fn:contains(blockedDisciplines, discipline)}">
                                <option disabled value="${discipline.id}">${discipline.name}</option>
                            </c:when>
                            <c:when test="${fn:contains(selectedDisciplines, discipline)}">
                                <option selected value="${discipline.id}">${discipline.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${discipline.id}">${discipline.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>

                <div>
                    <input class="apply_button" type="button" onclick="clearSelectedDisciplines();" value="Очистить"/>
                </div>
                <div>
                    <input class="apply_button" type="submit" value="Применить">
                </div>
            </form>

            <form method="get" action="/terms">
                <input class="apply_button" type="submit" value="Вернуться к списку">
            </form>



        </c:when>
        <c:otherwise>
            <p style="color:#ff0000;" class="main-section__title">Вам сюда нельзя!!!</p>
        </c:otherwise>
    </c:choose>
</section>

