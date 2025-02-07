package org.eduorg.msauth.user.infraestructure.mappers;

import lombok.NoArgsConstructor;
import org.eduorg.msauth.common.application.mapper.IMapper;
import org.eduorg.msauth.user.domain.User;
import org.eduorg.msauth.user.domain.vo.*;
import org.eduorg.msauth.user.infraestructure.model.OdmPhone;
import org.eduorg.msauth.user.infraestructure.model.OdmUserEntity;

import java.text.ParseException;
import java.util.UUID;

@NoArgsConstructor
public class UserMapper implements IMapper<User, OdmUserEntity> {

    @Override
    public OdmUserEntity fromDomainToPersistence(User domain) {

        OdmPhone phone = new OdmPhone(
                domain.getPhone().getPhoneCode(),
                domain.getPhone().getPhoneNumber()
        );

        return OdmUserEntity.builder()
                .id(domain.getId().getId())
                .name(domain.getName().getName())
                .lastname(domain.getName().getLastname())
                .email(domain.getEmail().getEmail())
                .phone(phone)
                .gender(domain.getGender().toString())
                .birthdate(domain.getBirthdate().getBirthdate())
                .build();
    }

    @Override
    public User fromPersistenceToDomain(OdmUserEntity persistence) {

            return User.create(
                    UserId.create(persistence.getId()),
                    UserName.create(persistence.getName(), persistence.getLastname()),
                    UserEmail.create(persistence.getEmail()),
                    UserPhone.create(persistence.getPhone().getCode(), persistence.getPhone().getNumber()),
                    UserBirthdate.create(persistence.getBirthdate()),
                    mapGender(persistence.getGender())
            );
    }

    private UserGender mapGender( String g ){

        UserGender gender = UserGender.OTHER;
        if ("MALE".equalsIgnoreCase(g)) {
            gender = UserGender.MALE;
        } else if ("FEMALE".equalsIgnoreCase(g)) {
            gender = UserGender.FEMALE;
        }
        return gender;
    }
}
