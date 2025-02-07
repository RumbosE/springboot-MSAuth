package org.eduorg.msauth.user.infraestructure.repository;

import org.eduorg.msauth.user.application.exceptions.NonExistentUserException;
import org.eduorg.msauth.user.domain.User;
import org.eduorg.msauth.user.domain.repository.IUserRepository;
import org.eduorg.msauth.user.domain.vo.UserEmail;
import org.eduorg.msauth.user.domain.vo.UserId;
import org.eduorg.msauth.user.infraestructure.mappers.UserMapper;
import org.eduorg.msauth.user.infraestructure.model.OdmUserEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepositoryImpl implements IUserRepository {

    private final static UserMapper userMapper = new UserMapper();
    private final MongoUserRepository mongoUserRepository;
    UserRepositoryImpl(
            MongoUserRepository mongoUserRepository
    ) {
        this.mongoUserRepository = mongoUserRepository;
    }

    @Override
    public void saveUser(User user, String password) {
        OdmUserEntity entity = userMapper.fromDomainToPersistence(user);
        entity.setPassword(password);
        mongoUserRepository.save(entity);
    }

    @Override
    public Optional<User> findUserById(UserId id) {
        return mongoUserRepository.findById(id.getId().toString())
                .map(userMapper::fromPersistenceToDomain);
    }

    @Override
    public Optional<User> findUserByEmail(UserEmail email) {

        OdmUserEntity entity = mongoUserRepository.findByEmail(email.getEmail()).orElse(null);

        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(userMapper.fromPersistenceToDomain(entity));
    }

    @Override
    public void updateUser(User user) {
        Optional<OdmUserEntity> existingEntityOpt = mongoUserRepository.findByEmail(user.getEmail().getEmail());
        if (existingEntityOpt.isPresent()) {
            OdmUserEntity existingEntity = existingEntityOpt.get();
            OdmUserEntity updatedEntity = userMapper.fromDomainToPersistence(user);
            updatedEntity.setPassword(existingEntity.getPassword()); // Mantén la contraseña existente
            mongoUserRepository.save(updatedEntity);
        } else {
            throw new NonExistentUserException();
        }
    }
}
