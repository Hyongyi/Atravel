$(document).ready(function () {
    $(".addpeople").click(function () {
        console.log('ss')
        var clone_div = $("#addpeoplenum").clone(true);
        $("#addpeoplenum").after(clone_div);
        $(this).css("box-shadow", "0 5px 18px -7px rgba(0,0,0,4)");
        setTimeout(function() {clear()},500);
    });


});

function clear() {
    $(".addpeople").css("box-shadow", "none")
}

