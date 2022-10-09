package com.example.demo.controller;

import com.example.demo.domain.repository.TravelInfoRepository;
import com.example.demo.domain.repository.UserInfoRepository;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserInfoService;
import com.example.demo.session.SessionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class ResController {

    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    TravelInfoRepository travelInfoRepository;

    //    회원가입
    @PostMapping("/user/signup")
    public String execSignup(UserDTO userDTO, BindingResult bindingResult, HttpServletResponse response) throws IOException {
        userInfoService.checkIdDuplication(userDTO);
        userInfoService.joinProc(userDTO, bindingResult);
        response.sendRedirect("http://localhost:8081/user/signin");
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


}
