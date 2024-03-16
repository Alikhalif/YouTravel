package com.youcode.youtravel.controller;

import com.youcode.youtravel.dto.AuthDTO.AuthRequestDTO;
import com.youcode.youtravel.dto.AuthDTO.AuthResponseDTO;
import com.youcode.youtravel.dto.AuthDTO.RegisterRequestDTO;
import com.youcode.youtravel.service.Imp.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
public class AuthController {
    private final AuthServiceImpl authService;

    @Autowired
    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        System.out.println("ok");
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO request) {
        System.out.println("login ok !!!");
        return ResponseEntity.ok(authService.login(request));
    }

}

