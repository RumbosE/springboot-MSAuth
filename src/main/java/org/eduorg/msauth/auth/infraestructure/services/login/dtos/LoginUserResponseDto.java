package org.eduorg.msauth.auth.infraestructure.services.login.dtos;

public class LoginUserResponseDto {

    private final String id;
    private final String email;
    private final String _token;

    public LoginUserResponseDto(String id, String email, String _token) {
        this.id = id;
        this.email = email;
        this._token = _token;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return _token;
    }
}
