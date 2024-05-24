package com.youcode.youtravel.service;

import com.youcode.youtravel.dto.ResponseDto.UserDTOResp;
import com.youcode.youtravel.entities.User;

import java.util.List;

public interface UserService {
    UserDTOResp getUserById(Long id);
    List<UserDTOResp> getUsersByUserRole();
    List<UserDTOResp> getAllUsers();
    User saveUser(User user);
    void deleteUser(Long id);
}
