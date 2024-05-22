package com.youcode.youtravel.service.Imp;

import com.youcode.youtravel.dto.AuthDTO.AuthRequestDTO;
import com.youcode.youtravel.dto.AuthDTO.AuthResponseDTO;
import com.youcode.youtravel.dto.AuthDTO.RegisterRequestDTO;
import com.youcode.youtravel.dto.ResponseDto.UserDTOResp;
import com.youcode.youtravel.entities.Token;
import com.youcode.youtravel.entities.User;
import com.youcode.youtravel.enums.Role;
import com.youcode.youtravel.enums.TokenType;
import com.youcode.youtravel.repositories.TokenRepository;
import com.youcode.youtravel.repositories.UserRepository;
import com.youcode.youtravel.security.JwtService;
import com.youcode.youtravel.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;


    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager,
                                 ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
    }


    @Override
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




            var jwtToken = jwtService.generateToken(user);

            saveUserToken(user, jwtToken);

            return AuthResponseDTO.builder()
                    .token(jwtToken)
                    .userDTOResp(modelMapper.map(user, UserDTOResp.class))
                    .build();

        } catch (DataIntegrityViolationException e) {
            return new AuthResponseDTO(e.getMessage());
        }

    }

    @Override
    public AuthResponseDTO login(AuthRequestDTO request){


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        System.out.println(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("lastname", user.getLastname());
        claims.put("password", user.getPassword());
        claims.put("role", user.getRole().name());

        String jwt = jwtService.generateToken(claims,user);



        return AuthResponseDTO.builder()
                .token(jwt)
                .userDTOResp(modelMapper.map(user, UserDTOResp.class)).build();

    }



    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false).build();
        tokenRepository.save(token);
    }


}
