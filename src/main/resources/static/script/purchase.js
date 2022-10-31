$(document).ready(function () {


    $("#purchase").click(function () {

        alert("정말로 결제하시겠습니까?")
        let ticketinfo = [];

        let first_name = [];
        let last_name = [];
        let age = [];
        let nationality =[];
        let passportno = [];
        let passportexpire = [];
        let sex = [];
        let phonenum = [];
        let travelinfo_boookingno = [];
        let userinfo_id = [];



        $(".key").each(function (i) {//체크된 리스트 저장
            travelinfo_boookingno.push($(this).val());
        });

        $(".firstname").each(function (i) {//체크된 리스트 저장
            first_name.push($(this).val());
        });

        $(".lastname").each(function (i) {//체크된 리스트 저장
            last_name.push($(this).val());
        });

        $(".age").each(function (i) {//체크된 리스트 저장
            age.push($(this).val());
        });

        $(".nationality").each(function (i) {//체크된 리스트 저장
            nationality.push($(this).val());
        });

        $(".passport").each(function (i) {//체크된 리스트 저장
            passportno.push($(this).val());
        });

        $(".duedate").each(function (i) {//체크된 리스트 저장
            passportexpire.push($(this).val());
        });

        $(".sex").each(function (i) {//체크된 리스트 저장
            sex.push($(this).val());
        });
        $(".phone-num").each(function (i) {//체크된 리스트 저장
            phonenum.push($(this).val());
        });

        // $(".user_id").each(function (i) {//체크된 리스트 저장
        //     userinfo_id.push($(this).innerHTML);
        // });



        for (var i = 0; i < first_name.length; i++) {
            var data = {
                first_name: first_name[i],
                last_name: last_name[i],
                age: age[i],
                nationality: nationality[i],
                passportno: passportno[i],
                passportexpire: passportexpire[i],
                sex: sex[i],
                phonenum: phonenum[i],
                travelinfo_boookingno: travelinfo_boookingno[i],
                userinfo_id: $(".user_id").text()
            }

            ticketinfo.push(data);
        }


        console.log(ticketinfo);
        debugger

        $.ajax({

            url: "/purchaseTicket", // 클라이언트가 요청을 보낼 서버의 URL 주소

            data: {
                ticketinfo : JSON.stringify(ticketinfo)},                // HTTP 요청과 함께 서버로 보낼 데이터

            type: "POST",                             // HTTP 요청 방식(GET, POST)
            traditional : true, //필수
            dataType: "JSON",
            // 서버에서 보내줄 데이터의 타입
            success: function (data) {
                console.log(data)
                let url = "http://localhost:8081/result";
                location.replace(url)
            },
            error : function (error) {
                console.log(error)
            }
        })

    })

    $("#rt-purchase").click(function () {
        alert("정말로 결제하시겠습니까?")
        let ticketinfo = [];

        let first_name = [];
        let last_name = [];
        let age = [];
        let nationality =[];
        let passportno = [];
        let passportexpire = [];
        let sex = [];
        let phonenum = [];
        let travelinfo_boookingno = [];
        let userinfo_id = [];



        $(".key").each(function (i) {//체크된 리스트 저장
            travelinfo_boookingno.push($(this).val());
        });

        $(".firstname").each(function (i) {//체크된 리스트 저장
            first_name.push($(this).val());
        });

        $(".lastname").each(function (i) {//체크된 리스트 저장
            last_name.push($(this).val());
        });

        $(".age").each(function (i) {//체크된 리스트 저장
            age.push($(this).val());
        });

        $(".nationality").each(function (i) {//체크된 리스트 저장
            nationality.push($(this).val());
        });

        $(".passport").each(function (i) {//체크된 리스트 저장
            passportno.push($(this).val());
        });

        $(".duedate").each(function (i) {//체크된 리스트 저장
            passportexpire.push($(this).val());
        });

        $(".sex").each(function (i) {//체크된 리스트 저장
            sex.push($(this).val());
        });
        $(".phone-num").each(function (i) {//체크된 리스트 저장
            phonenum.push($(this).val());
        });

        // $(".user_id").each(function (i) {//체크된 리스트 저장
        //     userinfo_id.push($(this).innerHTML);
        // });



        for (var i = 0; i < first_name.length; i++) {
            var data = {
                first_name: first_name[i],
                last_name: last_name[i],
                age: age[i],
                nationality: nationality[i],
                passportno: passportno[i],
                passportexpire: passportexpire[i],
                sex: sex[i],
                phonenum: phonenum[i],
                // travelinfo_boookingno: travelinfo_boookingno[i],
                userinfo_id: $(".user_id").text()
            }
            ticketinfo.push(data);
        }


        console.log(ticketinfo);
        console.log(travelinfo_boookingno);
        debugger

        $.ajax({

            url: "/purchaseRTTicket", // 클라이언트가 요청을 보낼 서버의 URL 주소
            data: {
                travelinfo_boookingno : JSON.stringify(travelinfo_boookingno),
                ticketinfo : JSON.stringify(ticketinfo)},                // HTTP 요청과 함께 서버로 보낼 데이터

            type: "POST",                             // HTTP 요청 방식(GET, POST)
            traditional : true, //필수
            dataType: "JSON",
            // 서버에서 보내줄 데이터의 타입
            success: function (data) {

                // let url = "http://localhost:8081/";
                // console.log(url);
                // location.replace(url)
            }
        })

    })

});

