package com.radov18.user.security.service.impl;

import com.radov18.user.security.dto.KeycloakUserResDto;
import com.radov18.user.security.service.SecurityAdminService;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityAdminServiceImpl implements SecurityAdminService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityAdminServiceImpl.class);

    @Value("${keycloak.realm}")
    private String realm;

    private final Keycloak keycloak;

    @Override
    public KeycloakUserResDto getUser(String userId) {
        logger.info("Getting user {} from keycloak", userId);

        UserResource userResource = keycloak
                .realm(realm)
                .users()
                .get(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();
        if(userRepresentation == null) {
            logger.error("User {} not found in keycloak", userId);
            return null;
        }

        return KeycloakUserResDto.builder()
                .id(userId)
                .username(userRepresentation.getUsername())
                .firstName(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName())
                .email(userRepresentation.getEmail())
                .build();
    }
}
