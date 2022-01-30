<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="main-section">
    <p class="main-section__title">
        Для модификации введите новые значения и нажмите кнопку "Применить".
    </p>

    <form class="main-section__form" method="post" action="/student-modify">
        <input type="hidden" name="id" value="${student.id}">
        <input class="main-section__form input" type="text" name="surname" value="${student.surname}">
        <input class="main-section__form input" type="text" name="name" value="${student.name}">
        <input class="main-section__form input" type="text" name="group" value="${student.group}">
        <input class="main-section__form input" type="text" name="date" id="datepicker" value="${student.date}">
        <input class="apply_button" type="submit" value="Применить">
    </form>
</section>
