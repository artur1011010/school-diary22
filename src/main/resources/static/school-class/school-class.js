let schoolClassList = [];
let tempSchoolClassList = [];
let teachersList = [];

function addClass() {
    const className = document.getElementById('add-class-name').value;
    console.log("addClass() ", className);
    postNewClass(className);
}

function renderSchoolClassList() {
    getClassList()
    getTeachersList()
}

function postNewClass(className) {
    const schoolClassDTO = {
        schoolClassName: className
    }
    let stringify = JSON.stringify(schoolClassDTO);
    console.log(schoolClassDTO)
    $.ajax({
        url: "/rest/school-class",
        contentType: "application/json",
        data: stringify,
        dataType: "json",
        method: "POST",
        success: function (result) {
            renderSchoolClassList()
        }
    })
}

function getClassList() {
    getTeachersList();
    $.ajax({
        url: "/rest/school-class",
        contentType: "application/json",
        dataType: "json",
        method: "GET",
        success: function (result) {
            schoolClassList = result;
            tempSchoolClassList = result;
            populateClassList();
        }
    })
}

function searchClasses() {
    console.log("searchClasses()");
}

function getTeachersList() {
    $.ajax({
        url: "/rest/teacher",
        contentType: "application/json",
        dataType: "json",
        method: "GET",
        success: function (result) {
            teachersList = result;
        }
    })
}

function populateClassList() {
    if (tempSchoolClassList.length > 0) {
        console.log(tempSchoolClassList)
        let jsonArr = [];
        for (let element of tempSchoolClassList) {
            jsonArr.push({
                class_id: element.classId,
                class_name: element.schoolClassName,
                teacher_name: element.schoolClassTeacherNameAndSurname,
            });
        }
        $('#schoolClassListResultWrapper').css("display", "block");
        $('#schoolClassListResult').bootstrapTable('removeAll');
        $('#schoolClassListResult').bootstrapTable('load', jsonArr);
    } else {
        $('#schoolClassListResultWrapper').css("display", "none");
    }
}

function searchClasses() {
    tempSchoolClassList = schoolClassList.slice()
    const searchWord = document.getElementById('class-form-search').value
    tempSchoolClassList = schoolClassList.filter(element => {
        if (element.classId.toString().toLowerCase().includes(searchWord.toLowerCase())) {
            return true;
        } else if (element.schoolClassName.toLowerCase().includes(searchWord.toLowerCase())) {
            return true;
        } else if (element.schoolClassTeacherNameAndSurname.toLowerCase().includes(searchWord.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    })
    populateClassList();
}

function detailFormatter1(index, row) {
    let html = []
    $.each(row, function (key, value) {
        html.push('<p><b>' + key + ':  </b>' + value + '</p>')
    })
    const deleteButton = `<button type ='button' data-toggle='modal'  data-target='#deleteModal' class='btn btn-warning mr-2'>delete class</button>`;

    const addClassTeacher = ` <button type='button' data-toggle='modal' data-target='#addTeacherModal' class='btn btn-warning m-2'>Add class teacher</button> <div class="modal fade" id="addTeacherModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="addTeacherModalLabel" aria-hidden="true"> <div class="modal-dialog"> <div class="modal-content"> <div class="modal-header"> <h5 class="modal-title" id="addTeacherModalLabel">Change school class teacher</h5> <button type="button" class="close" data-dismiss="modal" aria-label="Close"> <span aria-hidden="true">&times;</span> </button> </div> <div class="modal-body"> <div id="addTeacherBody">${createAddTeacherModalBody(row.classId)} </div> </div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> <button type="button" class="btn btn-primary" 
 onClick="addTeacherToClass(${row.classId})" data-dismiss="modal">Save</button> </div> </div> </div> </div>`;

    const delteModal = '<div class="modal fade" id="deleteModal" tabIndex="-1" role="dialog"><div class="modal-dialog" role="document">' +
        '<div class="modal-content"><div class="modal-header"><h5 class="modal-title" id="deleteModal">Are you sure you want to permanently delete class?</h5>' +
        '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
        '</div><div class="modal-footer"><button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> ' +
        '<button type="button" class="btn btn-primary" onClick="deleteSchoolClassById(' + row.classId + ')" data-dismiss="modal">Delete class</button></div></div></div></div>';


    const modifyClass = "<a type ='button' href='../studentProfile/" + row.classId + "' " +
        "class='btn btn-primary'>Edit</a>";

    return "<div class='student-table-details'><h5>Details:</h5></br><div class='student-table-details-row'>" + html.join('') + deleteButton + " " +
        modifyClass + delteModal + addClassTeacher + "</div>";
}

function createAddTeacherModalBody(id) {
    let result = '';
    teachersList.forEach(element => {
        result += `<div class="custom-control custom-radio"><input type="radio" id="${'teacher-' + element.id + ', classId: ' + id}" name="customRadio" class="custom-control-input custom-radio"></input>`;
        result += `<label class="custom-control-label" for="${'teacher-' + element.id}">${element.firstName + " " + element.lastName}</label></div>`
    })
   return result;
}

function idSorter(a, b) {
    return a - b
}

function nameSorter(a, b) {
    return a.localeCompare(b);
}

function deleteSchoolClassById(class_id) {
    const url = "/rest/school-class/" + student_id;
    setTimeout(function () {
        getStudentsList();
    }, 200);
    $.ajax({
        url: url,
        contentType: "application/json",
        dataType: "json",
        method: "DELETE",
        success: function () {
            populateClassList();
        }
    })
}

function addTeacherToClass(element) {
    console.log('classID: ' + element);
    const elemList = document.getElementsByClassName('custom-radio');
    for (let i = 0; i < elemList.length; i++) {
        if (elemList[i].checked == true) {
            console.log(elemList[i].id)
        }
    }
}
