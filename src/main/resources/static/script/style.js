

$(document).ready(function () {



    $(".service-box").hover( function () {
        $(this).find("h2").css("color", "white");
        $(this).find("p").css("color", "white");
    }, function() {
        $(this).find("h2").css("color", "black");
        $(this).find("p").css("color", "black");
    });

    $("#menu>ul>li").mouseenter(function () {
        $("#menu>ul>li>ul").stop().slideDown(200)
        $("#menu>.nav_bg").stop().slideDown(200)
    });
    $("#menu>ul>li").mouseleave(function () {
        $("#menu>ul>li>ul").stop().slideUp(200)
        $("#menu>.nav_bg").stop().slideUp(200)
    });


    // var currentindex = 0;
    // setInterval(function () {
    //     if (currentindex < 2) {
    //         currentindex++;
    //     } else {
    //         currentindex = 0;
    //     }

    //     var next = currentindex * (-1200) + "px";
    //     $("#banner ul").animate({ left: next }, 500);
    // }, 3000);


    $("#banner>ul>li").children("div:gt(0)").hide();
    var current = 0;
    setInterval(function () {
        var next = (current + 1) % 4;
        $("#banner ul").find("div").eq(current).fadeOut();
        $("#banner ul").find("div").eq(next).fadeIn();
        current = next;
    }, 3000);



    /* 슬라이더 메뉴 */
    var num=1;
    setInterval(function(){
        if(num<3){
            $('#info_silde>ul').animate({
                left: '-='+1200
            },'slow');
            num++;
        } else {
            $('#info_silde>ul').animate({
                left: 0
            },'slow');
            num = 1;
        }
    }, 3200);



    window.onload = function () {
        today = new Date();
        console.log("today.toISOString() >>>" + today.toISOString() + 1);
        today = today.toISOString().slice(0, 10);
        console.log("today >>>> " + today);
        bir = document.getElementById("input-deaprt-date");
        bir.value = today;


    }

    $("#swap").click(function () {
        // var x = (220) + 'px';
        // var y = (-170) + 'px';
        // $('#departures').animate({ left: x }, 50)
        // $('#arrivals').animate({ left: y }, 50)
        var x = $("#departures").val() ;
        var y = $("#arrivals").val();
        var temp = x;
        $("#departures").val(y) ;
        $("#arrivals").val(temp);


    })

    $("#round-trip").click(function () {
        $("#return-date").show();
        $("#formId").attr("action", "/booking-round");
    })

    $("#one-way").click(function () {
        $("#return-date").hide();
        $("#formId").attr("action", "/booking");
    })

    $("#search").click( function () {
        var check = $("#round-trip").is(":checked")
        var peoplenum = $("input-peoplenum").val();
        if ($("#departures").val() === null || $("#departures").val() === "" || $("#departures").val() === undefined) {
            $("#departures").css("border-color", "red")
            $(".warning").css('display', 'block')
        } else if ($("#arrivals").val() === null || $("#arrivals").val() === "" || $("#arrivals").val() === undefined) {
            $("#arrivals").css("border-color", "red")
            $(".warning").html("도착지를 선택해주세요.")
            $(".warning").css('display', 'block')
        } else if ($("#input-deaprt-date").val() === null || $("#input-deaprt-date").val() === "" || $("#input-deaprt-date").val() === undefined) {
            $("#input-deaprt-date").css("border-color", "red")
            $(".warning").html("출발일을 선택해주세요.")
            $(".warning").css('display', 'block')
        } else if (check) {
            if($("#input-return-date").val() === null || $("#input-return-date").val() === "" || $("#input-return-date").val() === undefined){
                $("#input-return-date").css("border-color", "red")
                $(".warning").html("돌아올 날을 선택해주세요.")
                $(".warning").css('display', 'block')
            }
            else{
                $(".warning").css('display', 'none')
                $("#search").attr("type", "submit")
            }
        } else {
            $(".warning").css('display', 'none')
            $("#search").attr("type", "submit")
        }
    });
});

