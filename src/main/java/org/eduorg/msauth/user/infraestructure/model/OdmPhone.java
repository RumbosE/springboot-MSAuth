package org.eduorg.msauth.user.infraestructure.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OdmPhone {
    private String number;
    private String areaCode;

    public OdmPhone(String number, String areaCode) {
        this.number = number;
        this.areaCode = areaCode;
    }
}
