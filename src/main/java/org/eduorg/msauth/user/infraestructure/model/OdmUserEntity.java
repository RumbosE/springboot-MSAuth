package org.eduorg.msauth.user.infraestructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class OdmUserEntity {

    @Id
    private UUID id;
    private String name;
    private String lastname;
    private String password;
    @Indexed(unique = true)
    private String email;
    private OdmPhone phone;
    private String gender;
    private LocalDate birthdate;

}
