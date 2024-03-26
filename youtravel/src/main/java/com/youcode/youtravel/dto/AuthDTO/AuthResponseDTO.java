package com.youcode.youtravel.dto.AuthDTO;

import com.youcode.youtravel.dto.ResponseDto.UserDTOResp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDTO {
    private String token;
    private UserDTOResp userDTOResp;

    public AuthResponseDTO(String message) {
    }
}
