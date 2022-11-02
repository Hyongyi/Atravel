var chkid = /^[A-Za-z0-9]{4,12}$/;
var chkpw = /^(?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$/;;
var chkname = /^[가-힣]{2,4}$/;
var chkNum = /^[0-9]+$/;

$(document).ready(function () {

    // $("#menu").remove();
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

    $("#input-pw").blur(function () {
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

    $("#sample6_postcode").blur(function () {
        if ($(this).val() === null || $(this).val() === '' || $(this).val() === undefined) {
            $(this).css("border-color", "red")
            $(this).siblings('.warning').css('display', 'block')
        } else {
            $(this).css("border-color", "black")
            $(this).siblings('.warning').css('display', 'none')
        }
    })

    $("#sample6_detailAddress").blur(function () {
        if ($(this).val() === null || $(this).val() === '' || $(this).val() === undefined) {
            $(this).css("border-color", "red")
            $(this).siblings('.warning').text("상세주소를 입력하세요")
            $(this).siblings('.warning').css('display', 'block')
        } else {
            $(this).css("border-color", "black")
            $(this).siblings('.warning').css('display', 'none')
        }
    })

    $("#input-pw-confirm").blur(function () {
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

    $("#input-name").blur(function () {
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


    $("#input-age").blur(function () {
        if ($(this).val() === null || $(this).val() === '' || $(this).val() === undefined) {
            $(this).css("border-color", "red")
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
    } else if($("#sample6_postcode").val() === null ||$("#sample6_postcode").val() === '' || $("#sample6_postcode").val() === undefined) {
        $("#sample6_postcode").focus()
        return false;
    } else if($("#sample6_detailAddress").val() === null ||$("#sample6_detailAddress").val() === '' || $("#sample6_detailAddress").val() === undefined) {
        $("#sample6_detailAddress").focus()
        return false;
    } else if ($("#input-age").val() === null || $("#input-age").val() === '' || $("#input-age").val() === undefined) {
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

function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;

            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();

}