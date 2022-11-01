package com.example.demo.dto;



import com.example.demo.domain.Role;
import com.example.demo.domain.entity.UserInfo;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor

public class UserDTO {
    private Long UserNum;

    @NotEmpty(message = "아이디를 입력해주세요.")
    @NotNull
    @NotBlank(message ="아이디를 입력해주세요.")
    @Pattern(regexp="^[A-Za-z0-9]{4,13}$",
            message = "아이디는 숫자를 포함한 영문만 가능합니다.(4자 ~ 13자리)")

    private String id;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @NotNull
    @NotBlank(message ="비밀번호를 입력해주세요.")
    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[0-9]).{6,20}$",
            message = "비밀번호는 영문, 숫자를 포함(6자리 이상)")
    private String password;

    @NotEmpty(message = "이름를 입력해주세요.")
    @NotNull
    @NotBlank(message ="이름를 입력해주세요.")
    @Pattern(regexp="^[가-힣].{2,4}+$",
            message = "이름을 제대로 입력해주세요.")

    private String name;
    
    @NotNull
    private String age;

    @NotEmpty(message = "주소를 입력해주세요.")
    @NotNull
    @NotBlank(message ="주소를 입력해주세요.")
    private String addressCode;

    private String address;

    private String addressDetail;

    private String addressRef;
    

    @NotEmpty(message = "성별을 입력해주세요.")
    @NotNull
    @NotBlank(message ="성별을 입력해주세요.")
    private String sex;
    private Role role;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public UserInfo toEntity(){
        return UserInfo.builder()
                .id(this.id)
                .UserNum(this.UserNum)
                .password(this.password)
                .name(this.name)
                .sex(this.sex)
                .age(this.age)
                .address(this.address)
                .addressCode(this.addressCode)
                .addressDetail(this.addressDetail)
                .addressRef(this.addressRef)
                .build();
    }

    @Builder
    public UserDTO(Long UserNum,String id, String password, String name, String age, String sex, String addressCode, String address, String addressDetail, String addressRef) {
        this.UserNum = UserNum;
        this.id = id;
        this.name = name;
        this.password = password;
        this.age =age;
        this.sex = sex;
        this.addressCode = addressCode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.addressRef = addressRef;


    }
}
