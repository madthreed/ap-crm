<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="main-section create-modify">
    <p class="main-section__title">
        Для создания студента заполните все поля и нажмите кнопку "Создать".
    </p>

    <form class="main-section__form" method="post" action="/student-create">
        <input class="main-section__form input" type="text" name="surname" placeholder="Фамилия" required pattern="[A-Za-zА-Яа-я]+" maxlength="64">
        <input class="main-section__form input" type="text" name="name" placeholder="Имя" required pattern="[A-Za-zА-Яа-я]+" maxlength="64">
        <input class="main-section__form input" type="text" name="group" placeholder="Группа" required pattern="[0-9A-Za-zА-Яа-я-]+" maxlength="64">
        <input class="main-section__form input" type="text" name="date" id="datepicker"
               placeholder="Дата поступления" required>
        <input class="apply_button" type="submit" value="Создать">
    </form>
</section>