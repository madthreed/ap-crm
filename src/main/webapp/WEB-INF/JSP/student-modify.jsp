<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="main-section create-modify">
    <c:choose>
        <c:when test="${role == 1}">


            <p class="main-section__title">
                Для модификации введите новые значения и нажмите кнопку "Применить".
            </p>

            <form class="main-section__form" method="post" action="/student-modify">
                <input type="hidden" name="id" value="${student.id}">
                <input class="main-section__form input" type="text" name="surname" value="${student.surname}" required
                       pattern="[A-Za-zА-Яа-я]+" maxlength="64">
                <input class="main-section__form input" type="text" name="name" value="${student.name}" required
                       pattern="[A-Za-zА-Яа-я]+" maxlength="64">
                <input class="main-section__form input" type="text" name="group" value="${student.group}" required
                       pattern="[0-9A-Za-zА-Яа-я-]+" maxlength="64">
                <input class="main-section__form input" type="date" name="date" value="${student.date}" required>
                    <%--        id="datepicker"--%>
                <input class="apply_button" type="submit" value="Применить">
            </form>


            <form method="get" action="/students">
                <input class="apply_button" type="submit" value="Вернуться к списку">
            </form>

        </c:when>
        <c:otherwise>
            <p style="color:#ff0000;" class="main-section__title">Вам сюда нельзя!!!</p>
        </c:otherwise>
    </c:choose>
</section>
