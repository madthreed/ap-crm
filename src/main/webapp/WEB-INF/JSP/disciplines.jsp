<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--~
  ~ Created by MadThreeD on 2022.
  --%>

<section class="main-section disciplines">
    <div class="main-section__menu disciplines">
        <c:if test="${role == 1}">
            <form method="get" action="${pageContext.request.contextPath}/discipline-create">
                <input class="i_button" type="submit" value="Создать дисциплину...">
            </form>
            <input class="i_button" onclick="modifyDiscipline()" type="submit"
                   value="Модифицировать выбранную дисциплину...">
            <input class="i_button" onclick="deleteDisciplines()" type="submit" value="Удалить выбранные дисциплины...">

            <form id="deleteDisciplineForm" action="${pageContext.request.contextPath}/disciplines" method="post">
                <input type="hidden" id="deleteDisciplineHiddenId" name="deleteDisciplineHiddenId">
            </form>

            <form id="modifyDisciplineForm" action="${pageContext.request.contextPath}/discipline-modify" method="get">
                <input type="hidden" id="modifyDisciplineHiddenId" name="modifyDisciplineHiddenId">
            </form>
        </c:if>
    </div>

    <p>Список дисциплин</p>

    <table class="main-section__table disciplines">
        <thead>
        <tr>
            <th class="main-section__table col_checkbox"></th>
            <th class="main-section__table col_discipline">Название дисциплины</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="discipline" items="${disciplines}">
            <tr>
                <td class="main-section__table col_checkbox">
                    <input type="checkbox" name="idDiscipline" value="${discipline.id}">
                </td>
                <td class="main-section__table col_discipline">${discipline.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>
