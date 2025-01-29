package org.eduorg.msauth.auth.infraestructure.services.login.dtos;


import org.eduorg.msauth.common.application.service.dto.ApplicationServiceEntryDto;

public class LoginUserEntryDto extends ApplicationServiceEntryDto {

        private String email;
        private String password;

        public LoginUserEntryDto(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
}
