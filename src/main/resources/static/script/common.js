$(document).ready(function () {
    let sir = ``;
    sir = `<span id="sir">님</span>`

    console.log($(".user_id").text())
    if ($(".user_id").text() === "anonymousUser") {
        $(".user_id").parent().hide();
    } else {
        $(".user_id").append(sir)
    }

    $("#menu>ul>li").mouseenter(function () {
        $("#menu>ul>li>ul").stop().slideDown(200)
        $("#menu>.nav_bg").stop().slideDown(200)
    });
    $("#menu>ul>li").mouseleave(function () {
        $("#menu>ul>li>ul").stop().slideUp(200)
        $("#menu>.nav_bg").stop().slideUp(200)
    });


})