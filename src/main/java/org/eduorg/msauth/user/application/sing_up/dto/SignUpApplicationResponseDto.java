package org.eduorg.msauth.user.application.sing_up.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignUpApplicationResponseDto {
    String id;
    String email;
    String name;

}
