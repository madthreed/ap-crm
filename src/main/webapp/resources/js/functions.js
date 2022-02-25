/**
 * Created by MadThreeD on 2022.
 */

function progressStudent() {
    let checkedStudents = document.querySelectorAll('input[name=idStudent]:checked');
    if (checkedStudents.length === 0) {
        alert("Выберите хотя бы одного студента!!");
        return;
    }
    if (checkedStudents.length > 1) {
        alert("Выберите только одного студента!!");
        return;
    }

    document.getElementById("progressStudentId").value = checkedStudents[0].value;
    document.getElementById("progressStudentForm").submit();
}

function modifyStudent() {
    let checkedStudents = document.querySelectorAll('input[name=idStudent]:checked');
    if (checkedStudents.length === 0) {
        alert("Выберите хотя бы одного студента!!");
        return;
    }
    if (checkedStudents.length > 1) {
        alert("Выберите только одного студента!!");
        return;
    }

    document.getElementById("modifyStudentHiddenId").value = checkedStudents[0].value;
    document.getElementById("modifyStudentForm").submit();
}

function deleteStudents() {
    let checkedStudents = document.querySelectorAll('input[name=idStudent]:checked');
    if (checkedStudents.length === 0) {
        alert("Выберите хотя бы одного студента!!");
        return;
    }

    let ids = "";
    for (let i = 0; i < checkedStudents.length; i++) {
        ids = ids + checkedStudents[i].value + " ";
    }

    document.getElementById("deleteStudentHiddenId").value = ids;
    document.getElementById("deleteStudentForm").submit();
}

function modifyDiscipline() {
    let checkedDisciplines = document.querySelectorAll('input[name=idDiscipline]:checked');
    if (checkedDisciplines.length === 0) {
        alert("Выберите хотя бы одну дисциплину!!");
        return;
    }
    if (checkedDisciplines.length > 1) {
        alert("Выберите только одну дисциплину!!");
        return;
    }

    document.getElementById("modifyDisciplineHiddenId").value = checkedDisciplines[0].value;
    document.getElementById("modifyDisciplineForm").submit();
}

function deleteDisciplines() {
    let checkedDisciplines = document.querySelectorAll('input[name=idDiscipline]:checked');
    if (checkedDisciplines.length === 0) {
        alert("Выберите хотя бы одну дисциплину!!");
        return;
    }

    let ids = "";
    for (let i = 0; i < checkedDisciplines.length; i++) {
        ids = ids + checkedDisciplines[i].value + " ";
    }

    document.getElementById("deleteDisciplineHiddenId").value = ids;
    document.getElementById("deleteDisciplineForm").submit();
}

function createTerm() {
    let duration = document.getElementById('duration').value;
    let checkedDisciplines = document.querySelectorAll('#disciplineSelector option:checked');

    if (checkedDisciplines.length === 0) {
        alert("Выберите хотя бы одну дисциплину!!");
        return;
    }

    if (duration.length === 0) {
        alert("Введите длительность семестра!!");
        return;
    }

    let ids = "";
    for (let i = 0; i < checkedDisciplines.length; i++) {
        ids = ids + checkedDisciplines[i].value + " ";
    }

    document.getElementById("createTermHiddenDuration").value = duration;
    document.getElementById("createTermHiddenIds").value = ids;
    document.getElementById("createTermForm").submit();
}

function deleteTerm() {
    let checkedTerm = document.querySelector('#termSelector option:checked');

    if (checkedTerm.length === 0) {
        alert("Выберите семестр!!");
        return;
    }

    document.getElementById("deleteTermHiddenId").value = checkedTerm.value;
    document.getElementById("deleteTermForm").submit();
}

function modifyTerm() {
    let checkedTerm = document.querySelector('#termSelector option:checked');

    if (checkedTerm.length === 0) {
        alert("Выберите семестр!!");
        return;
    }

    document.getElementById("modifyTermHiddenId").value = checkedTerm.value;
    document.getElementById("modifyTermForm").submit();
}

function clearSelectedDisciplines(){
    const elements = document.getElementById("disciplineSelector").options;

    for(let i = 0; i < elements.length; i++){
        elements[i].selected = false;
    }
}