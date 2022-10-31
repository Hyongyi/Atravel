$(document).ready(function () {
    let sir = ``;
    sir = `<span id="sir">님</span>`

    console.log($(".user_id").text())
    if ($(".user_id").text() === "anonymousUser") {
        $(".user_id").parent().hide();
    } else {
        $(".user_id").append(sir)
    }

})