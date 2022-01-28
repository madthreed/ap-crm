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