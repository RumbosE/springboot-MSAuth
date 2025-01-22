package org.eduorg.msauth.user.application.sing_up.dto;

import org.eduorg.msauth.common.application.service.dto.ApplicationServiceEntryDto;

import java.util.Date;


public class SignUpApplicationEntryDto extends ApplicationServiceEntryDto {

    String email;
    String password;
    String name;
    String lastname;
    String phoneCode;
    String phone;
    Date birthdate;
    String gender;

    public SignUpApplicationEntryDto(String email, String password, String name, String surname, String phoneCode, String phone, Date birthdate, String gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = surname;
        this.phoneCode = phoneCode;
        this.phone = phone;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public String getPhone() {
        return phone;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getGender() {return gender;}
    
}
