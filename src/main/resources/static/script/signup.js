var chkid = /^[A-Za-z0-9]{4,12}$/;
var chkpw = /^(?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$/;;
var chkname = /^[가-힣]{2,4}$/;
var chkNum = /^[0-9]+$/;

$(document).ready(function () {

    var id = $("#input-id").val();
    var pw = $("#input-pw").val();
    var pw_confirm = $("#input-pw-confirm").val();
    var name = $("#input-name").val();
    var age = $("#input-age").val();

    $("#input-id").blur(function () {
        if ($(this).val() === null || $(this).val() === '' || $(this).val() === undefined) {
            $(this).css("border-color", "red")
            $(this).next().css('display', 'block')
        } else if (!chkid.test($(this).val())) {
            $(this).css("border-color", "red")
            $(this).next().text("아이디는 숫자를 포함한 영문만 가능합니다.(4자 ~ 13자리)")
            $(this).next().css('display', 'block')
        } else {
            $(this).css("border-color", "black");
            $(this).next().css('display', 'none');
            $.ajax({
                type: "get",            // HTTP method type(GET, POST) 형식이다.
                url: "/checkid",      // 컨트롤러에서 대기중인 URL 주소이다.
                data: {id: $("#input-id").val()},
                dataType: "json",        // Json 형식의 데이터이다.
                success: function (res) { // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
                    // 응답코드 > 0000
                    $("#input-id").next().css('display', 'block');
                    $("#input-id").next().text('사용 가능한 아이디입니다.')
                    $("#input-id").next().css('color', 'green');
                },
                error: function (error) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.$("#input-id").next().html('사용할 수 있는 아이디입니다.');
                    $("#input-id").next().css('color', 'red');
                    $("#input-id").next().html('사용할 수 없는 아이디입니다.');
                    $("#input-id").next().css('display', 'block');

                }

            });

        }
    })

    $("#input-pw").focusout(function () {
        if ($(this).val() === null || $(this).val() === '' || $(this).val() === undefined) {
            $(this).css("border-color", "red")
            $(this).next().css('display', 'block')
        } else if (!chkpw.test(($(this).val()))) {
            $(this).css("border-color", "red")
            $(this).next().text("비밀번호는 영문, 숫자를 포함(6자리 이상)")
            $(this).next().css('display', 'block')
        } else {
            $(this).css("border-color", "black")
            $(this).next().css('display', 'none')
        }
    })

    $("#input-pw-confirm").focusout(function () {
        if ($(this).val() === null || $(this).val() === '' || $(this).val() === undefined) {
            $(this).css("border-color", "red")
            $(this).next().css('display', 'block')
        } else if ($(this).val() !== $("#input-pw").val()) {
            $(this).css("border-color", "red")
            $(this).next().text("비밀번호가 맞지 않습니다.")
            $(this).next().css('display', 'block')
        } else {
            $(this).css("border-color", "black")
            $(this).next().text("비밀번호가 일치합니다.")
            $(this).next().css('display', 'block')
            $(this).next().css('color', 'green');
        }
    })

    $("#input-name").focusout(function () {
        if ($(this).val() === null || $(this).val() === '' || $(this).val() === undefined) {
            $(this).css("border-color", "red")
            $(this).next().css('display', 'block')
        } else if (!chkname.test($(this).val())) {
            $(this).css("border-color", "red")
            $(this).next().text("이름을 제대로 입력해주세요.")
            $(this).next().css('display', 'block')
        } else {
            $(this).css("border-color", "black")
            $(this).next().css('display', 'none')
        }
    })


    $("#input-age").focusout(function () {
        if ($(this).val() === null || $(this).val() === '' || $(this).val() === undefined) {
            $(this).css("border-color", "red")
            $(this).next().css('display', 'block')
        } else if (!chkNum.test($(this).val())) {
            $(this).css("border-color", "red")
            $(this).next().text("숫자만 입력해주세요.")
            $(this).next().css('display', 'block')
        } else {
            $(this).css("border-color", "black")
            $(this).next().css('display', 'none')
        }
    })

})

function fnSubmit() {
    if ($("#input-id").val() === null || $("#input-id").val() === '' || $("#input-id").val() === undefined) {
        $("#input-id").focus();
        return false;
    } else if (!chkid.test($("#input-id").val())) {
        $("#input-id").focus();
        return false;
    } else if ($("#input-pw").val() === null || $("#input-pw").val() === '' || $("#input-pw").val() === undefined) {
        $("#input-pw").focus();
        return false;
    } else if (!chkpw.test($("#input-pw").val())) {
        $("#input-pw").focus();
        return false;
    } else if ($("#input-pw-confirm").val() === null || $("#input-pw-confirm").val() === '' || $("#input-pw-confirm").val() === undefined) {
        $("#input-pw-confirm").focus()
        return false;
    } else if ($("#input-pw").val() !== $("#input-pw-confirm").val()) {
        $("#input-pw-confirm").focus()
        return false;
    } else if ($("#input-name").val() === null || $("#input-name").val() === '' || $("#input-name").val() === undefined) {
        $("#input-name").focus()
        return false;
    } else if (!chkname.test($("#input-name").val())) {
        $("#input-name").focus()
        return false;
    } else if ($("#input-age").val() === null || $("#input-age").val() === '' || $("#input-age").val() === undefined) {
        $("#input-age").focus()
        return false;
    } else if (!chkNum.test($("#input-age").val())) {
        $("#input-age").focus()
        return false;
    } else if ($('input:radio[name="sex"]').is(':checked') === false) {
        $("#div-female").next().css("display", "block")
        return false;
    } else if($('.warning').html() === "사용할 수 없는 아이디입니다.") {
        $("#input-id").focus();
    } else {

        $("#signup").prop('type', 'submit');
        $.ajax({
            type: "POST",            // HTTP method type(GET, POST) 형식이다.
            url: "/user/signup",      // 컨트롤러에서 대기중인 URL 주소이다.
            data: data,            // Json 형식의 데이터이다.
            dataType: "json",
            success: function (res) { // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
                // 응답코드 > 0000
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
                alert("통신 실패.")
            }

        });

    }
}
