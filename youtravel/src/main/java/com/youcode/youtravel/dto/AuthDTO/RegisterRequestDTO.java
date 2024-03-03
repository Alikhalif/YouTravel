package com.youcode.youtravel.dto.AuthDTO;

import com.youcode.youtravel.enums.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDTO {
    @NotBlank(message = "First Name Should not be Empty")
    private String firstname;

    @NotBlank(message = "Last Name Should not be Empty")
    private String lastname;

    @NotBlank(message = "Phone Should not be Empty")
    private String phone;

    @NotBlank(message = "Email Should not be Empty")
    private String email;

    @NotBlank(message = "username should be not Empty")
    private String username;

    @NotBlank(message = "password should be not Empty")
    private String password;

    private Role role;

}
