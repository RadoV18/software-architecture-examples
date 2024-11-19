package com.radov18.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "library_user")
@Getter
@Setter
public class User extends BaseEntity {

    private String keycloakId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public User() {
        super();
        this.status = true;
    }
}
