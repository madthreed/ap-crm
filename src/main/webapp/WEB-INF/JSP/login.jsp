<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="main-section create-modify">
    <form method="post" action="/login" class="main-section__form">
        <input class="main-section__form input" name="login" type="text" placeholder="Введите логин" required pattern="[A-Za-zА-Яа-я0-9]+" maxlength="64">
        <input class="main-section__form input" name="password" type="password" placeholder="Введите пароль" required>
        <select name="role">
            <c:forEach var="role" items="${roles}">
                <option value="${role.id}">${role.role}</option>
            </c:forEach>
        </select>
        <br>
        <input class="apply_button" type="submit" value="Войти">
    </form>

    <p style="font-size: small">test credentials:</p>
    <p style="font-size: small">admin:123, teacher:123, student:123</p>


    <c:if test="${message == 1}">
        <p style="color: red; font-size: smaller">Логин, пароль или роль пользователя не верны</p>
    </c:if>
</section>