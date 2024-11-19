package com.example.user.service;

import com.example.user.dto.UserRequestDto;
import com.example.user.dto.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);
    UserResponseDto getUserById(Long id);

    Boolean isValidAuthorizationCode(String authorizationCode);
}
