// function addGradeToStudentById() {
//     let element = $("#id_student").val();
//     let url = "/rest/addGrade/" + element;
//     console.log("addGradeToStudentById()");
//     $.ajax({
//         url: url,
//         contentType: "application/json",
//         dataType: "json",
//         method: "POST",
//         success: function (result) {
//             console.log(result.status);
//         },
//         complete: function (xhr, textStatus) {
//             if (xhr.status === 202) {
//                 getStudentDetails();
//             }
//         }
//     })
// }