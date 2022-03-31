let studentsList = [];
let tempList = [];

function getStudentsList() {
    $.ajax({
        url: "/rest/students",
        contentType: "application/json",
        dataType: "json",
        success: function (result) {
            studentsList = result;
            tempList = result;
            populateStudentsList();
        }
    })
}

function search() {
    tempList = studentsList.slice()
    const searchWord = document.getElementById('form-search').value
    tempList = studentsList.filter(element => {
        if (element.firstName.toLowerCase().includes(searchWord.toLowerCase())) {
            return true;
        } else if (element.lastName.toLowerCase().includes(searchWord.toLowerCase())) {
            return true;
        } else if (element.email.toLowerCase().includes(searchWord.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    })
    populateStudentsList();
}

function add20Students() {
    $.ajax({
        url: "/rest/add20Students",
        contentType: "application/json",
        dataType: "json",
        success: function (result) {
            console.log(result.status);
        },
        complete: function (xhr, textStatus) {
            if (xhr.status === 202) {
                getStudentsList();
            }
        }
    })
}

function populateStudentsList() {
    if (tempList.length > 0) {
        let jsonArr = [];
        for (let element of tempList) {
            jsonArr.push({
                student_id: element.id,
                firstName: element.firstName,
                lastName: element.lastName,
                email: element.email,
                birthDate: element.birthDate,
                gradeList: parseGradeListIntoString(element.gradeList),
            });
        }
        $('#studentListResultWrapper').css("display", "block");
        $('#studentListResult').bootstrapTable('removeAll');
        $('#studentListResult').bootstrapTable('load', jsonArr);
    } else {
        $('#studentListResultWrapper').css("display", "none");
    }
}

function parseGradeListIntoString(gradeList) {
    let resultString = 'grade list: ';
    gradeList.forEach(grade => resultString += `${grade.subject}-${grade.gradeValueEnum} ,`)
    return resultString;
}

function idSorter(a, b) {
    return a - b
}

function nameSorter(a, b) {
    return a.localeCompare(b);
}

function detailFormatter(index, row) {
    let html = []
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':  </b>' + value + '</p>')
    })
    // const deleteButton = "<button type ='button' onClick='deleteStudentById(" + row.student_id + ")' data-toggle='modal'  data-target='#deleteModal' class='btn btn-warning mr-2'>delete student</button>";
    const deleteButton = `<button type ='button' data-toggle='modal'  data-target='#deleteModal' class='btn btn-warning mr-2'>delete student</button>`;
    const goToProfileButton = "<a type ='button' href='../studentProfile/" + row.student_id + "' " +
        "class='btn btn-primary'>Student profile</a>";

    const modal = '<div class="modal fade" id="deleteModal" tabIndex="-1" role="dialog"><div class="modal-dialog" role="document">' +
        '<div class="modal-content"><div class="modal-header"><h5 class="modal-title" id="deleteModal">Are you sure you want to permanently delete user?</h5>' +
        '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
        '</div><div class="modal-footer"><button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> ' +
        '<button type="button" class="btn btn-primary" onClick="deleteStudentById(' + row.student_id + ')" data-dismiss="modal">Delete user</button>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>';

    return "<div class='student-table-details'><h5>Details:</h5></br><div class='student-table-details-row'>" + html.join('') + deleteButton + " " +
        goToProfileButton + modal + "</div>";
}

function deleteStudentById(student_id) {
    const url = "/rest/student/" + student_id;
    setTimeout(function () {
        getStudentsList();
    }, 200);
    $.ajax({
        url: url,
        contentType: "application/json",
        dataType: "json",
        method: "DELETE",
        success: function (result) {
            console.log(result);
        }
    })
}