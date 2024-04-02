package com.youcode.youtravel.service;


import com.youcode.youtravel.dto.AuthDTO.AuthRequestDTO;
import com.youcode.youtravel.dto.AuthDTO.AuthResponseDTO;
import com.youcode.youtravel.dto.AuthDTO.RegisterRequestDTO;

public interface AuthService {
    AuthResponseDTO register(RegisterRequestDTO request);
    AuthResponseDTO login(AuthRequestDTO request);
}
