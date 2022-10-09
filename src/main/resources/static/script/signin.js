$(document).ready(function () {
    $("#id").focusout(function () {
        console.log('ss')
        var chkid = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
        if ($(this).val() === null || $(this).val() === '' || $(this).val() === undefined) {
            $(this).css("border-color", "red")
            $(this).next().css('display', 'block')
        } else if (chkid.test($(this).val())) {
            $(this).css("border-color", "red")
            $(this).next().text("올바른 아이디를 입력해주세요.")
            $(this).next().css('display', 'block')
        } else {
            $(this).css("border-color", "black")
            $(this).next().css('display', 'none')
        }
    })

    $("#password").focusout(function () {
        console.log('ss')
        if ($(this).val() === null || $(this).val() === '' || $(this).val() === undefined) {
            $(this).css("border-color", "red")
            $(this).next().css('display', 'block')
        } else {
            $(this).css("border-color", "black")
            $(this).next().css('display', 'none')
        }
    });
    $("#remember").click(function () {
        if ($("input:checkbox[id='remember']").is(":checked")) {
            Cookies.set("rememberMe", true);
        } else {
            Cookies.set("rememberMe", false);
        }
        alert("rememberMe")
    })
    if (Cookies.get("rememberMe") === 'true') {
        $("input:checkbox[id='remember']").prop("checked", true);
    }

    $("#id").val(Cookies.get('key'));
    if($("#id").val() != ""){
        $("#remember").attr("checked", true);
    }

    $("#remember").change(function(){
        if($("#remember").is(":checked")){
            Cookies.set('key', $("#id").val(), { expires: 7 });
        }else{
            Cookies.remove('key');
        }
    });

    $("#id").keyup(function(){
        if($("#remember").is(":checked")){
            Cookies.set('key', $("#id").val(), { expires: 7 });
        }
    });


});

function login() {
    if ($("#id").val() === null || $("#id").val() === '' || $("#id").val() === undefined) {
        $("#id").focus();
        return false;
    } else if ($("#password").val() === null || $("#password").val() === '' || $("#password").val() === undefined) {
        $("#password").focus();
        return false;
    } else {
        $("#signin").prop('type', 'submit');

    }


}