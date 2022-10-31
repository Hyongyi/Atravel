package com.example.demo.controller;


import com.example.demo.domain.repository.TravelInfoRepository;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.TravelInfoService;
import com.example.demo.service.UserInfoService;
import com.example.demo.session.SessionConst;
import com.example.demo.session.SessionManager;
import com.example.demo.validator.CheckUsernameValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class PageController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    TravelInfoService travelInfoService;
    @Autowired
    TravelInfoRepository travelInfoRepository;
    private final CheckUsernameValidator checkUsernameValidator;

    @Autowired
    SessionManager sessionManager;

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkUsernameValidator);
    }

//         메인 페이지
    @GetMapping("/purchaseTicket")
    public String CompPurchase() {
        return "/result";
    }

    @GetMapping("/")

    public String index() {
        return "/page";
    }

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String dispSignup() {
        return "/signup";
    }


    // 로그인 페이지
    @GetMapping("/user/signin")
    public String dispSignin(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) UserDTO userDTO,
                             HttpServletRequest request, Model model) throws IOException {
        model.addAttribute("UserDTO", userDTO);
        return "/signin";
    }

    // 로그인 결과 페이지
    @GetMapping("/user/signin/result")
    public String dispLoginResult() {

        return "/loginSucess";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String dispLogout(HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/info")
    public String dispMyInfo() {
        return "/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "/admin";
    }

    // 어드민 페이지
    @GetMapping("/none")
    public String none() {
        return "/none";
    }

//
//    @RequestMapping("/booking")
//    public ModelAndView dispbook(@RequestParam String departdate, @RequestParam String departlocation, @RequestParam String arrivallocation, @RequestParam int peoplenum) throws Exception {
//        List<Object> travelDTO = travelInfoRepository.searchOnewayTicket(departdate,departlocation,arrivallocation);
//        System.out.println("-----"+travelDTO);
//        ModelAndView mv = new ModelAndView("/booking");
//        mv.addObject("ticket", travelDTO);
//        mv.addObject("byFlighthour", Comparator.comparing(TravelDTO::getFlighthour));
//        mv.addObject("peoplenum", peoplenum);
//        return mv;
//    }

    @RequestMapping("/booking")
    public String dispbook(@RequestParam String departdate, @RequestParam String departlocation, @RequestParam String arrivallocation, @RequestParam int peoplenum, Model model) {
        List<Object> travelDTO = travelInfoRepository.searchOnewayTicket(departdate, departlocation, arrivallocation);
        List<Object> travelDTO1 = travelInfoRepository.searchOnewayTicketbyFlighthour(departdate, departlocation, arrivallocation);
        System.out.println("-----" + travelDTO);
        if (CollectionUtils.isEmpty(travelDTO)) {
            model.addAttribute("depart", departlocation);
            model.addAttribute("arrival", arrivallocation);
            model.addAttribute("peoplenum", peoplenum);
            model.addAttribute("departdate", departdate);
            return "/none";
        }
        model.addAttribute("ticket", travelDTO);
        model.addAttribute("ticketH", travelDTO1);
        model.addAttribute("peoplenum", peoplenum);
        return "/booking";
    }

    @RequestMapping("/booking-round")
    public String disproundbook(@RequestParam String departdate, @RequestParam String departlocation, @RequestParam String arrivallocation, @RequestParam int peoplenum, @RequestParam String returndate, Model model) throws Exception {
        List<Object> travelDTO = travelInfoRepository.searchOnewayTicket(departdate, departlocation, arrivallocation);
        List<Object> travelDTO3 = travelInfoRepository.searchOnewayTicketbyFlighthour(departdate, departlocation, arrivallocation);
        List<Object> travelDTO1 = travelInfoRepository.searchRoundTicket(returndate, departlocation, arrivallocation);
        List<Object> travelDTO2 = travelInfoRepository.searchRoundTicketbyflighthour(returndate, departlocation, arrivallocation);
        if (CollectionUtils.isEmpty(travelDTO) || CollectionUtils.isEmpty(travelDTO1)) {
            model.addAttribute("depart", departlocation);
            model.addAttribute("arrival", arrivallocation);
            model.addAttribute("peoplenum", peoplenum);
            model.addAttribute("departdate", departdate);
            return "/none";
        }
        System.out.println("-----" + travelDTO);
        System.out.println("-----" + travelDTO1);
        model.addAttribute("ticket", travelDTO);
        model.addAttribute("ticketH", travelDTO2);
        model.addAttribute("ticketRH", travelDTO3);
        model.addAttribute("ticketround", travelDTO1);
        model.addAttribute("peoplenum", peoplenum);
        return "/booking-round";
    }

//    @GetMapping ("/purchase" )
//    public String disppurchase() {
//        return "/purchase";
//    }

}
//    // 회원가입 처리
//    @PostMapping("/user/signup")
//    public String execSignup(@Valid UserDTO userDTO, BindingResult bindingResult) {
//        userInfoService.joinProc(userDTO,bindingResult);
//        return "signin";
//    }

// 회원가입 처리
//    @PostMapping("/user/signup")
//    public String joinProc(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
//
//        /* 검증 */
//        if(bindingResult.hasErrors()) {
//            /* 회원가입 실패 시 입력 데이터 값 유지 */
//            model.addAttribute("userDto", userDTO);
//
//            /* 유효성 검사를 통과하지 못 한 필드와 메시지 핸들링 */
//            Map<String, String> errorMap = new HashMap<>();
//
//            for(FieldError error : bindingResult.getFieldErrors()) {
//                errorMap.put("valid_"+error.getField(), error.getDefaultMessage());
//                log.info("error message : "+error.getDefaultMessage());
//            }
//
//            /* 회원가입 페이지로 리턴 */
//            return "/signup";
//        }
//
//        // 회원가입 성공 시
//        userInfoService.SignIn(userDTO);
//        return "/signin";
//    }

//    @GetMapping("/booking")
//    public String dispbook(@RequestParam String departdate, @RequestParam String departlocation, @RequestParam String arrivallocation, Model model){
//        List travelDTO = travelInfoRepository.searchOnewayTicket(departdate,departlocation,arrivallocation);
//        System.out.println("-----"+travelDTO);
//        model.addAttribute("ticket", travelDTO);
//        return "/booking";
//    }