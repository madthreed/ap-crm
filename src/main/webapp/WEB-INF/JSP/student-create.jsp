<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Student Creating</title>
    <link rel="stylesheet" href="../../resources/css/fonts.css">
    <link rel="stylesheet" href="../../resources/css/style.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
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
    <nav class="nav">
        <a class="nav_button" href="/home">На главную</a>
        <a class="nav_button" href="#" onclick="history.back();">Назад</a>
    </nav>

    <header class="header">
        <h1 class="header name">Система управления студентами и их успеваемостью</h1>
    </header>

    <section class="main-section">
        <p class="main-section__title">
            Для создания студента заполните все поля и нажмите кнопку "Создать".
        </p>

        <form class="main-section__form" method="post" action="/student-create">
            <input class="main-section__form input" type="text" name="surname" placeholder="Фамилия">
            <input class="main-section__form input" type="text" name="name" placeholder="Имя">
            <input class="main-section__form input" type="text" name="group" placeholder="Группа">
            <input class="main-section__form input" type="text" name="date" id="datepicker"
                   placeholder="Дата поступления">
            <input class="i_button" type="submit" value="Создать">
        </form>
    </section>

    <div class="log-in-out">
        <a class="nav_button" href="">Выйти</a>
    </div>

    <footer class="footer">
        <div>
            &copy; 2021 MadThreeD - "Java Junior" Avenue course
        </div>
    </footer>
</div>
</body>
</html>