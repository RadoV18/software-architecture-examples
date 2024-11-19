package com.radov18.user.mapper;


import com.radov18.user.entity.User;
import com.radov18.user.security.dto.KeycloakUserResDto;

public class UserMapper {
    public static User toEntityFromKeycloakUserResDto(KeycloakUserResDto keycloakUserDto) {
        User user = new User();
        user.setKeycloakId(keycloakUserDto.getId());
        user.setUsername(keycloakUserDto.getUsername());
        user.setFirstName(keycloakUserDto.getFirstName());
        user.setLastName(keycloakUserDto.getLastName());
        user.setEmail(keycloakUserDto.getEmail());
        return user;
    }
}
