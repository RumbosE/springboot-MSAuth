package org.eduorg.msauth.user.application.services.user_details;

import org.eduorg.msauth.common.application.service.IService;
import org.eduorg.msauth.common.utils.result.Result;
import org.eduorg.msauth.user.application.exceptions.NonExistentUserException;
import org.eduorg.msauth.user.application.services.user_details.dtos.UserDetailsEntryDto;
import org.eduorg.msauth.user.application.services.user_details.dtos.UserDetailsResponseDto;
import org.eduorg.msauth.user.domain.User;
import org.eduorg.msauth.user.domain.repository.IUserRepository;
import org.eduorg.msauth.user.domain.vo.UserEmail;

import java.util.Optional;

public class GetUserDetailsService implements IService<UserDetailsEntryDto, UserDetailsResponseDto> {

    private final IUserRepository userRepository;

    public GetUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

        @Override
        public Result<UserDetailsResponseDto> execute(UserDetailsEntryDto data) {
            final Optional<User> user = userRepository.findUserByEmail( UserEmail.create( data.email() ));
            if (user.isEmpty()) {
                return Result.makeFailure( new NonExistentUserException() );
            }

            final User res = user.get();

            return Result.makeResult( new UserDetailsResponseDto(
                    res.getId().getId().toString(),
                    res.getName().getName(),
                    res.getName().getLastname(),
                    res.getEmail().getEmail(),
                    res.getPhone().getFullPhone(),
                    res.getBirthdate().getBirthdate(),
                    res.getGender().toString()
            ));
        }
}
