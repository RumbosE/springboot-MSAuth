package org.eduorg.msauth.user.application.services.update_details;


import org.eduorg.msauth.common.application.service.IService;
import org.eduorg.msauth.common.utils.result.Result;
import org.eduorg.msauth.user.application.exceptions.NonExistentUserException;
import org.eduorg.msauth.user.application.services.update_details.dtos.UpdateUserDetailsEntryDto;
import org.eduorg.msauth.user.application.services.update_details.dtos.UpdateUserDetailsResponseDto;
import org.eduorg.msauth.user.domain.User;
import org.eduorg.msauth.user.domain.repository.IUserRepository;
import org.eduorg.msauth.user.domain.vo.*;

import java.util.Date;
import java.util.Optional;

public class UpdateUserDetailsService implements IService<UpdateUserDetailsEntryDto, UpdateUserDetailsResponseDto> {

    private final IUserRepository userRepository;

    public UpdateUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Result<UpdateUserDetailsResponseDto> execute(UpdateUserDetailsEntryDto data) {
        try {
            System.out.println("UpdateUserDetailsService.execute data: " + data);
            final Optional<User> optionalUser = userRepository.findUserByEmail( UserEmail.create( data.email() ));

            if (optionalUser.isEmpty()) {
                return Result.makeFailure( new NonExistentUserException() );
            }

            final User user = optionalUser.get();
            
            if (data.name() != null && data.lastname() != null) {
                user.updateName( UserName.create(data.name(), data.lastname()) );
            } else if (data.name() != null) {
                user.updateName(UserName.create(data.name(), user.getName().getLastname()));
            } else if (data.lastname() != null) {
                user.updateName(UserName.create(user.getName().getName(), data.lastname()));
            }
            //data.name() == null ? user.(user.getName()) : user.updateName(UserName.create( data.name(), user.getName().getLastname() ));
            
            if( data.phoneCode() != null && data.phoneNumber() != null ) {
                user.updatePhone( UserPhone.create(data.phoneCode(), data.phoneNumber()));
            } else if (data.phoneCode() != null) {
                user.updatePhone( UserPhone.create(data.phoneCode(), user.getPhone().getPhoneNumber()) );
            } else if (data.phoneNumber() != null) {
                user.updatePhone( UserPhone.create(user.getPhone().getPhoneCode(), data.phoneNumber()) );
            }

            if(data.birthdate() != null) {
                user.updateBirthdate( UserBirthdate.create(data.birthdate()));
            }

            if(data.gender() != null){
                final UserGender gen;
                if(data.gender().equals("M")) {
                    gen = UserGender.MALE;
                } else if (data.gender().equals("F")) {
                    gen = UserGender.FEMALE;
                } else {
                    gen = UserGender.OTHER;
                }

                user.updateGender(gen);
            };

            userRepository.updateUser(user);
            return Result.makeResult(new UpdateUserDetailsResponseDto(
                    user.getId().getId().toString(),
                    user.getEmail().getEmail(),
                    user.getName().getName(),
                    user.getName().getLastname(),
                    user.getPhone().getPhoneCode(),
                    user.getPhone().getPhoneNumber(),
                    user.getBirthdate().getBirthdate().toString(),
                    user.getGender().toString()
            ));
        } catch (Exception e) {
            return Result.makeFailure(e);
        }
    }

}
