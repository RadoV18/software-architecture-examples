package com.radov18.dto;

public class UserMessageDto {
    private String keycloakId;
    private String type;

    public UserMessageDto(String keycloakId, String type) {
        this.keycloakId = keycloakId;
        this.type = type;
    }

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
