function getStudentDetails() {
    let id_student = $('#id_student-details').val();
    if (!id_student) {
        let profile_id = $('#profile_id').html();
        id_student = profile_id;
    }
    $.ajax({
        url: "/rest/student/" + id_student,
        contentType: "application/json",
        dataType: "json",
        method: "GET",
        success: function (result) {
            console.log(result)
            populateProfile(result);
        }
    })
}

function populateProfile(data) {
    const $studentId = $('#id-details');
    const $firstName = $('#first-name-details');
    const $lastName = $('#last-name-details');
    const $email = $('#email-details');
    const $birthDate = $('#birth-date-details');
    // todo - prevent undefined error
    // console.log(data.gradeList)
    populateGrades(data.gradeList);
    $studentId.val(data.id);
    $firstName.val(data.firstName);
    $lastName.val(data.lastName);
    $email.val(data.email);
    $birthDate.val(data.birthDate);
}

function populateGrades(grades) {
    const $gradeListEmpty = $('#grade-list-empty');
    const $gradeList = $('#grade-list');
    if (grades === undefined || grades.length === 0) {
        $gradeListEmpty.css("display", "block");
    } else {
        $gradeListEmpty.css("display", "none");
        $gradeList.html(parseGradeToTable(grades));
    }
}

function parseGradeToTable(grades) {
    let result = '';
    const tableHeader = '<table class="table"><thead><tr><th scope="col" class="col-3">Subject:</th><th scope="col" class="col-9">Grades:</th></tr></thead><tbody>';
    const tableEnding = ' </tbody></table>';
    let rows = [];
    let subjects = [];
    grades.forEach(grade => {
        const tempSubject = grade.subject;
        const index = subjects.indexOf(tempSubject);
        if (index === -1) {
            subjects.push(tempSubject);
            rows[subjects.indexOf(tempSubject)] += ' <tr><th scope="row">' + tempSubject + '</th><td>' + parseGrade(grade);
        } else {
            rows[index] += parseGrade(grade);
        }
    })
    result += tableHeader;
    rows.forEach(row => result += (row + '</td></tr>'));
    result += tableEnding;
    // console.log("wynik tworzenia tabeli:\n" + result)
    return result;
}

function parseGrade(grade) {
    // console.log(grade)
    let result = ''
    switch (grade.gradeValueEnum) {
        case 'EXCELLENT':
            result = '<span class="badge badge-success badge-grade">celująca-6</span>';
            break;
        case 'VERY_GOOD':
            result = '<span class="badge badge-success badge-grade">bardzo dobra-5</span>';
            break;
        case 'GOOD':
            result = '<span class="badge badge-success badge-grade">dobra-4</span>';
            break;
        case 'SATISFACTORY':
            result = '<span class="badge badge-secondary badge-grade">dostateczna-3</span>';
            break;
        case 'SUFFICIENT':
            result = '<span class="badge badge-warning badge-grade">dopuszczająca-2</span>';
            break;
        case 'INSUFFICIENT':
            result = '<span class="badge badge-danger badge-grade">niedostateczna-1</span>';
            break;
    }
    return result;
}

function addGradeProfile() {
    let id = $("#id-details").val();
    // if (!id) {
    //     id = $("#id-details").val();
    // }
    console.log("id: " + id)
    const subjectName = $("#add-grade-subject1").val();
    const gradeValue = $("#add-grade-value").val();
    // console.log('grade value:\n' + gradeValue)
    const gradeDTO = {
        gradeValueEnum: gradeValue,
        subjectName: subjectName
    }
    const stringGrade = JSON.stringify(gradeDTO);
    const url = "/rest/grade/" + id;
    console.log("\"/rest/grade/\" + id")
    console.log(gradeDTO)
    $.ajax({
        url: url,
        contentType: "application/json",
        dataType: "json",
        data: stringGrade,
        method: "POST",
        success: function (result) {
            // console.log(result.status);
        },
        complete: function (xhr, textStatus) {
            if (xhr.status === 200) {
                getStudentDetails();
            }
        }
    })
}
