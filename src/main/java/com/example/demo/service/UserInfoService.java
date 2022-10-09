package com.example.demo.service;


import com.example.demo.domain.Role;
import com.example.demo.domain.entity.UserInfo;
import com.example.demo.domain.repository.UserInfoRepository;
import com.example.demo.dto.TravelDTO;
import com.example.demo.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.*;


@Service
@AllArgsConstructor
@Slf4j
@Validated
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;


    @Transactional
    public Long SignUp(UserDTO userDTO) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return userInfoRepository.save(userDTO.toEntity()).getUserNum();
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<UserInfo> userInfoWrapper = userInfoRepository.findById(id);
        UserInfo userInfo = userInfoWrapper.get();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin").equals(id)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userInfo.getId(), userInfo.getPassword(), authorities);
    }



    public String joinProc(@Valid UserDTO userDTO, BindingResult bindingResult) {
//        BindingResult bindingResult
        /* 검증 */
        if (bindingResult.hasErrors()) {
            /* 회원가입 실패 시 입력 데이터 값 유지 */
//            model.addAttribute("userDto", userDTO);
            /* 유효성 검사를 통과하지 못 한 필드와 메시지 핸들링 */
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put("valid_" + error.getField(), error.getDefaultMessage());
                log.info("error message : " + error.getDefaultMessage());

            }
            /* 회원가입 페이지로 리턴 */
            return bindingResult.getFieldError().getDefaultMessage();
        }

        // 회원가입 성공 시
        SignUp(userDTO);
        return "성공";
//        return "redirect:/user/signin";
    }

    @Transactional(readOnly = true)
    public boolean checkUsernameDuplication(String id) {
        return userInfoRepository.existsById(id);
    }

    @Transactional(readOnly = true)
    public void checkIdDuplication(UserDTO userDTO) {
        boolean usernameDuplicate = userInfoRepository.existsById(userDTO.toEntity().getId());
        if (usernameDuplicate) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

//    @Transactional(readOnly = true)
//    public void searchOnewayTicket(TravelDTO travelDTO) {
//
//    }
//
//    @Transactional(readOnly = true)
//    public void searchRoundTicket(TravelDTO travelDTO) {
//
//    }
//
//   if (("SJAU_0001").equals(userDto.getAuth_code())) {
//        authorities.add(new SimpleGrantedAuthority(Role.EMP.getValue()));
//    } else if (("SJAU_0002").equals(userDto.getAuth_code()))  {
//        authorities.add(new SimpleGrantedAuthority(Role.REP.getValue()));
//    } else if (("SJAU_0003").equals(userDto.getAuth_code())) {
//        authorities.add(new SimpleGrantedAuthority(Role.ACC.getValue()));
//    } else if (("SJAU_0004").equals(userDto.getAuth_code())) {
//        authorities.add(new SimpleGrantedAuthority(Role.EM.getValue()));
//    } else if (("SJAU_0005").equals(userDto.getAuth_code())) {
//        authorities.add(new SimpleGrantedAuthority(Role.ADM.getValue()));
//    }

}