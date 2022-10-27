
$(document).ready(function () {
    $(".header_wrap").remove();
    $(".itinery>.ticket").mouseenter(function() {
        $(this).find(".ticket-list").css('box-shadow', '4px 4px 10px 2px gray')
        $(this).find(".ticket-list").next().css('box-shadow', '4px 4px 10px 2px gray')
    })

    $(".itinery>.ticket").mouseleave(function() {
        $(this).find(".ticket-list").css('box-shadow', 'none')
        $(this).find(".ticket-list").next().css('box-shadow', 'none')
    })
})