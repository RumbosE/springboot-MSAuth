package org.eduorg.msauth.user.application.sing_up;

import org.eduorg.msauth.common.application.password_encoder.IPasswordEncoder;
import org.eduorg.msauth.user.application.exceptions.UserEmailAlreadyExistsException;
import org.eduorg.msauth.user.application.sing_up.dto.SignUpApplicationEntryDto;
import org.eduorg.msauth.common.application.service.IService;
import org.eduorg.msauth.common.utils.result.Result;
import org.eduorg.msauth.user.application.sing_up.dto.SignUpApplicationResponseDto;
import org.eduorg.msauth.user.domain.User;
import org.eduorg.msauth.user.domain.repository.IUserRepository;
import org.eduorg.msauth.user.domain.vo.*;

import java.util.Optional;
import java.util.UUID;


public class SingUpApplicationService implements IService<SignUpApplicationEntryDto, SignUpApplicationResponseDto> {

    private final IUserRepository userRepo;
    private final IPasswordEncoder passwordEncoder;

    public SingUpApplicationService(IUserRepository userRepo, IPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Result<SignUpApplicationResponseDto> execute(SignUpApplicationEntryDto data) {

        try{

            Optional<User> userOptional = userRepo.findUserByEmail( UserEmail.create( data.getEmail() ));
            if (userOptional.isPresent()) {
                throw new UserEmailAlreadyExistsException( data.getEmail() );
            }

            UUID uuid = UUID.randomUUID();
            UserGender gender = data.getGender().equals("M")
                    ? UserGender.MALE
                    : data.getGender().equals("F")
                    ? UserGender.FEMALE
                    : UserGender.OTHER;

            User user = User.create(
                    UserId.create( uuid ),
                    UserName.create( data.getName(), data.getLastname() ),
                    UserEmail.create( data.getEmail() ),
                    UserPhone.create( data.getPhoneCode(), data.getPhone() ),
                    UserBirthdate.create( data.getBirthdate() ),
                    gender
            );


            String password = passwordEncoder.encode( data.getPassword() );

            userRepo.saveUser(user, password);

            return Result.makeResult( new SignUpApplicationResponseDto(
                    user.getId().getId().toString(),
                    user.getEmail().getEmail(),
                    user.getName().getFullName()
            ));

        } catch (Exception e) {
            return Result.makeFailure( e );
        }

    }
}
