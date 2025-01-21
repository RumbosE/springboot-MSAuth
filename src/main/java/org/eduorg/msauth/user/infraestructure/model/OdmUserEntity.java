package org.eduorg.msauth.user.infraestructure.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.util.Date;

@Data
@Document(collection = "users")
public class OdmUserEntity {

    @Id
    private String id;
    private String name;
    private String lastname;
    private String password;
    private String email;
    private OdmPhone phone;
    private String gender;
    private Date birthdate;

    public OdmUserEntity(
            String id, String name, String lastname, String email, OdmPhone phone, String gender, Date birthdate, String password
    ) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birthdate = birthdate;
        this.password = password;

    }
}
