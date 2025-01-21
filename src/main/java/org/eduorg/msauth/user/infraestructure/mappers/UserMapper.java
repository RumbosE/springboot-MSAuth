package org.eduorg.msauth.user.infraestructure.mappers;

import org.eduorg.msauth.common.application.mapper.IMapper;
import org.eduorg.msauth.user.domain.User;
import org.eduorg.msauth.user.domain.vo.*;
import org.eduorg.msauth.user.infraestructure.model.OdmPhone;
import org.eduorg.msauth.user.infraestructure.model.OdmUserEntity;


public class UserMapper implements IMapper<User, OdmUserEntity> {

    @Override
    public OdmUserEntity fromDomainToPersistence(User domain) {

        OdmPhone phone = new OdmPhone(
                domain.getPhone().getPhoneCode(),
                domain.getPhone().getPhoneCode()
        );

            return new OdmUserEntity(
                    domain.getId().getId(),
                    domain.getName().getName(),
                    domain.getName().getLastname(),
                    domain.getEmail().getEmail(),
                    phone,
                    domain.getGender().toString(),
                    domain.getBirthdate().getBirthdate(),
                    null
            );
    }

    @Override
    public User fromPersistenceToDomain(OdmUserEntity persistence) {

        return User.create(
                UserId.create(persistence.getId()),
                UserName.create(persistence.getName(), persistence.getLastname()),
                UserEmail.create(persistence.getEmail()),
                UserPhone.create(persistence.getPhone().getAreaCode(), persistence.getPhone().getNumber()),
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
