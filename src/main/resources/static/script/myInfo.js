$(document).ready(function () {
        let userId = {
            userinfo_id: $(".user_id").text().slice(0, -1)
        }

        console.log(userId)

        window.onload = function () {

            $.ajax({

                url: "/GetUserInfo", // 클라이언트가 요청을 보낼 서버의 URL 주소

                data: userId,                // HTTP 요청과 함께 서버로 보낼 데이터

                type: "POST",                             // HTTP 요청 방식(GET, POST)

                dataType: "JSON",                         // 서버에서 보내줄 데이터의 타입

                success: function (data) {
                    console.log(data)
                        $(".myInfo").empty();
                        let myInfo = ``;
                        myInfo = `
                        <h1 style="text-align: center">내 정보</h1>
                        <div class="myInfo-box">
                            <p>이름</p>
                            <span id="name">${data.name}</span>
                            <p>생년월일</p>
                            <span id="birth">${data.age}</span>
                            <p>성별</p>
                            <span id="sex"> ${data.sex}</span>
                             <p>주소</p>
                            <span id="address">(${data.addressCode}) ${data.address} ${data.addressDetail} ${data.addressRef}</span>
                        </div>`;
                        $(".myInfo").append(myInfo)
                }
            })
            $.ajax({

                url: "/result", // 클라이언트가 요청을 보낼 서버의 URL 주소

                data: userId,                // HTTP 요청과 함께 서버로 보낼 데이터

                type: "POST",                             // HTTP 요청 방식(GET, POST)

                dataType: "JSON",                         // 서버에서 보내줄 데이터의 타입

                success: function (data) {

                    $("#result").empty()
                    let noData = ``;
                    noData = ` 
                <div id="noTicket">
                    <h2>예약내역이 없습니다.</h2>
                </div>`;

                    if (data.length === 0 || data === 0) {
                        $("#result").append(noData)
                    }
                    console.log(data)

                    html = ``;


                    $.each(data, function (i, item) {

                        html = `
                <div id="ticket">
                <img src="/images/AIRLINE.svg" alt="airline">
                <div id="ticket-list">
                    <div class="ticket-content-left tk">
                        <p class="ticket-left slicetime">${item.departdate}</p>
                        <p class="ticket-left slicetime">${item.departtime.substring(0,5)}</p>
                        <p name="departlocation">${item.departlocation}</p>
                    </div>
                    <div class="ticket-content-middle tk">
                        <p class="ticket-middle">${item.flighthour + ' 시간'}</p>
                        <p class="ticket-middle">
                            <img src="/images/6875967_flight_fly_plane_icon.png" alt="비행기">
                        </p>
                        <p class="ticket-middle">직항</p>
                    </div>
                    <div class="ticket-content-right tk">
                         <p class="ticket-right slicetime">${item.arrivaldate}</p>
                        <p class="ticket-right slicetime">${item.arrivaltime.substring(0,5)}</p>
                        <p name="arrivallocation">${item.arrivallocation}</p>
                    </div>
                </div>
                </div>
            `;
                        $("#result").append(html)


                    })

                }
            })
        }
    }
)
;