package com.example.demo.controller;

import com.example.demo.domain.entity.TicketInfo;
import com.example.demo.domain.entity.UserInfo;
import com.example.demo.domain.repository.TicketInfoRepository;
import com.example.demo.domain.repository.TravelInfoRepository;
import com.example.demo.domain.repository.UserInfoRepository;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserInfoService;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
public class ResController {

    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    TravelInfoRepository travelInfoRepository;

    @Autowired
    TicketInfoRepository ticketInfoRepository;


    //    회원가입
    @PostMapping("/user/signup")
    public String execSignup(UserDTO userDTO, BindingResult bindingResult, HttpServletResponse response) throws IOException {
        userInfoService.checkIdDuplication(userDTO);
        userInfoService.joinProc(userDTO, bindingResult);
        response.sendRedirect("http://localhost:8081/user/signin");
        return "userDTO";
    }

    @RequestMapping(value="/purchaseTicket", method = RequestMethod.POST, produces = "application/json")
    public String purchaseTicket(@RequestParam Map<String, Object> ticketDTO) throws IOException, ParseException {
        System.out.println("ticketDto:=========" + ticketDTO);

        String jsonData = ticketDTO.get("ticketinfo").toString();
        JSONParser parser = new JSONParser();
        Object obj  = parser.parse(jsonData);
        //stringify 된 자료를 다시 json으로 파싱

        ticketDTO.put("ticketinfo", obj);
        //map에다가 파싱된 자료를 다시 넣어줌

        List<Map> temp = (List<Map>) ticketDTO.get("ticketinfo");
        //list 에다 다시 map의 value 값을 넣어줌

        List<TicketInfo> tickets = new ArrayList<>();
        //jpa saveAll을 사용하기 위해 entity를 넣어줄 리스트 생성
        
        for (Map obj1 : temp) {

            TicketInfo ticket = TicketInfo.builder()
                    .first_name(obj1.get("first_name").toString())
                    .last_name(obj1.get("last_name").toString())
                    .age(obj1.get("age").toString())
                    .sex(obj1.get("sex").toString())
                    .nationality(obj1.get("nationality").toString())
                    .passportno(obj1.get("passportno").toString())
                    .passportexpire(obj1.get("passportexpire").toString())
                    .travelinfoBoookingno(Long.parseLong(String.valueOf(obj1.get("travelinfo_boookingno"))))
                    .phonenum(obj1.get("phonenum").toString())
                    .userinfoId(obj1.get("userinfo_id").toString())
                    .build();

            tickets.add(ticket);
            //builder를 이용해서 자료를 넣어준 뒤, 만들어놓은 리스트에다 넣기
        }

        System.out.println(tickets);
        ticketInfoRepository.saveAll(tickets);
        //insert 사용

        return tickets.toString();
    }

    @RequestMapping(value="/purchaseRTTicket", method = RequestMethod.POST, produces = "application/json")
    public String purchaseRTTicket(@RequestParam Map<String, Object> ticketDTO, HttpServletResponse response) throws IOException, ParseException {

        System.out.println("ticketDto:=========" + ticketDTO);


        String jsonData = ticketDTO.get("ticketinfo").toString();
        String jsonData1 = ticketDTO.get("travelinfo_boookingno").toString();
        JSONParser parser = new JSONParser();
        Object obj  = parser.parse(jsonData);
        Object tikNo  = parser.parse(jsonData1);
        //stringify 된 자료를 다시 json으로 파싱

        ticketDTO.put("ticketinfo", obj);
        //map에다가 파싱된 자료를 다시 넣어줌
        ticketDTO.put("ticketNo", tikNo);
        //map에다가 파싱된 자료를 다시 넣어줌

        List<Map> temp = (List<Map>) ticketDTO.get("ticketinfo");
        //list 에다 다시 map의 value 값을 넣어줌

        List<Map> temp1 = (List<Map>) ticketDTO.get("ticketNo");
        //list 에다 다시 map의 value 값을 넣어줌


        System.out.println(temp);
        System.out.println(temp1.get(0));


        List<TicketInfo> tickets = new ArrayList<>();
        //jpa saveAll을 사용하기 위해 entity를 넣어줄 리스트 생성


        for (Map obj1 : temp) {

            TicketInfo ticket = TicketInfo.builder()
                    .first_name(obj1.get("first_name").toString())
                    .last_name(obj1.get("last_name").toString())
                    .age(obj1.get("age").toString())
                    .sex(obj1.get("sex").toString())
                    .nationality(obj1.get("nationality").toString())
                    .passportno(obj1.get("passportno").toString())
                    .passportexpire(obj1.get("passportexpire").toString())
                    .travelinfoBoookingno(Long.parseLong(String.valueOf(temp1.get(0))))
                    .phonenum(obj1.get("phonenum").toString())
                    .userinfoId(obj1.get("userinfo_id").toString())
                    .build();

            tickets.add(ticket);
            //builder를 이용해서 자료를 넣어준 뒤, 만들어놓은 리스트에다 넣기
        }

        for (Map obj1 : temp) {

            TicketInfo ticket = TicketInfo.builder()
                    .first_name(obj1.get("first_name").toString())
                    .last_name(obj1.get("last_name").toString())
                    .age(obj1.get("age").toString())
                    .sex(obj1.get("sex").toString())
                    .nationality(obj1.get("nationality").toString())
                    .passportno(obj1.get("passportno").toString())
                    .passportexpire(obj1.get("passportexpire").toString())
                    .travelinfoBoookingno(Long.parseLong(String.valueOf(temp1.get(1))))
                    .phonenum(obj1.get("phonenum").toString())
                    .userinfoId(obj1.get("userinfo_id").toString())
                    .build();

            tickets.add(ticket);
            //builder를 이용해서 자료를 넣어준 뒤, 만들어놓은 리스트에다 넣기
        }
        //왕복 티켓이기 때문에 편도 티켓 넣는 방식을 두번 사용하여 사람이 여러명일때 티켓 1번으로 여러명 넣어주고, 티켓 2번을 이용하여 여러명 넣어줌

        System.out.println(tickets);
        ticketInfoRepository.saveAll(tickets);
       //insert 사용

        return "userDTO";
    }


    @GetMapping("/checkid")
    public ResponseEntity<?> checkIdDuplicate(@RequestParam(value = "id") String id) throws BadRequestException {
        if (userInfoService.checkUsernameDuplication(id) == true) {
            throw new BadRequestException("이미 사용중인 아이디입니다.");
        } else {
            return ResponseEntity.ok(userInfoService.checkUsernameDuplication(id));
        }
    }

    @GetMapping("/purchase")
    public List<Object> disppurchase(@RequestParam String ticketno, HttpServletResponse response) throws IOException {
        System.out.println("---------" + ticketno);
        long num = Long.parseLong(ticketno);
        List<Object> travelDTO = travelInfoRepository.findTravelInfoById(num);
        System.out.println("---------" + travelDTO);
        return travelDTO;
    }



    @GetMapping("/purchase-round")
    public List<Object> disppurchaseRound(@RequestParam String ticketno1, @RequestParam String ticketno2) throws IOException {
        System.out.println("---------" + ticketno1);
        System.out.println("---------" + ticketno2);
        long num = Long.parseLong(ticketno1);
        long num1 = Long.parseLong(ticketno2);
        List<Object> travelDTO = travelInfoRepository.findTravelInfoById(num);
        List<Object> travelDTO1 =travelInfoRepository.findTravelInfoById(num1);
        List<Object> joined = new ArrayList<>();
        joined.addAll(travelDTO);
        joined.addAll(travelDTO1);
        System.out.println("*******" + joined);
        return joined;
    }


    @PostMapping("/result")
    public List<Object> GoResult(@RequestParam String userinfo_id) {
        List<Object> ticket = ticketInfoRepository.searchBooking(userinfo_id);
        System.out.println("검색 결과: " + ticket);
        return ticket;
    }

    @PostMapping("/GetUserInfo")
    public Optional<UserInfo> GetInfo(@RequestParam String userinfo_id) {
        Optional<UserInfo> myInfo = userInfoRepository.findById(userinfo_id);
        System.out.println("검색 결과: " + myInfo);
        return myInfo;
    }

}
