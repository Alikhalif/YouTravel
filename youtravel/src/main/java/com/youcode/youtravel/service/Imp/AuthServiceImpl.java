package com.youcode.youtravel.service.Imp;

import com.youcode.youtravel.dto.AuthDTO.AuthRequestDTO;
import com.youcode.youtravel.dto.AuthDTO.AuthResponseDTO;
import com.youcode.youtravel.dto.AuthDTO.RegisterRequestDTO;
import com.youcode.youtravel.entities.Token;
import com.youcode.youtravel.entities.User;
import com.youcode.youtravel.enums.Role;
import com.youcode.youtravel.repositories.TokenRepository;
import com.youcode.youtravel.repositories.UserRepository;
import com.youcode.youtravel.security.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;


    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }


    public AuthResponseDTO register(RegisterRequestDTO request) {

        // check if user already exist. if exist than authenticate the user
        if(userRepository.findByEmail(request.getUsername()).isPresent()) {
            return new AuthResponseDTO("User already exist");
        }
        try {
            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .phone(request.getPhone())
                    .email(request.getEmail())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.BASE_USER)
                    .build();


            user = userRepository.save(user);

            String jwt = jwtService.generateToken(user);

            Long user_id = user.getUid();
            String username = user.getUsername();
            String role = user.getRole().name();

            saveUserToken(jwt, user);

            return AuthResponseDTO.builder()
                    .token(jwt)
                    .build();

        } catch (DataIntegrityViolationException e) {
            return new AuthResponseDTO(e.getMessage());
        }

    }

    public AuthResponseDTO login(AuthRequestDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getEmail()).orElseThrow();
        String jwt = jwtService.generateToken(user);




        revokeAllTokenByUser(user);
        saveUserToken(jwt, user);

        return AuthResponseDTO.builder()
                .token(jwt).build();

    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getUid());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }
    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }





}
