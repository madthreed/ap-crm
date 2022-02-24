<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="main-section create-modify">
    <c:choose>
        <c:when test="${role == 1}">


            <p class="main-section__title">
                Для создания дисциплины заполните все поля и нажмите кнопку "Создать".
            </p>

            <form class="main-section__form" method="post">
                <input class="main-section__form input" type="text" name="name" placeholder="Название дисциплины"
                       required pattern="[0-9A-Za-zА-Яа-я ]+" maxlength="64">
                <input class="apply_button" type="submit" value="Создать">
            </form>


        </c:when>
        <c:otherwise>
            <p style="color:#ff0000;" class="main-section__title">Вам сюда нельзя!!!</p>
        </c:otherwise>
    </c:choose>
</section>