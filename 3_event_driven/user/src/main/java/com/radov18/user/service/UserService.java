package com.radov18.user.service;

import com.radov18.user.amqp.dto.UserMessageDto;

public interface UserService {
    void createUser(UserMessageDto userReqDto);
}
