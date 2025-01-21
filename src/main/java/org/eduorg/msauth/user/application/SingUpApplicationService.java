package org.eduorg.msauth.user.application;

import org.eduorg.msauth.user.application.dto.SignUpApplicationEntryDto;
import org.eduorg.msauth.common.application.service.IService;
import org.eduorg.msauth.common.utils.result.Result;
import org.eduorg.msauth.user.application.dto.SignUpApplicationResponseDto;
import org.eduorg.msauth.user.domain.User;
import org.eduorg.msauth.user.domain.repository.IUserRepository;
import org.eduorg.msauth.user.domain.vo.*;


public class SingUpApplicationService implements IService<SignUpApplicationEntryDto, SignUpApplicationResponseDto> {

    private final IUserRepository userRepo;

    public SingUpApplicationService(IUserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    public Result<SignUpApplicationResponseDto> execute(SignUpApplicationEntryDto data) {
        System.out.println("SignUpApplicationService.execute");

        try{
            User user = User.create(
                    UserId.create("31231-1231sad-1231"),
                    UserName.create( data.getName(), data.getLastname() ),
                    UserEmail.create(data.getEmail()),
                    UserPhone.create(data.getPhoneCode(), data.getPhone()),
                    UserBirthdate.create(data.getBirthdate()),
                    UserGender.MALE
            );

            userRepo.saveUser(user);

            return Result.makeResult( new SignUpApplicationResponseDto(
                    "31231-1231sad-1231",
                    user.getEmail().getEmail(),
                    user.getName().getFullName()
            ));

        } catch (Exception e) {
            return Result.makeFailure( e );
        }

    }
}
