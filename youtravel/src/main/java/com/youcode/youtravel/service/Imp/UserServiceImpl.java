package com.youcode.youtravel.service.Imp;

import com.youcode.youtravel.dto.ResponseDto.CarDTOResp;
import com.youcode.youtravel.dto.ResponseDto.UserDTOResp;
import com.youcode.youtravel.entities.User;
import com.youcode.youtravel.enums.Role;
import com.youcode.youtravel.exception.ResourceNotFoundException;
import com.youcode.youtravel.repositories.UserRepository;
import com.youcode.youtravel.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTOResp getUserById(Long id) {
        User user = userRepository.findByUid(id)
                .orElseThrow(()-> new ResourceNotFoundException("Join not found with id "+id));
            return modelMapper.map(user, UserDTOResp.class);

    }

    @Override
    public List<UserDTOResp> getUsersByUserRole() {
        Role userRole = Role.BASE_USER;
        return userRepository.findByRole(userRole).stream().map(user -> modelMapper.map(user, UserDTOResp.class)).collect(Collectors.toList());
    }

    @Override
    public List<UserDTOResp> getAllUsers() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDTOResp.class)).collect(Collectors.toList());
    }



    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
