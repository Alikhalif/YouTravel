package com.youcode.youtravel.dto.ResponseDto;

import com.youcode.youtravel.enums.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDTOResp {
    private Long uid;
    private String firstname;
    private String lastname;

    private String phone;

    private String email;

    private String username;

    private String role;
}
