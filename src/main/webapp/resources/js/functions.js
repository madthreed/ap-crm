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

    document.getElementById("progressStudentHiddenId").value = checkedStudents[0].value;
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

    // let ids = Array.from(selected).map(el => el.value + " ");

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