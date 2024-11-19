package com.radov18.user.security.service;

import com.radov18.user.security.dto.KeycloakUserResDto;

public interface SecurityAdminService {
    KeycloakUserResDto getUser(String userId);
}
