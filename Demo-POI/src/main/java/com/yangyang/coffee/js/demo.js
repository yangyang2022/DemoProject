
alert(" this is demo.js ... ")
$(function () {
    $('#logo').click(function() {
        return $(this).html($(this).html() + "logo");
    });
})