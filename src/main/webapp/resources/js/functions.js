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

    document.getElementById("modifyStudentHidden").value = checkedStudents[0].value;
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

    document.getElementById("deleteStudentHidden").value = ids;
    document.getElementById("deleteStudentForm").submit();
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

    document.getElementById("deleteDisciplineHidden").value = ids;
    document.getElementById("deleteDisciplineForm").submit();
}
