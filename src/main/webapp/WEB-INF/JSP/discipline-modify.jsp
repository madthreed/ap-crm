<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="main-section">
    <p class="main-section__title">
        Для того чтобы модифицировать дисциплину введите новое значение поля и нажмите кнопку "Применить".
    </p>

    <form class="main-section__form" method="post" action="/discipline-modify">
        <input type="hidden" name="id" value="${discipline.id}">
        <input class="main-section__form input" type="text" name="name" value="${discipline.name}">
        <input class="i_button" type="submit" value="Применить">
    </form>
</section>
