<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Discipline Modifying</title>
    <link rel="stylesheet" href="../../../resources/css/fonts.css">
    <link rel="stylesheet" href="../../../resources/css/style.css">
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
            Для того чтобы модифицировать дисциплину введите новое значение поля и нажмите кнопку "Применить".
        </p>

        <form class="main-section__form" method="post" action="/discipline-modify">
            <input class="main-section__form input" type="hidden" name="disciplineId"
                   value="${disciplineId}">
            <input class="main-section__form input" type="text" name="disciplineName"
                   value="${disciplineName}">
            <input class="i_button" type="submit" name="modify" value="Применить">
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