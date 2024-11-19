package com.radov18.user.amqp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserMessageDto {
    private String keycloakId;
    private String type;

    public UserMessageDto(String keycloakId, String type) {
        this.keycloakId = keycloakId;
        this.type = type;
    }
}
