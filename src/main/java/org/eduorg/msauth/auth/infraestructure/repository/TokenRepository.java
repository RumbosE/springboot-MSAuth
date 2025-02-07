package org.eduorg.msauth.auth.infraestructure.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends MongoRepository<Token, String> {

    Optional<Token> findByToken(String userId);

    List<Token> findByUserIdAndRevokedIsFalse(String userId);

}
