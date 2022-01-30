<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="main-section">
    <p class="main-section__title">
        Для создания дисциплины заполните все поля и нажмите кнопку "Создать".
    </p>

    <form class="main-section__form" method="post">
        <input class="main-section__form input" type="text" name="name" placeholder="Название дисциплины">
        <input class="i_button" type="submit" value="Создать">
    </form>
</section>
