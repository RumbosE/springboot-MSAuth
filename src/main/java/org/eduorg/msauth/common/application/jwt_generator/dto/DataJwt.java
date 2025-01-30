package org.eduorg.msauth.common.application.jwt_generator.dto;

import java.util.UUID;

public class DataJwt {

    private final UUID id;
    private final String email;

    public DataJwt(UUID id, String email) {
        this.id = id;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
