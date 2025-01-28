package org.eduorg.msauth.user.application.services.sing_up.dto;


public class SignUpApplicationResponseDto {
    String id;
    String email;
    String name;

    public SignUpApplicationResponseDto(String id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
