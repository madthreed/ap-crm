<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Student Progress</title>
    <link rel="stylesheet" href="../../../resources/css/fonts.css">
    <link rel="stylesheet" href="../../../resources/css/style.css">
</head>
<body>

<div class="container">
    <nav class="nav">
        <a class="nav_button" href="/home">На главную</a>
        <a class="nav_button" href="#" onclick="history.back();">Назад</a>
    </nav>

    <div class="log-in-out">
        <a class="nav_button" href="">Выйти</a>
    </div>

    <header class="header">
        <h1 class="header name">Система управления студентами и их успеваемостью</h1>
    </header>

    <section class="main-section student-progress">
        <p class="main-section__title student-progress">
            Отображена успеваемость для следующего студента:
        </p>

        <table class="main-section__table student-progress-sngd">
            <tr>
                <th class="student-progress-sngd-tbl-header">Фамилия</th>
                <th>Имя</th>
                <th>Группа</th>
                <th>Дата поступления</th>
            </tr>
            <tr>
                <td class="main-section__table col_surname">Сидоров</td>
                <td class="main-section__table col_name">Сидор</td>
                <td class="main-section__table col_group">КТ-21</td>
                <td class="main-section__table col_date">01/09/2011</td>
            </tr>
        </table>

        <table class="main-section__table student-progress-disciplines">
            <tr>
                <th class="student-progress-disciplines-tbl-header">Дисциплина</th>
                <th>Оценка</th>
            </tr>
            <tr>
                <td class="main-section__table col_discipline">Информатика</td>
                <td class="main-section__table col_mark">5</td>
            </tr>
            <tr>
                <td class="main-section__table col_discipline">Системный анализ</td>
                <td class="main-section__table col_mark">4</td>
            </tr>
            <tr>
                <td class="main-section__table col_discipline">Управление проектами</td>
                <td class="main-section__table col_mark">5</td>
            </tr>
            <tr>
                <td class="main-section__table col_discipline">Основы дискретной математики</td>
                <td class="main-section__table col_mark">4</td>
            </tr>
        </table>

        <div class="main-section__student-progress-termlist">
            <span>Выбрать семестр</span>
            <select class="term-selector" name="" id="">
                <option value="">Семестр 1</option>
                <option value="">Семестр 2</option>
                <option value="">Семестр 3</option>
                <option value="">Семестр4</option>
            </select>
            <input class="i_button" type="submit" name="apply" value="Выбрать">
            <p>Средняя оценка за семестр: 4.8 балла</p>
        </div>
    </section>

    <footer class="footer">
        <div>&copy; 2021 MadThreeD "Java Junior" Avenue course</div>
    </footer>
</div>
</body>
</html>