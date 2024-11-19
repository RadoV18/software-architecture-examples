package com.example.user.service.impl;

import com.example.user.dto.UserRequestDto;
import com.example.user.dto.UserResponseDto;
import com.example.user.exception.NotFoundException;
import com.example.user.model.AppUser;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        AppUser user = new AppUser();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setAuthorizationCode(userRequestDto.getAuthorizationCode());
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        AppUser savedUser = userRepository.save(user);
        return toUserResponseDto(savedUser);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        Optional<AppUser> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return toUserResponseDto(userOptional.get());
    }

    @Override
    public Boolean isValidAuthorizationCode(String authorizationCode) {
        Optional<AppUser> userOptional = Optional.ofNullable(userRepository.findByAuthorizationCode(authorizationCode));
        if(userOptional.isEmpty()) {
            return false;
        }
        return true;
    }

    public UserResponseDto toUserResponseDto(AppUser user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
