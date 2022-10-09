package com.example.demo.validator;

import com.example.demo.domain.repository.UserInfoRepository;
import com.example.demo.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckUsernameValidator extends AbstractValidator<UserDTO> {
    private final UserInfoRepository userInfoRepository;

    @Override
    protected void doValidate(UserDTO userDTO, Errors errors) {
        if (userInfoRepository.existsById(userDTO.toEntity().getId())) {
            errors.rejectValue("id", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
        }
    }
}
