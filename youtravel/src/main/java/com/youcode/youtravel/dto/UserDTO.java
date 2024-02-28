package com.youcode.youtravel.dto;

import com.youcode.youtravel.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {
    @NotBlank(message = "First Name Should not be Empty")
    private String firstname;

    @NotBlank(message = "Last Name Should not be Empty")
    private String lastname;

    @NotBlank(message = "Phone Should not be Empty")
    private String phone;

    @NotBlank(message = "Email Should not be Empty")
    private String email;

    @NotBlank(message = "User Name Should not be Empty")
    private String username;

    @NotBlank(message = "Password Should not be Empty")
    private String password;

    @NotEmpty(message = "Role Type should not be empty")
    private Role role;

    //private String message;
    //private int statusCode;
    //private String token;
    //private String refreshToken;
    //private String expirationTime;


}
