package com.example.user.controller;

import com.example.user.dto.ResponseDto;
import com.example.user.dto.UserRequestDto;
import com.example.user.dto.UserResponseDto;
import com.example.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @PostMapping
    public ResponseDto<UserResponseDto> createUser(
            @RequestBody UserRequestDto userRequestDto
    ) {
        logger.info("POST /api/v1/users");
        UserResponseDto result = userService.createUser(userRequestDto);
        return ResponseDto.<UserResponseDto>builder()
                .data(result)
                .success(true)
                .message("User created successfully")
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<UserResponseDto> getUserById(
            @PathVariable Long id
    ) {
        logger.info("GET /api/v1/users/{}", id);
        UserResponseDto result = userService.getUserById(id);
        return ResponseDto.<UserResponseDto>builder()
                .data(result)
                .success(true)
                .message("User retrieved successfully")
                .build();
    }

    @GetMapping("/valid/{authorizationCode}")
    public ResponseDto<Boolean> isValidCode(
            @PathVariable String authorizationCode
    ) {
        logger.info("GET /api/v1/users/valid/{}", authorizationCode);
        Boolean result = userService.isValidAuthorizationCode(authorizationCode);
        return ResponseDto.<Boolean>builder()
                .data(result)
                .success(true)
                .message("Authorization code is valid")
                .build();
    }

}
