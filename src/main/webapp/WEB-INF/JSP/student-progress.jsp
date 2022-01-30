<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="main-section student-progress">
    <p class="main-section__title student-progress">
        Отображена успеваемость для следующего студента:
    </p>

    <table class="main-section__table student-progress-sngd">
        <thead>
        <tr>
            <th class="students-tbl-header">Фамилия</th>
            <th>Имя</th>
            <th>Группа</th>
            <th>Дата поступления</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="main-section__table col_surname">${student.surname}</td>
            <td class="main-section__table col_name">${student.name}</td>
            <td class="main-section__table col_group">${student.group}</td>
            <td class="main-section__table col_date">${student.date}</td>
        </tr>
        </tbody>
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
