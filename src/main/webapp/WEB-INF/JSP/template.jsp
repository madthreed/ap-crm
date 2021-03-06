<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--~
  ~ Created by MadThreeD on 2022.
  --%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Students List</title>
    <link rel="stylesheet" href="../../resources/css/fonts.css">
    <link rel="stylesheet" href="../../resources/css/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <script src="../../resources/js/functions.js"></script>
    <script>
        $( function() {
            $( "#datepicker" ).datepicker();
            $( "#format" ).on( "change", function() {
                $( "#datepicker" ).datepicker( "option", "dateFormat", $( this ).val() );
            });
        } );
    </script>
</head>
<body>
<div class="container">
    <c:if test="${role != null}">
        <nav class="nav">
            <a class="nav_button" href="${pageContext.request.contextPath}/home">На главную</a>
            <a class="nav_button" href="#" onclick="history.back();">Назад</a>
        </nav>

        <div class="log-in-out">
            <a class="nav_button" href="${pageContext.request.contextPath}/logout">Выйти</a>
        </div>
    </c:if>

    <header class="header">
        <h1 class="header name">Система управления студентами и их успеваемостью</h1>
    </header>

    <jsp:include page="${currentPage}"/>
    <%--body--%>

</div>
<footer class="footer">
    <div>&copy; 2022 MadThreeD -"Java Junior" Avenue course</div>
</footer>
</body>
</html>