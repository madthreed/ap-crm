<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--~
  ~ Created by MadThreeD on 2022.
  --%>

<section class="main-section">
    <div class="main-section__menu home">
        <a class="a_button" href="${pageContext.request.contextPath}/students">Студенты</a>
        <a class="a_button" href="${pageContext.request.contextPath}/disciplines">Дисциплины</a>
        <a class="a_button" href="${pageContext.request.contextPath}/terms">Семестры</a>
    </div>
</section>
