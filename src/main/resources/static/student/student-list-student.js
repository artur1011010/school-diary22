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

function idSorter(a, b) {
    return a - b
}

function nameSorter(a, b) {
    return a.localeCompare(b);
}