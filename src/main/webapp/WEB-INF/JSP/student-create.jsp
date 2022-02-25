<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--~
  ~ Created by MadThreeD on 2022.
  --%>

<section class="main-section create-modify">
    <c:choose>
        <c:when test="${role == 1}">


            <p class="main-section__title">
                Для создания студента заполните все поля и нажмите кнопку "Создать".
            </p>

            <form class="main-section__form" method="post" action="${pageContext.request.contextPath}/student-create">
                <input class="main-section__form input" type="text" name="surname" placeholder="Фамилия" required
                       pattern="[A-Za-zА-Яа-я]+" maxlength="64">
                <input class="main-section__form input" type="text" name="name" placeholder="Имя" required
                       pattern="[A-Za-zА-Яа-я]+" maxlength="64">
                <input class="main-section__form input" type="text" name="group" placeholder="Группа" required
                       pattern="[0-9A-Za-zА-Яа-я-]+" maxlength="64">
                <input class="main-section__form input" type="date" name="date" placeholder="Дата поступления" required>
                <input class="apply_button" type="submit" value="Создать">
            </form>


        </c:when>
        <c:otherwise>
            <p style="color:#ff0000;" class="main-section__title">Вам сюда нельзя!!!</p>
        </c:otherwise>
    </c:choose>
</section>