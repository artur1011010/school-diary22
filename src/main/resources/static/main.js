let btn = $('#button-top');

$(window).scroll(function () {
    if ($(window).scrollTop() > 300) {
        btn.addClass('show');
    } else {
        btn.removeClass('show');
    }
});

btn.on('click', function (e) {
    e.preventDefault();
    $('html, body').animate({scrollTop: 0}, '300');
});

function postAdmin() {
    $.ajax({
        url: "/rest/admin/",
        contentType: "application/json",
        dataType: "json",
        method: "POST",
        success: function (result) {

        }
    })
}