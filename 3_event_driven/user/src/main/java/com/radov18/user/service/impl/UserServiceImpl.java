package com.radov18.user.service.impl;

import com.radov18.user.amqp.dto.UserMessageDto;
import com.radov18.user.entity.User;
import com.radov18.user.mapper.UserMapper;
import com.radov18.user.repository.UserRepository;
import com.radov18.user.security.dto.KeycloakUserResDto;
import com.radov18.user.security.service.SecurityAdminService;
import com.radov18.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final SecurityAdminService securityAdminService;

    private final UserRepository userRepository;

    @Override
    public void createUser(UserMessageDto userReqDto) {
        logger.info("Creating user {} from keycloak", userReqDto.getKeycloakId());
        KeycloakUserResDto userResDto = securityAdminService.getUser(userReqDto.getKeycloakId());
        if(userResDto == null) {
            return;
        }

        User userEntity = UserMapper.toEntityFromKeycloakUserResDto(userResDto);
        logger.info("Saving user {} to database", userEntity.getKeycloakId());
        userRepository.save(userEntity);
    }
}
