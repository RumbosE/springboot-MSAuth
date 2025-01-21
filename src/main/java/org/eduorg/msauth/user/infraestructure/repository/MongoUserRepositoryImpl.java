package org.eduorg.msauth.user.infraestructure.repository;

import lombok.RequiredArgsConstructor;
import org.eduorg.msauth.user.domain.User;
import org.eduorg.msauth.user.domain.repository.IUserRepository;
import org.eduorg.msauth.user.domain.vo.UserEmail;
import org.eduorg.msauth.user.domain.vo.UserId;
import org.eduorg.msauth.user.infraestructure.mappers.UserMapper;
import org.eduorg.msauth.user.infraestructure.model.OdmUserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MongoUserRepositoryImpl implements IUserRepository {

    private MongoUserRepository mongoUserRepository;

    private UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        OdmUserEntity newUser = userMapper.fromDomainToPersistence(user);
        mongoUserRepository.save(newUser);
    }

    @Override
    public Optional<User> findUserById(UserId id) {
        Optional<User> user = mongoUserRepository.findById(id.getId())
                .map(userMapper::fromPersistenceToDomain);
        return user;
    }

    @Override
    public Optional<User> findUserByEmail(UserEmail email) {
        OdmUserEntity user = mongoUserRepository.findByEmail(email.getEmail());

        Optional<User> res = Optional.ofNullable(user)
                .map(userMapper::fromPersistenceToDomain);
        return res;
    }
}
