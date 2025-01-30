package org.eduorg.msauth.user.infraestructure.repository;
import org.eduorg.msauth.user.infraestructure.model.OdmUserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoUserRepository extends MongoRepository<OdmUserEntity, String> {
    Optional<OdmUserEntity> findByEmail(String email);
}