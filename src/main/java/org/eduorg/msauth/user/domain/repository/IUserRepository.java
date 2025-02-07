package org.eduorg.msauth.user.domain.repository;

import org.eduorg.msauth.user.domain.User;
import org.eduorg.msauth.user.domain.vo.UserEmail;
import org.eduorg.msauth.user.domain.vo.UserId;
import org.eduorg.msauth.user.infraestructure.model.OdmUserEntity;

import java.util.Optional;
import java.util.concurrent.Future;

public interface IUserRepository {

    void saveUser(User user, String password);

    Optional<User> findUserById(UserId id);

    Optional<User> findUserByEmail(UserEmail email);

    void updateUser(User user);
}
