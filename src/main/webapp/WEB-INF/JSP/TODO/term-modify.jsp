<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Term Modifying</title>
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
            Для модификации семестра отредактируйте данные и нажмите кнопку "Применить".
        </p>

        <table class="main-section__form">
            <tr>
                <td class="main-section__form annotation">Длительность (в неделях)</td>
                <td class="main-section__form data">
                    <label>
                        <input class="main-section__form input" type="text">
                    </label>
                </td>
            </tr>
            <tr>
                <td class="main-section__form annotation">Дисциплины в семестре</td>
                <td class="main-section__form data">
                    <select class="discipline-selector" name="" id="" size="10" multiple>
                        <option value="">Информатика</option>
                        <option value="">Политология</option>
                        <option value="">Социология</option>
                        <option value="">Высшая Математика</option>
                        <option value="">Теория Алгоритмизации</option>
                        <option value="">Теория Игр</option>
                        <option value="">Булева Алгебра</option>
                        <option value="">Системный анализ</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td class="main-section__form data">
                    <input class="i_button" type="submit" name="apply" value="Применить">
                </td>
            </tr>
        </table>
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