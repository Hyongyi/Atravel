$(document).ready(function () {
    $(".header_wrap").remove();

    var key = $(".key").val();
    var people = $(".select>#peoplenum").text();
    // var people1 = $(".peoplenum").text();
    // console.log(people1)
    console.log(key)
    $("#section-tab>button").mouseenter(function() {
        $(this).css('background-color', '#00005e')
        $(this).find("p").css('color', 'white')
    })
    $("#section-tab>button").mouseleave(function(){
        $(this).css('background-color', '#ebebeb')
        $(this).find("p").css('color', 'black')
    })

    $(".itinery>.ticket").mouseenter(function() {
        $(this).find(".ticket-list").css('box-shadow', '4px 4px 10px 2px gray')
        $(this).find(".ticket-list").next().css('box-shadow', '4px 4px 10px 2px gray')
    })

    $(".itinery>.ticket").mouseleave(function() {
        $(this).find(".ticket-list").css('box-shadow', 'none')
        $(this).find(".ticket-list").next().css('box-shadow', 'none')
    })

    $(".itinery>.ticket-round").mouseenter(function() {
        $(this).find(".ticket-list").css('box-shadow', '4px 4px 10px 2px gray')
        $(this).find(".ticket-list").next().css('box-shadow', '4px 4px 10px 2px gray')
    })

    $(".itinery>.ticket-round").mouseleave(function() {
        $(this).find(".ticket-list").css('box-shadow', 'none')
        $(this).find(".ticket-list").next().css('box-shadow', 'none')
    })


    $(".itinery>.ticket").click(function() {
        var ticketno = $(this).find(".key").val();
        sessionStorage.setItem("ticketno", ticketno);
        sessionStorage.setItem("peoplenum", people);
        console.log(sessionStorage.getItem("ticketno"));
        console.log(sessionStorage.getItem("peoplenum"));


        $.ajax({

            url: "/purchase", // 클라이언트가 요청을 보낼 서버의 URL 주소

            data: "ticketno="+ticketno,                // HTTP 요청과 함께 서버로 보낼 데이터

            type: "GET",                             // HTTP 요청 방식(GET, POST)

            dataType: "text",                         // 서버에서 보내줄 데이터의 타입

            success : function (data) {
                var obj = JSON.parse(data);


                console.log(JSON.parse(data));
                console.log(obj[0].price * people);
                console.log(people);
                // JSON.parse(data);
                $("#content-book").hide();
                $("#content").show();
                $("#price").text(addComma(obj[0].price * people) +' 원');
                $("#totalprice").text(addComma(obj[0].price  * people) +' 원');
                for (var i = 1; i<people; i++) {
                    var ticketinfo = $("#addpeoplenum").clone(true);
                    $("#addpeoplenum").after(ticketinfo);
                }
            }

        })

    })


    $(".itinery>.ticket-round").click(function() {
        var ticketnoA = $(this).find(".key1").val();
        var ticketnoB = $(this).find(".key2").val();
        sessionStorage.setItem("Round1", ticketnoA);
        sessionStorage.setItem("Round2", ticketnoB);
        sessionStorage.setItem("peoplenum", people);
        console.log(sessionStorage.getItem("Round1"));
        console.log(sessionStorage.getItem("Round2"));
        console.log(sessionStorage.getItem("peoplenum"));


        var params = {ticketno1 :ticketnoA,
            ticketno2 : ticketnoB };
        $.ajax({

            url: "/purchase-round", // 클라이언트가 요청을 보낼 서버의 URL 주소

            data: params,                // HTTP 요청과 함께 서버로 보낼 데이터

            type: "GET",                             // HTTP 요청 방식(GET, POST)
            success : function (data) {

                console.log(data);
                console.log(data[0].price);

                $("#content-book").hide();
                $("#content").show();
                $("#br-price").text(addComma((data[0].price+ data[1].price) * people) +' 원');
                $("#totalprice").text(addComma((data[0].price+ data[1].price) * people) +' 원');
                for (var i = 1; i<people; i++) {
                    var ticketinfo = $("#addpeoplenum").clone(true);
                    $("#addpeoplenum").after(ticketinfo);
                }
            }

        })

    })

})
function addComma(value){
    value = value.toLocaleString();
    return value;
}


// $("#price1").text(data);
// $("#price2").text(obj[0].price);
// $("#price1").text(this.price);
// $("#price2").text(this['price']);
// $("#price3").text(data.price);
// $("#price4").text(obj.price);