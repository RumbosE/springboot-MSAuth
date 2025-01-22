package org.eduorg.msauth.user.infraestructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OdmPhone {
    private String code;
    private String number;
}
